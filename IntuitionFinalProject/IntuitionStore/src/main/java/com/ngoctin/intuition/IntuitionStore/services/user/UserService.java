package com.ngoctin.intuition.IntuitionStore.services.user;

import com.ngoctin.intuition.IntuitionStore.entities.User.Address;
import com.ngoctin.intuition.IntuitionStore.entities.User.User;
import com.ngoctin.intuition.IntuitionStore.models.user.AddressInfoUpdateRequest;
import com.ngoctin.intuition.IntuitionStore.models.user.AddressInforUpdateResponse;
import com.ngoctin.intuition.IntuitionStore.models.user.PrincipalResponse;
import com.ngoctin.intuition.IntuitionStore.models.user.UserInfoUpdateRequest;

import java.util.List;

public interface UserService {
    boolean createUser(User user);
    PrincipalResponse findUserByUsername(String username);
    UserInfoUpdateRequest findUserByUserNameToUpdate(String username);
    boolean updateUser(String username, UserInfoUpdateRequest user);
    List<Address> getAddressByUserID(int userID);
    boolean removeAddressByUserID(int userID, int addressID);
    Address addNewAddress(String addressValue);
    boolean addNewUserAddress(int userID, int addressID);
}
