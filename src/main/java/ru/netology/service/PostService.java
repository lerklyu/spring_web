package ru.netology.service;

import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.model.PostDto;
import ru.netology.repository.PostRepository;
import ru.netology.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final Repository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<PostDto> all() {
        return repository.all().stream()
                .filter(post -> !post.isRemoved())
                .map(PostDto::fromPost)
                .toList();
    }

    public PostDto getById(long id) {
        Post post = repository.getById(id).orElseThrow(NotFoundException::new);
        PostDto dto = PostDto.fromPost(post);
        if (!post.isRemoved()) {
            return dto;
        } else {
            throw new NotFoundException("Пост удалён или не существует");
        }
    }

    public PostDto save(PostDto dto) {
        Post post = dto.toPost();
        repository.save(post);
        return PostDto.fromPost(post);
    }

    public void removeById(long id) {
        Optional<Post> postOptional = repository.getById(id);
        if (postOptional.isEmpty()) {
            throw new NotFoundException("Пост таким id не найден");
        } else {
            Post post = postOptional.get();
            post.setRemoved(true);
            repository.save(post);
        }
    }
}

