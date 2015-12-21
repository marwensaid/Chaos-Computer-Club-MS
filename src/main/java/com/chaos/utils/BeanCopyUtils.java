package com.chaos.utils;

import com.chaos.entities.Comment;
import com.chaos.entities.Post;
import com.chaos.entities.User;

/**
 * Created by marwen on 20/12/15.
 */
public class BeanCopyUtils {

    public BeanCopyUtils() {
    }
    public static User copy(User user)
    {
        if(user == null)
        {
            return null;
        }
        User clone = new User();
        clone.setId(user.getId());
        clone.setEmail(user.getEmail());
        clone.setPassword(user.getPassword());
        clone.setName(user.getName());
        return clone;
    }

    public static Post copy(Post post)
    {
        if(post == null){
            return null;
        }
        Post clone = new Post();
        clone.setId(post.getId());
        clone.setTitle(post.getTitle());
        clone.setContent(post.getContent());
        clone.setCreatedOn(post.getCreatedOn());
        clone.setUpdatedOn(post.getUpdatedOn());

        return clone;
    }

    public static Comment copy(Comment comment)
    {
        if(comment == null){
            return null;
        }
        Comment clone = new Comment();
        clone.setId(comment.getId());
        clone.setName(comment.getName());
        clone.setContent(comment.getContent());
        clone.setCreatedOn(comment.getCreatedOn());
        clone.setUpdatedOn(comment.getUpdatedOn());

        return clone;
    }
}
