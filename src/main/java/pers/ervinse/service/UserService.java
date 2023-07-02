package pers.ervinse.service;

import pers.ervinse.domain.User;

public interface UserService {

    boolean login(User user);

    int register(User user);

    String getDescription(String name);
}
