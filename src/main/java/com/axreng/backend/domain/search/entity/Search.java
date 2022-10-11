package com.axreng.backend.domain.search.entity;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.axreng.backend.domain.commons.url.Url;
import com.axreng.backend.domain.query.IQuery;
import com.axreng.backend.domain.query.Query;

public class Search implements ISearch {
    private Url url;
    private String keyword;
    private AtomicInteger results;
    private Integer limitOfResults;
    private AtomicBoolean completed;
    private Set<String> queries;
    private ConcurrentLinkedQueue<IQuery> queue;

    private void validate() throws Exception {
        if (this.keyword == null || this.keyword.length() > 32 || this.keyword.length() < 4 || this.keyword.isBlank()) {
            throw new Exception(
                    "Cannot create a search with invalid keyword, keyword must be greater 4 and letter 32 (chars)");
        }

        if (this.limitOfResults <= 0) {
            this.limitOfResults = -1;
        }
    }

    public Search(Url url, String keyword, Integer limitOfResults) throws Exception {
        this.url = url;
        this.keyword = keyword;
        this.results = new AtomicInteger(0);
        this.completed = new AtomicBoolean(false);
        this.queries = Collections.synchronizedSet(new HashSet<>());
        this.queries.add(this.url.toString());
        this.queue = new ConcurrentLinkedQueue<>();
        this.queue.add(new Query(this.url));
        this.limitOfResults = limitOfResults;
        this.validate();
    }

    public Search(String url, String keyword, String limitOfResults) throws Exception {
        try {
            this.url = new Url(url);
            this.keyword = keyword;
            this.results = new AtomicInteger(0);
            this.completed = new AtomicBoolean(false);
            this.queries = Collections.synchronizedSet(new HashSet<>());
            this.queries.add(this.url.toString());
            this.queue = new ConcurrentLinkedQueue<>();
            this.queue.add(new Query(this.url));
            this.limitOfResults = Integer.valueOf(limitOfResults);
        } catch (MalformedURLException e) {
            throw new Exception("Cannot create a search with invalid url");
        } catch (NumberFormatException e) {
            this.limitOfResults = -1;
        }
        this.validate();
    }

    @Override
    public synchronized Url url() {
        return this.url;
    }

    @Override
    public synchronized String keyword() {
        return this.keyword;
    }

    @Override
    public synchronized Integer results() {
        return this.results.get();
    }

    @Override
    public synchronized ConcurrentLinkedQueue<IQuery> queries() {
        return this.queue;
    }

    @Override
    public synchronized Integer limit() {
        return this.limitOfResults;
    }

    @Override
    public synchronized boolean isCompleted() {
        if (this.completed.get())
            return true;
        for (var query : this.queries()) {
            if (!query.isCompleted())
                return false;
        }
        return this.completed.get();
    }

    @Override
    public synchronized boolean isLimited() {
        return this.limitOfResults != -1;
    }

    @Override
    public synchronized void addQuery(IQuery query) {
        if (query.url().containsPathOfUrl(this.url()) && !this.completed.get()) {
            if(this.queries.add(query.url().toString())){
                this.queue.add(query);
            }
        }
    }

    @Override
    public synchronized void incrementResult() {
        if (this.isLimited()) {
            if (this.results.get() < this.limitOfResults) {
                this.results.incrementAndGet();
                return;
            }
            this.completed.set(true);
            return;
        }
        this.results.incrementAndGet();
    }
}
