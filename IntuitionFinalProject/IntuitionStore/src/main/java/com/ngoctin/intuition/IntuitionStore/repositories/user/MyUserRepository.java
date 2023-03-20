package com.ngoctin.intuition.IntuitionStore.repositories.user;

import com.ngoctin.intuition.IntuitionStore.entities.User.Address;
import com.ngoctin.intuition.IntuitionStore.entities.User.User;
import com.ngoctin.intuition.IntuitionStore.models.user.UserInfoUpdateRequest;

import java.util.List;

public interface MyUserRepository {
    User findByUsername(String username);
    boolean createUser(User user);
    boolean updateUser(String username, User user);
    List<Address> getAddressByUserID(int userID);
    boolean removeAddressByUserID(int userID, int addressID);
    Address addNewAddress(String addressValue);
    boolean addNewUserAddress(int userID, int addressID);

}
