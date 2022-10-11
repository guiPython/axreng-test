package com.axreng.backend.domain.search.events;

import com.axreng.backend.domain.commons.events.IEvent;
import com.axreng.backend.domain.query.IQuery;
import com.axreng.backend.domain.search.entity.ISearch;

public class KeywordFound implements IEvent{
    public final ISearch search;
    public final IQuery query;

    public KeywordFound(ISearch search, IQuery query){
        this.search = search;
        this.query = query;
    }
}
