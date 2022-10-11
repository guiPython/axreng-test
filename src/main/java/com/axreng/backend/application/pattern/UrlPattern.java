package com.axreng.backend.application.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UrlPattern implements IPattern {

    private final Pattern regex;
    private final int groupOfRegexResult = 0;

    public UrlPattern() {
        this.regex = Pattern.compile(
                "\\b((http|https)://)(www.)?"
                        + "[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]"
                        + "{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)",
                Pattern.MULTILINE);
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
        while (macther.find()) {
            results.add(macther.group(groupOfRegexResult));
        }
        return results;
    }
}
