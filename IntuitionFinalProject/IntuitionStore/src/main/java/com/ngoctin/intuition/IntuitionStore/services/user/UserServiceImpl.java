package com.ngoctin.intuition.IntuitionStore.services.user;

import com.ngoctin.intuition.IntuitionStore.entities.User.Address;
import com.ngoctin.intuition.IntuitionStore.entities.User.User;
import com.ngoctin.intuition.IntuitionStore.models.user.AddressInfoUpdateRequest;
import com.ngoctin.intuition.IntuitionStore.models.user.AddressInforUpdateResponse;
import com.ngoctin.intuition.IntuitionStore.models.user.PrincipalResponse;
import com.ngoctin.intuition.IntuitionStore.models.user.UserInfoUpdateRequest;
import com.ngoctin.intuition.IntuitionStore.repositories.AddressRespository;
import com.ngoctin.intuition.IntuitionStore.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final AddressRespository addressRespository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username);
            log.info("User : {}", user.toString());
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return null;
        }
    }

    @Override
    public boolean createUser(User user) {
        try {
            log.info(user.toString());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if(userRepository.createUser(user)) {
                return true;
            }
            return false;
        }catch (Exception exception){
            log.error(exception.getMessage());
            return false;
        }
    }

    @Override
    public PrincipalResponse findUserByUsername(String username) {
        try {
            User user = userRepository.findByUsername(username);
//            log.info("User : {}",user.toString());
            int id = Integer.parseInt(user.getId().toString());
            PrincipalResponse principalResponse
                    = new PrincipalResponse(id,user.getUsername(),user.getFullname(),
                    user.getPhoneNumber(),user.getAvatar(),user.getRank(),
                    user.getEmail(),user.getRole());
            return principalResponse;
        }catch (Exception exception){
            log.error("Exception : " + exception.getMessage());
            return null;
        }
    }

    @Override
    public UserInfoUpdateRequest findUserByUserNameToUpdate(String username) {
       try {
            User user = userRepository.findByUsername(username);
            UserInfoUpdateRequest userInfoUpdateResponse
                    = new UserInfoUpdateRequest(user.getId(),user.getUsername(), user.getFullname(),
                    user.getPhoneNumber(), user.getEmail());
            return userInfoUpdateResponse;
       } catch (Exception ex) {
           log.error("Exception: " + ex.getMessage());
           return null;
       }
    }

    @Override
    public boolean updateUser(String username, UserInfoUpdateRequest userInfoUpdateRequest) {
        try {
            User user = new User();
            user.setFullname(userInfoUpdateRequest.getFullname());
            user.setPhoneNumber(userInfoUpdateRequest.getPhoneNumber());
            user.setEmail(userInfoUpdateRequest.getEmail());
            return userRepository.updateUser(username,user);
        } catch (Exception ex) {
            log.error("Exception: " + ex.getMessage());
            return false;
        }
    }


    @Override
    public List<Address> getAddressByUserID(int userID) {
        return userRepository.getAddressByUserID(userID);
    }

    @Override
    public boolean removeAddressByUserID(int userID, int addressID) {
        return userRepository.removeAddressByUserID(userID,addressID);
    }

    @Override
    public Address addNewAddress(String addressValue) {
        Address address = new Address(addressValue);
        return addressRespository.save(address);
    }

    @Override
    public boolean addNewUserAddress(int userID, int addressID) {
        return userRepository.addNewUserAddress(userID,addressID);
    }
}
