package com.axreng.backend.domain.search.entity;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.axreng.backend.domain.commons.url.Url;
import com.axreng.backend.domain.query.IQuery;

public interface ISearch {
    Url url();
    String keyword();
    Integer results();
    Integer limit();
    ConcurrentLinkedQueue<IQuery> queries();
    boolean isCompleted();
    boolean isLimited();

    void addQuery(IQuery query);
    void incrementResult();
}
