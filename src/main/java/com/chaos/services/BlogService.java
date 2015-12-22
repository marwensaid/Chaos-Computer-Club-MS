package com.chaos.services;

import com.chaos.entities.Comment;
import com.chaos.entities.Post;
import com.chaos.interfacesRepository.CommentRepository;
import com.chaos.interfacesRepository.EmailSubscriberRepository;
import com.chaos.interfacesRepository.PostRepository;
import com.chaos.restControllers.model.PostsRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by marwen on 21/12/15.
 */

@Service
@Transactional
public class BlogService {

    @Autowired
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public Page<Post> findPosts(PostsRequestDTO postsRequestDTO) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdOn");
        if (postsRequestDTO.getPageNo() < 0) {
            postsRequestDTO.setPageNo(0);
        }
        if (postsRequestDTO.getPageSize() < 1) {
            postsRequestDTO.setPageSize(5);
        }
        Pageable pageable = new PageRequest(postsRequestDTO.getPageNo(), postsRequestDTO.getPageSize(), sort);

        return postRepository.findAll(pageable);
    }

    public Post findPostById(Integer postId) {
        return postRepository.findOne(postId);
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Integer postId) {
        postRepository.delete(postId);
    }

    public Page<Comment> findComments(PostsRequestDTO request) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdOn");
        if (request.getPageNo() < 0) {
            request.setPageNo(0);
        }
        if (request.getPageSize() < 1) {
            request.setPageSize(5);
        }
        Pageable pageable = new PageRequest(request.getPageNo(), request.getPageSize(), sort);

        return commentRepository.findAll(pageable);
    }

    public void deleteComment(Integer commentId) {
        commentRepository.delete(commentId);
    }
}
