package com.axreng.backend.application.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import com.axreng.backend.application.match.MatchHandler;
import com.axreng.backend.domain.query.IQuery;

public class SearchTask implements Runnable {
    private final IQuery query;
    private final MatchHandler matchHandler;

    public SearchTask(IQuery query, MatchHandler matchHandler) {
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
            System.out.println("Cannot read html of url " + query.url().toString());
        } finally {
            query.complete();
        }

    }
}
