package com.prof4you.app.services.impl;

import com.prof4you.app.entities.Prof;
import com.prof4you.app.entities.Subscription;
import com.prof4you.app.repositories.ProfRepository;
import com.prof4you.app.repositories.SubscriptionRepository;
import com.prof4you.app.services.api.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.aspectj.bridge.Version.getTime;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    private SubscriptionRepository subscriptionRepository;
    private ProfRepository profRepository;

    @Autowired
    SubscriptionServiceImpl (final SubscriptionRepository subscriptionRepository,
                             final ProfRepository profRepository){
        this.subscriptionRepository = subscriptionRepository;
        this.profRepository = profRepository;
    }

    @Override
    public Subscription create(Long profId, String subscriptionType) {
        Optional<Prof> optionalProf = profRepository.findById(profId);
        if (optionalProf.isPresent()){
            Prof prof = optionalProf.get();
            Subscription subscription =  new Subscription();
            Calendar calendar = Calendar.getInstance();
            Date start =calendar.getTime();
            subscription.setStartDate(start);
            switch (subscriptionType){
                case "YEAR":
                    calendar.add(Calendar.YEAR, 1);
                    subscription.setEndDate(calendar.getTime());
                    break;
                case  "HALF_YEAR":
                    calendar.add(Calendar.MARCH, 6);
                    subscription.setEndDate(calendar.getTime());
                    break;
                default: // QUarter
                    calendar.add(Calendar.MARCH, 3);
                    subscription.setEndDate(calendar.getTime());
            }
            prof.setSubscription(subscription);
            profRepository.save(prof);
            prof = profRepository.getById(profId);
            return subscriptionRepository.save(prof.getSubscription());
        }
        return  null;
    }

    @Override
    public Optional<Subscription> findSubscriptionById(Long id) {
        return subscriptionRepository.findById(id);
    }

    @Override
    public Subscription update(Subscription subscription, Long id) {
        Optional<Subscription>  optionSubscriptionToUpdate = subscriptionRepository.findById(id);
        if(optionSubscriptionToUpdate.isPresent()){
            Subscription subscriptionToUpdate = optionSubscriptionToUpdate.get();
            subscriptionToUpdate.setEndDate(subscription.getEndDate());
            subscriptionToUpdate.setStartDate(subscriptionToUpdate.getStartDate());
            subscriptionToUpdate.setId(id);
            return  subscriptionRepository.save(subscriptionToUpdate);
        }
        return subscription;
    }

    @Override
    public Boolean delete(Long id) {
        subscriptionRepository.deleteById(id);
        return true;
    }
}
