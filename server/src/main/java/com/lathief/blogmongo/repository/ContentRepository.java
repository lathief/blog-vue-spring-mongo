package com.lathief.blogmongo.repository;

import com.lathief.blogmongo.model.Content;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends MongoRepository<Content, String> {
    List<Content> findByTitleContaining(String title);
}
