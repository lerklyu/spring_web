package ru.netology.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.model.PostDto;
import ru.netology.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<PostDto> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable("id") long id) {
        return service.getById(id);
    }

    @PostMapping
    public PostDto save(@RequestBody PostDto post) {
        return service.save(post);
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable("id") long id) {
        service.removeById(id);
    }
}