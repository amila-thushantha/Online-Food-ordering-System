package com.amila.Service;

import com.amila.config.JwtProvider;
import com.amila.model.User;
import com.amila.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {

       String email = jwtProvider.getEmailFromJwtToken(jwt);

       User user = userRepository.findByEmail(email);
       return user;
    }

    @Override
    public User FindUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("User not found");
        }
            return user;
        }
    }
