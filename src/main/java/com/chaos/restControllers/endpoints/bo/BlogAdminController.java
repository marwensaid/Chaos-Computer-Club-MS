package com.chaos.restControllers.endpoints.bo;

import com.chaos.entities.Post;
import com.chaos.services.BlogService;
import com.chaos.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by marwen on 21/12/15.
 */

@RestController
@RequestMapping(value="/api/admin")
public class BlogAdminController {

    private final static Logger LOGGER = LoggerFactory.getLogger(BlogAdminController.class);

    @Autowired
    private BlogService blogService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value="/posts", method= RequestMethod.POST)
    public ResponseEntity<Post> createPost(@RequestBody Post post,
                                           HttpServletRequest request)
    {
        Post createdPost = this.blogService.createPost(post);
        String subject = "New Post ["+post.getTitle()+"] published";
        String content = "A new post with title \""+post.getTitle()+"\" is published.\nThanks,\nSiva";
        emailService.sendEmail(subject, content);
        return new ResponseEntity<Post>(createdPost, HttpStatus.OK);
    }

    @RequestMapping(value="/posts/{postId}", method=RequestMethod.DELETE)
    public void deletePostById(@PathVariable(value="postId") Integer postId) {
        LOGGER.debug("Delete Post id: "+postId);
        blogService.deletePost(postId);
    }

}
