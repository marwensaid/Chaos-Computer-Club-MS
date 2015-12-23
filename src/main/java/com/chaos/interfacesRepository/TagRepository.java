package com.chaos.interfacesRepository;

import com.chaos.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by marwen on 23/12/15.
 */
public interface TagRepository extends JpaRepository<Tag, Integer> {
}
