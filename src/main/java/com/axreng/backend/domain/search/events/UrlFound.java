package com.axreng.backend.domain.search.events;

import com.axreng.backend.domain.commons.events.IEvent;
import com.axreng.backend.domain.search.entity.ISearch;

public class UrlFound implements IEvent{
    public final String url;
    public final ISearch search;
    
    public UrlFound(ISearch search, String url){
        this.search = search;
        this.url = url;
    }
}
