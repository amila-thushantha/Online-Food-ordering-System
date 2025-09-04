package com.amila.Service;

import com.amila.model.User;

public interface UserService {

    public User findUserByJwtToken(String jwt) throws Exception;

    public User FindUserByEmail(String email) throws Exception;
}
