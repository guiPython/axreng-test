package com.axreng.backend.application.pattern;

import java.util.List;
import java.util.regex.Pattern;

public interface IPattern {
    Pattern pattern();
    boolean match(String value);
    List<String> matches(String value);
}
