package rs.hybridit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import rs.hybridit.service.UserService;
import rs.hybridit.serviceImpl.UserServiceImpl;

@RestController
public class UserController {

    @Autowired
    UserService userService;
}
