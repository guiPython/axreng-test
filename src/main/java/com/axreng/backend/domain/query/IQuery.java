package com.axreng.backend.domain.query;

import com.axreng.backend.domain.commons.url.Url;

public interface IQuery {
    Url url();
    void complete();
    boolean isCompleted();
}
