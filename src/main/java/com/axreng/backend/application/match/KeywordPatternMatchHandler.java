package com.axreng.backend.application.match;

import com.axreng.backend.application.pattern.IPattern;
import com.axreng.backend.application.pattern.KeywordPattern;
import com.axreng.backend.domain.commons.events.IEventDispatcher;
import com.axreng.backend.domain.query.IQuery;
import com.axreng.backend.domain.search.entity.ISearch;
import com.axreng.backend.domain.search.events.KeywordFound;

public class KeywordPatternMatchHandler implements IPatternMatchHandler {
    private final IPattern regex;
    private final IEventDispatcher dispatcher;

    public KeywordPatternMatchHandler(IEventDispatcher dispatcher, KeywordPattern pattern) {
        this.dispatcher = dispatcher;
        this.regex = pattern;
    }

    @Override
    public synchronized void process(String document, ISearch search, IQuery query) {
        if (this.regex.match(document)) {
            KeywordFound event = new KeywordFound(search, query);
            dispatcher.notify(event);
        }
    }
}
