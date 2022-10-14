package com.axreng.backend.domain.search.events;

import com.axreng.backend.domain.commons.events.IEventHandler;

public class KeywordFoundHandler implements IEventHandler<KeywordFound>{

    @Override
    public synchronized void handle(KeywordFound data) {
        data.search.incrementResult();
        if(!data.search.isCompleted()){
            System.out.println("Result found: " + data.query.url().toString());
        }
    }
    
}
