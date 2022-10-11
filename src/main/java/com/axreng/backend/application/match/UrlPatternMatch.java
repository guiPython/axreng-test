package com.axreng.backend.application.match;

import com.axreng.backend.application.pattern.IPattern;
import com.axreng.backend.application.pattern.UrlPattern;
import com.axreng.backend.domain.commons.events.IEventDispatcher;
import com.axreng.backend.domain.query.IQuery;
import com.axreng.backend.domain.search.entity.ISearch;
import com.axreng.backend.domain.search.events.UrlFound;

public class UrlPatternMatch implements IPatternMatch{
    private final IEventDispatcher dispatcher;
    private final UrlPattern regex;

    public UrlPatternMatch(IEventDispatcher dispatcher, UrlPattern pattern){
        this.dispatcher = dispatcher;
        this.regex = pattern;
    }

    @Override
    public IPattern pattern() {
        return this.regex;
    }

    @Override
    public void process(String document, ISearch search, IQuery query) {
        for(var match : this.regex.matches(document)){
            UrlFound event = new UrlFound(search, match);
            dispatcher.notify(event);
        }
    }

    @Override
    public String regex() {
        return this.regex.pattern().toString();
    }
    
}
