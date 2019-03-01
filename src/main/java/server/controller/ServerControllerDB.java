package server.controller;

import server.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.repository.UserRepository;
@RestController
public class ServerControllerDB {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/users")
    public Page<User> getQuestions(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

}
