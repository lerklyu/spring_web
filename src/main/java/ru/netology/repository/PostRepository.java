package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository implements ru.netology.repository.Repository {

    private final AtomicLong counter = new AtomicLong(1);
    private final List<Post> dataBase = new CopyOnWriteArrayList<>();

    public List<Post> all() {
        return List.copyOf(dataBase);
    }

    public Optional<Post> getById(long id) {
        Iterator<Post> iterator = dataBase.iterator();
        while (iterator.hasNext()) {
            Post post = iterator.next();
            if (!post.isRemoved() && post.getId() == id) {
                return Optional.of(post);
            }
        }
        return Optional.empty();
    }

    public Post save(Post post) {
        if (post == null || post.getContent() == null) {
            throw new IllegalArgumentException("Пост или контент не могут быть null");
        }
        if (post.getId() == 0) {
            post.setId(counter.getAndIncrement());
            dataBase.add(post);
            return post;
        } else {
            for (Post p : dataBase) {
                if (p.getId() == post.getId()) {
                    p.setContent(post.getContent());
                    return p;
                }
            }
            throw new NotFoundException("Пост с таким идентификатором не найден");
        }
    }

    public void removeById(long id) {
            dataBase.removeIf(post -> post.getId() == id);
    }
}
