package com.chaos.interfacesRepository;

import com.chaos.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by marwen on 23/12/15.
 */
public interface TagRepository extends JpaRepository<Tag, Integer> {
}
