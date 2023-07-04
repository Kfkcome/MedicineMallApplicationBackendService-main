package pers.ervinse.service;

import pers.ervinse.domain.User;

public interface UserService {

    boolean login(User user);

    int register(User user);

    User getUserInfo(String name);
}
