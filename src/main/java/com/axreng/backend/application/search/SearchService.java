package com.axreng.backend.application.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.axreng.backend.application.match.MatchHandler;
import com.axreng.backend.domain.query.IQuery;
import com.axreng.backend.domain.search.entity.ISearch;

public class SearchService {
    private final MatchHandler matchHandler;

    public SearchService(MatchHandler matchHandler) {
        this.matchHandler = matchHandler;
    }

    private class Task implements Runnable {

        private final IQuery query;
        private final MatchHandler matchHandler;

        public Task(IQuery query, MatchHandler matchHandler) {
            this.query = query;
            this.matchHandler = matchHandler;
        }

        @Override
        public void run() {
            try (var buffer = new BufferedReader(
                    new InputStreamReader(query.url().getValue().openConnection().getInputStream()));) {
                String content = buffer.lines().collect(Collectors.joining("\n"));
                matchHandler.handle(content, query);
                buffer.close();
            } catch (IOException e) {
                System.out.println(e + " " + query.url().toString());
            } finally {
                query.complete();
            }

        }
    }

    public void execute(ISearch search) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        try {
            while (!search.isCompleted()) {
                var query = search.queries().poll();
                if (query != null) {
                    executor.execute(new Task(query, matchHandler));
                }
            }
            executor.shutdownNow();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
