package pers.ervinse.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import pers.ervinse.domain.Address;
import pers.ervinse.domain.Photo;
import pers.ervinse.domain.User;
import pers.ervinse.utils.ApiResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

    ApiResponse login(User user);

    int register(User user);

    User getUserInfo(String name);

    ApiResponse addUserLocation(Address address);

    List<Address> getUserLocation();

    ApiResponse updateUserLocation(Address address);

    ApiResponse addUserPhoto(HttpServletRequest request, HttpServletResponse response);

    Photo getUserHead();

    ApiResponse unsubscribeAccount();
}
