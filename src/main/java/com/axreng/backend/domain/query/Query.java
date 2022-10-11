package com.axreng.backend.domain.query;

import java.util.concurrent.atomic.AtomicBoolean;

import com.axreng.backend.domain.commons.url.Url;

public class Query implements IQuery{
    private final Url url;
    private final AtomicBoolean completed;

    public Query(com.axreng.backend.domain.commons.url.Url url){
        this.url = url;
        this.completed = new AtomicBoolean(false);
    }

    @Override
    public Url url() {
        return this.url;
    }

    @Override
    public synchronized boolean isCompleted() {
        return completed.get();
    }

    @Override
    public synchronized  void complete() {
        this.completed.set(true);
    }
    
}
