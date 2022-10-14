package com.axreng.backend.application.search;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.axreng.backend.application.match.MatchHandler;
import com.axreng.backend.domain.search.entity.ISearch;

public class SearchService {
    private final MatchHandler matchHandler;
    private final int LIMIT_OF_THREADS = 10;

    public SearchService(MatchHandler matchHandler) {
        this.matchHandler = matchHandler;
    }

    public void execute(ISearch search) {
        ExecutorService executor = Executors.newFixedThreadPool(LIMIT_OF_THREADS);
        while (!search.isCompleted()) {
            var query = search.queries().poll();
            if (query != null) {
                executor.execute(new SearchTask(query, matchHandler));
            }
        }
        executor.shutdownNow();
    }
}
