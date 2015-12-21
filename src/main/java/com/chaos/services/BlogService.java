package com.chaos.services;

import com.chaos.entities.Comment;
import com.chaos.entities.Post;
import com.chaos.restControllers.model.PostsRequestDTO;
import org.springframework.data.domain.Page;

/**
 * Created by marwen on 21/12/15.
 */
public class BlogService {
    public Page<Post> findPosts(PostsRequestDTO request) {
        return null;
    }

    public Post findPostById(Integer postId) {
        return null;
    }

    public Comment createComment(Comment comment) {
        return null;
    }

    public Post createPost(Post post) {
        return null;
    }

    public void deletePost(Integer postId) {

    }
}
