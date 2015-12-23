package com.chaos.services;

import com.chaos.entities.Tag;
import com.chaos.interfacesRepository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by marwen on 21/12/15.
 */

@Service
@Transactional
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }

    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }
}
