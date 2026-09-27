package ru.netology.model;

public class Post {
    private long id;
    private String content;
    private boolean removed;

    public Post() {
    }

    public Post(long id, String content, boolean removed) {
        this.id = id;
        this.content = content;
        this.removed = removed;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
}
