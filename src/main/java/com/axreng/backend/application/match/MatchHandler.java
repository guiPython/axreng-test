package com.axreng.backend.application.match;

import com.axreng.backend.domain.query.IQuery;
import com.axreng.backend.domain.search.entity.ISearch;

public class MatchHandler {
    private final ISearch search;
    private final IPatternMatch[] handlers;
    
    public MatchHandler(ISearch search, IPatternMatch... patterns){
        this.search = search;
        this.handlers = patterns;
    }

    public synchronized void handle(String document, IQuery query){
        for (var handler : this.handlers){
            handler.process(document, search, query);
        }
    }
}
