package com.chaos.restControllers.endpoints;

import com.chaos.entities.Comment;
import com.chaos.entities.EmailSubscriber;
import com.chaos.entities.Post;
import com.chaos.entities.Tag;
import com.chaos.interfacesRepository.EmailSubscriberRepository;
import com.chaos.restControllers.model.PostsRequestDTO;
import com.chaos.restControllers.model.PostsResponseDTO;
import com.chaos.services.BlogService;
import com.chaos.services.EmailService;
import com.chaos.services.TagService;
import com.chaos.services.UserService;
import com.chaos.utils.BeanCopyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by marwen on 21/12/15.
 */

@RestController
@RequestMapping(value="/api")
public class BlogController {

    private final static Logger LOGGER = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private UserService userService;
    @Autowired private TagService tagService;
    @Autowired
    private BlogService blogService;
    @Autowired private EmailSubscriberRepository emailSubscriberRepository;

    @Autowired
    EmailService emailService;

    @RequestMapping(value="/posts", method= RequestMethod.GET)
    public PostsResponseDTO findPosts(PostsRequestDTO request) {
        Page<Post> pageData = blogService.findPosts(request);
        return new PostsResponseDTO(pageData);
    }

    @RequestMapping(value="/posts/{postId}", method=RequestMethod.GET)
    public Post findPostById(@PathVariable(value="postId") Integer postId) {
        LOGGER.debug("View Post id: "+postId);
        Post post = blogService.findPostById(postId);
        Post postCopy = BeanCopyUtils.copy(post);
        List<Comment> comments = post.getComments();
        for (Comment comment : comments)
        {
            postCopy.getComments().add(BeanCopyUtils.copy(comment));
        }
        return postCopy;
    }

    @RequestMapping(value="/posts/{postId}/comments", method=RequestMethod.POST)
    public Comment addComment(@PathVariable(value="postId") Integer postId, @RequestBody Comment comment) {
        comment.setPost(new Post(postId));
        Comment createdComment = blogService.createComment(comment);
        String subject = "New Comment on ["+comment.getPost().getTitle()+"] published";
        String content = "A new post with title \""+comment.getPost().getTitle()+"\" is published.\nThanks,\nSiva";
        emailService.sendEmail(subject, content);
        return createdComment;
    }

    @RequestMapping(value="/tags", method=RequestMethod.GET)
    public List<Tag> findAllTags()
    {
        return tagService.findAllTags();
    }

    @RequestMapping(value="/emailSubscribers", method=RequestMethod.POST)
    public EmailSubscriber createEmailSubscriber(@RequestBody EmailSubscriber subscriber)
    {
        return emailSubscriberRepository.save(subscriber);
    }
}
