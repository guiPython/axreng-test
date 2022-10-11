package com.axreng.backend.application.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class HrefHtmlPattern implements IPattern {
    private final Pattern regex;
    private final String hrefHtmlPattern = "\\s*(?i)href\\s*=\\s*(\\\"([^\\\"]*\\.html\\\")|'[^']*'|([^'\\\">\\\\s\\.html]+))";
    private final int groupOfRegexResult = 1;

    public HrefHtmlPattern(){
        this.regex = Pattern.compile(hrefHtmlPattern);
    }

    @Override
    public Pattern pattern() {
        return this.regex;
    }
    
    @Override
    public boolean match(String value) {
        return this.regex.matcher(value).find();
    }

    @Override
    public List<String> matches(String value) {
        List<String> results = new ArrayList<>();
        var macther = this.regex.matcher(value);
        while(macther.find()){
            results.add(macther.group(groupOfRegexResult).replaceAll("\"", ""));
        }
        return results;
    }
}
