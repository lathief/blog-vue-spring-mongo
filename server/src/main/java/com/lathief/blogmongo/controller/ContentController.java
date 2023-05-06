package com.lathief.blogmongo.controller;

import com.lathief.blogmongo.model.Content;
import com.lathief.blogmongo.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blog")
@CrossOrigin(origins = "*")
public class ContentController {
    @Autowired
    ContentRepository contentRepository;

    @GetMapping("/contents")
    public ResponseEntity<List<Content>> getAllContents(@RequestParam(required = false) String title) {
        try {
            List<Content> contents = new ArrayList<>();
            if (title == null)
                contentRepository.findAll().forEach(contents::add);
            else
                contentRepository.findByTitleContaining(title).forEach(contents::add);

            if (contents.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(contents, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/content/{id}")
    public ResponseEntity<?> getContentById(@PathVariable("id") String id) {
        try {
            Optional<Content> contentData = contentRepository.findById(id);
            if (contentData.isPresent()) {
                Content _content = contentData.get();
                return new ResponseEntity<>(_content, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/content")
        public ResponseEntity<?> createContent(@RequestBody Content content) {
        try {
            System.out.println(content.getTitle());
            Content saveContent = contentRepository.save(content);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/content/{id}")
    public ResponseEntity<?> editContent(@PathVariable("id") String id, @RequestBody Content content) {
        try {
            Optional<Content> contentData = contentRepository.findById(id);

            if (contentData.isPresent()) {
                Content _content = contentData.get();
                _content.setTitle(content.getTitle());
                _content.setDescription(content.getDescription());
                _content.setBody(content.getBody());
                _content.setAuthor(content.getAuthor());
                return new ResponseEntity<>(contentRepository.save(_content), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/content/{id}")
    public ResponseEntity<HttpStatus> deleteContent(@PathVariable("id") String id) {
        try {
            contentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
