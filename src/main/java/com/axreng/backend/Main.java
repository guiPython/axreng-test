package com.axreng.backend;

import com.axreng.backend.application.match.HrefHtmlPatternMatchHandler;
import com.axreng.backend.application.match.KeywordPatternMatchHandler;
import com.axreng.backend.application.match.MatchManager;
import com.axreng.backend.application.pattern.HrefHtmlPattern;
import com.axreng.backend.application.pattern.KeywordPattern;
import com.axreng.backend.application.search.SearchService;
import com.axreng.backend.domain.commons.events.EventDispatcher;
import com.axreng.backend.domain.commons.events.IEventDispatcher;
import com.axreng.backend.domain.commons.events.IEventHandler;
import com.axreng.backend.domain.search.entity.Search;
import com.axreng.backend.domain.search.events.KeywordFound;
import com.axreng.backend.domain.search.events.KeywordFoundHandler;
import com.axreng.backend.domain.search.events.UrlFound;
import com.axreng.backend.domain.search.events.UrlFoundHandler;

public class Main {
    public static void main(String[] args) {
        try {
            // #region Create a search
            final String url = System.getenv("BASE_URL");
            final String keyword = System.getenv("KEYWORD");
            final String limitOfResults = System.getenv("MAX_RESULTS");
            Search search = new Search(url, keyword, limitOfResults);
            // #endregion

            // #region Dispatcher
            IEventDispatcher dispatcher = new EventDispatcher();
            IEventHandler<KeywordFound> keywordFoundHandler = new KeywordFoundHandler();
            dispatcher.subscribe(KeywordFound.class, keywordFoundHandler);
            IEventHandler<UrlFound> urlFoundHandler = new UrlFoundHandler();
            dispatcher.subscribe(UrlFound.class, urlFoundHandler);
            // #endregion

            // #region MatchHandler
            KeywordPatternMatchHandler keywordPatternMatch = new KeywordPatternMatchHandler(dispatcher,
                    new KeywordPattern(search.keyword()));
            HrefHtmlPatternMatchHandler hrefHtmlPatternMatch = new HrefHtmlPatternMatchHandler(dispatcher,
                    new HrefHtmlPattern());
            MatchManager matchManager = new MatchManager(search, keywordPatternMatch, hrefHtmlPatternMatch);
            // #endregion

            SearchService service = new SearchService(matchManager);
            service.execute(search);
            System.out.println("Results: " + search.results());
        } catch (Exception e) {
            System.exit(0);
        }
    }
}
