package com.axreng.backend.application.search;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.axreng.backend.application.match.MatchManager;
import com.axreng.backend.domain.search.entity.ISearch;

public class SearchService {
    private final MatchManager matchManager;
    private final int LIMIT_OF_THREADS = 8;

    public SearchService(MatchManager matchManager) {
        this.matchManager = matchManager;
    }

    public void execute(ISearch search) {
        ExecutorService executor = Executors.newFixedThreadPool(LIMIT_OF_THREADS);
        while (!search.isCompleted()) {
            var query = search.queries().poll();
            if (query != null) {
                executor.execute(new SearchTask(query, matchManager));
            }
        }
        executor.shutdownNow();
    }
}
