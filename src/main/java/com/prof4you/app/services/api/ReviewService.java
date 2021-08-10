package com.prof4you.app.services.api;

import com.prof4you.app.entities.Review;

import java.util.Optional;

public interface ReviewService {
    
    public Review create(Review review);

    public Optional<Review> findReviewById(Long id);

    public Review update(Review review, Long id);

    public Boolean delete(Long id);
}
