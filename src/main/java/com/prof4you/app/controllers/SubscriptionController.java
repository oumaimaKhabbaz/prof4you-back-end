package com.prof4you.app.controllers;

import com.prof4you.app.entities.Subscription;
import com.prof4you.app.services.api.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/subscription")
@CrossOrigin("*")
public class SubscriptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionController.class);

    private SubscriptionService subscriptionService;

    @Autowired
    SubscriptionController( final SubscriptionService subscriptionService){
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("")
    public ResponseEntity createSubscription(@RequestParam("profId") Long profId, @RequestParam("subscriptionType") String subscriptionType){
        Subscription subscription = subscriptionService.create(profId, subscriptionType);
        if(subscription != null){
            return  new ResponseEntity(subscription, HttpStatus.OK);
        }
        return  new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity getSubscription(@PathVariable Long id) {
        final Optional<Subscription> subscription = subscriptionService.findSubscriptionById(id);
        if (subscription.isPresent()){
            new ResponseEntity(subscription.get(), HttpStatus.OK);
        }
        return new ResponseEntity("subscription with id= "+ id + " not found!", HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity updateSubscription(@RequestBody Subscription subscription, @PathVariable Long id){
        return  new ResponseEntity(subscriptionService.update(subscription, id), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteSubscription(@PathVariable Long id){
        return  new ResponseEntity(subscriptionService.delete(id), HttpStatus.OK);
    }
}
