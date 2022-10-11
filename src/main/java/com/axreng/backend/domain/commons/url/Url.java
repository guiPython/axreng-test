package com.axreng.backend.domain.commons.url;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

public class Url {
    private final URL value;
    private final Pattern pattern;

    public Url(String rawUrl) throws MalformedURLException {
        this.value = new URL(rawUrl);
        this.pattern = Pattern.compile("((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)");
    }

    public String getPath() {
        return this.value.getPath();
    }

    public String getHost() {
        return this.value.getHost();
    }

    public URL getValue() {
        return this.value;
    }

    public Url concat(String path) throws MalformedURLException {
        if (this.pattern.matcher(path).find() || path.contains("://")) {
            throw new MalformedURLException(
                    String.format("Cannot concat two url`s: %s and %s", this.value.toString(), path));
        } else if (path.startsWith("../")) {
            var lastFolderIndex = this.value.toString().lastIndexOf("/");
            var urlWithoutLastFolder = this.value.toString().substring(0, lastFolderIndex);
            return new Url(urlWithoutLastFolder + path.substring(2));
        } else if (this.value.toString().endsWith("/")) {
            return new Url(this.value.toString() + path);
        }
        return new Url(this.value.toString() + "/" + path);
    }

    public boolean containsPathOfUrl(Url other) {
        if (this.value.toString().equals(other.getValue().toString()))
            return true;
        return this.getPath().contains(other.getPath()) && this.getHost().equals(other.getHost());
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public int hashCode() {
        return this.getHost().hashCode() + this.getPath().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other.getClass() == Url.class) {
            Url otherUrl = (Url) other;
            return otherUrl.getPath().equals(this.getPath()) && otherUrl.getHost().equals(this.getHost());
        }
        return false;
    }
}
