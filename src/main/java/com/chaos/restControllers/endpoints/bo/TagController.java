package com.chaos.restControllers.endpoints.bo;

import com.chaos.entities.Tag;
import com.chaos.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by marwen on 21/12/15.
 */

@RestController
@RequestMapping(value="/api/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/tags", method = RequestMethod.POST)
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.createTag(tag);
    }
}
