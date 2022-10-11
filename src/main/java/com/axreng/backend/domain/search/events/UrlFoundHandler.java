package com.axreng.backend.domain.search.events;

import java.net.MalformedURLException;

import com.axreng.backend.domain.commons.events.IEventHandler;
import com.axreng.backend.domain.commons.url.Url;
import com.axreng.backend.domain.query.Query;

public class UrlFoundHandler implements IEventHandler<UrlFound>{

    @Override
    public synchronized void handle(UrlFound data) {
        try{
            Url url = new Url(data.url);
            Query query = new Query(url);
            data.search.addQuery(query);
        }catch(MalformedURLException e){
            System.out.println(e.getMessage());
        }   
    }
    
}
