package com.chaos.restControllers.endpoints.bo;

import com.chaos.entities.Comment;
import com.chaos.restControllers.model.CommentsResponseDTO;
import com.chaos.restControllers.model.PostsRequestDTO;
import com.chaos.services.BlogService;
import com.chaos.services.EmailService;
import com.chaos.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by marwen on 21/12/15.
 */

@RestController
@RequestMapping(value="/api/admin")
public class CommentController {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value="/comments", method= RequestMethod.GET)
    public CommentsResponseDTO findComments(PostsRequestDTO request) //TODO;
    {
        Page<Comment> pageData = blogService.findComments(request);
        CommentsResponseDTO commentsResponse = new CommentsResponseDTO(pageData);
        return commentsResponse;
    }

    @RequestMapping(value="/comments/{commentId}", method=RequestMethod.DELETE)
    public void deleteCommentById(@PathVariable(value="commentId") Integer commentId) {
        LOGGER.debug("Delete comment id: "+commentId);
        blogService.deleteComment(commentId);
    }

    @RequestMapping(value="/comments", method=RequestMethod.DELETE)
    public void deleteComments(@RequestParam(value="commentIds") String commentIds) {
        LOGGER.debug("Delete comment ids: "+commentIds);
        String[] ids = commentIds.split(",");
        for (String strId : ids)
        {
            blogService.deleteComment(Integer.parseInt(strId));
        }

    }
}
