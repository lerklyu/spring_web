package ru.netology.model;

public record PostDto(long id, String content) {
    public static PostDto fromPost(Post post) {
        return new PostDto(post.getId(),
                post.getContent());
    }

    public Post toPost() {
        return new Post(id, content, false);
    }
}
