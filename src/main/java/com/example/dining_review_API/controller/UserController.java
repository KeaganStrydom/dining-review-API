package com.example.dining_review_API.controller;

import com.example.dining_review_API.model.User;
import com.example.dining_review_API.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    final UserRepository userRepository;
    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        this.validateUser(user);
        return this.userRepository.save(user);
    }

    @GetMapping("/{displayName}")
    public User getUser(@PathVariable String displayName) {
        validateDisplayName(displayName);

        Optional<User> optionalExistingUser = this.userRepository.findByDisplayName(displayName);
        if (!optionalExistingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return optionalExistingUser.get();
    }


    @PutMapping("/{displayName}")
    public User updateUser(@PathVariable String displayName, @RequestBody User user) {
        validateDisplayName(displayName);
        Optional<User> optionalExistingUser = this.userRepository.findByDisplayName(displayName);
        if (!optionalExistingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        User existingUser = optionalExistingUser.get();
        copyAcrossProperties(user, existingUser);
        return this.userRepository.save(existingUser);
    }


    private void copyAcrossProperties(User updated, User existing) {
        if (updated.getDisplayName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        if (!updated.getCity().isEmpty()) {
            existing.setCity(updated.getCity());
        }

        if (!updated.getState().isEmpty()) {
            existing.setState(updated.getState());
        }

        if (!updated.getZipcode().isEmpty()) {
            existing.setZipcode(updated.getZipcode());
        }

        if (!ObjectUtils.isEmpty(updated.getReviewsDairy())) {
            existing.setReviewsDairy(updated.getReviewsDairy());
        }

        if (!ObjectUtils.isEmpty(updated.getReviewsEggs())) {
            existing.setReviewsEggs(updated.getReviewsEggs());
        }

        if (!ObjectUtils.isEmpty(updated.getReviewsPeanut())) {
            existing.setReviewsPeanut(updated.getReviewsPeanut());
        }
    }


    private void validateUser(User user) {
        String displayName = user.getDisplayName();
        validateDisplayName(displayName);

        Optional<User> optionalExistingUser = this.userRepository.findByDisplayName(displayName);
        if (optionalExistingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    private void validateDisplayName(String displayName) {
        if (displayName == null || displayName.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
