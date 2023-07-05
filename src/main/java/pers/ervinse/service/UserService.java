package pers.ervinse.service;

import pers.ervinse.domain.Address;
import pers.ervinse.domain.User;
import pers.ervinse.utils.ApiResponse;

import java.util.List;

public interface UserService {

    ApiResponse login(User user);

    int register(User user);

    User getUserInfo(String name);
    ApiResponse addUserLocation(Address address);
    List<Address> getUserLocation();
    ApiResponse updateUserLocation(Address address);
}
