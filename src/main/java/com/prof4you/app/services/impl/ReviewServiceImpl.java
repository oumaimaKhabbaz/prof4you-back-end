package com.prof4you.app.services.impl;

import com.prof4you.app.entities.Review;
import com.prof4you.app.repositories.ReviewRepository;
import com.prof4you.app.services.api.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private ReviewRepository reviewRepository;

    @Autowired
    ReviewServiceImpl (final ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Optional<Review> findReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public Review update(Review review, Long id) {
        Optional<Review>  optionalReviewToUpdate = reviewRepository.findById(id);
        if(optionalReviewToUpdate.isPresent()){
            Review reviewToUpdate = optionalReviewToUpdate.get();
            reviewToUpdate.setId(id);
            reviewToUpdate.setRate(review.getRate());
            reviewToUpdate.setReviewer(review.getReviewer());
            return reviewRepository.save(reviewToUpdate);
        }
        return review;
    }

    @Override
    public Boolean delete(Long id)
    {
        reviewRepository.deleteById(id);
        return null;
    }
}
