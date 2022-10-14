package com.axreng.backend.application.match;

import com.axreng.backend.domain.query.IQuery;
import com.axreng.backend.domain.search.entity.ISearch;

public interface IPatternMatchHandler {
    void process(String document, ISearch search, IQuery query);
}
