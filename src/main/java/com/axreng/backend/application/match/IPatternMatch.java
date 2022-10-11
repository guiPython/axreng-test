package com.axreng.backend.application.match;

import com.axreng.backend.application.pattern.IPattern;
import com.axreng.backend.domain.query.IQuery;
import com.axreng.backend.domain.search.entity.ISearch;

public interface IPatternMatch {
    IPattern pattern();
    String regex();
    void process(String document, ISearch search, IQuery query);
}
