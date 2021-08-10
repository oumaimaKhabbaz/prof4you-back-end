package com.prof4you.app.services.api;

import com.prof4you.app.entities.Subscription;

import java.util.Optional;

public interface SubscriptionService {

    public Subscription create(Long profId, String subscriptionType);

    public Optional<Subscription> findSubscriptionById(Long id);

    public Subscription update(Subscription subscription, Long id);

    public Boolean delete(Long id);
}
