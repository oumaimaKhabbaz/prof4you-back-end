package com.prof4you.app.controllers;

import com.prof4you.app.entities.Review;
import com.prof4you.app.services.api.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewController.class);

    private ReviewService reviewService;

    @Autowired
    ReviewController( final ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity createReview(@RequestBody Review review){
        return  new ResponseEntity(reviewService.create(review), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getReview(@PathVariable Long id) {
        final Optional<Review> review =  reviewService.findReviewById(id);
        if (review.isPresent()){
            new ResponseEntity( review.get(), HttpStatus.OK);
        }
        return  new ResponseEntity("Review with id= "+ id + " not found!", HttpStatus.NOT_FOUND);
    }


    @PutMapping
    public ResponseEntity updateReview(@RequestBody Review review, @PathVariable Long id){
        return  new ResponseEntity(reviewService.update(review, id), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteReview(@PathVariable Long id){
        return  new ResponseEntity(reviewService.delete(id), HttpStatus.OK);
    }

}
