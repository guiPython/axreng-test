package com.axreng.backend.application.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class KeywordPattern implements IPattern {
    private final String keyword;
    private final Pattern regex;
    private final int groupOfRegexResult = 0;

    public KeywordPattern(String keyword) {
        this.keyword = keyword;
        this.regex = Pattern.compile(this.keyword, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
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
            results.add(macther.group(groupOfRegexResult));
        }
        return results;
    }
}
