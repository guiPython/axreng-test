package com.axreng.backend.application.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import com.axreng.backend.application.match.MatchManager;
import com.axreng.backend.domain.query.IQuery;

public class SearchTask implements Runnable {
    private final IQuery query;
    private final MatchManager matchManager;

    public SearchTask(IQuery query, MatchManager matchManager) {
        this.query = query;
        this.matchManager = matchManager;
    }

    @Override
    public void run() {
        try (var buffer = new BufferedReader(
                new InputStreamReader(query.url().getValue().openConnection().getInputStream()));) {
            String content = buffer.lines().collect(Collectors.joining("\n"));
            matchManager.handle(content, query);
            buffer.close();
        } catch (IOException e) {
            System.out.println("Cannot read html of url " + query.url().toString());
        } finally {
            query.complete();
        }

    }
}
