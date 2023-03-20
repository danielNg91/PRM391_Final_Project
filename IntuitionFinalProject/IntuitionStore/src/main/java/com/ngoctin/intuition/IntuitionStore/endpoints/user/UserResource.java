package com.ngoctin.intuition.IntuitionStore.endpoints.user;


import com.ngoctin.intuition.IntuitionStore.entities.User.Address;
import com.ngoctin.intuition.IntuitionStore.entities.User.User;
import com.ngoctin.intuition.IntuitionStore.models.user.AddressInfoUpdateRequest;
import com.ngoctin.intuition.IntuitionStore.models.user.AddressInforUpdateResponse;
import com.ngoctin.intuition.IntuitionStore.models.user.PrincipalResponse;
import com.ngoctin.intuition.IntuitionStore.models.user.UserInfoUpdateRequest;
import com.ngoctin.intuition.IntuitionStore.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserResource {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User user){
        if(userService.createUser(user)){
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Create User Failed !");
    }

    @GetMapping("/user/getAuthenticatedUser")
    public ResponseEntity<?> getPrincipal(){
//        log.info("getPrincipal");
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        PrincipalResponse principalResponse = userService.findUserByUsername(username);
        if(principalResponse != null){
            return ResponseEntity.status(HttpStatus.OK).body(principalResponse);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authenticated Failed");
    }

    @GetMapping("/user/getUserInfo")
    public ResponseEntity<?> getUserInfo() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UserInfoUpdateRequest userInfoUpdateResponse = userService.findUserByUserNameToUpdate(username);
        if (userInfoUpdateResponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userInfoUpdateResponse);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authenticated Failed");
    }

    @PutMapping("/user/update/{username}")
    public ResponseEntity<?> updateUser(@PathVariable("username") String username, @RequestBody UserInfoUpdateRequest user) {
        boolean check = userService.updateUser(username, user);
        if (check) {
            return ResponseEntity.status(HttpStatus.OK).body("Update Successfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Update Failed!");
    }


    @GetMapping("user/getAddressesByUserID/{userId}")
    public ResponseEntity<?> getAddressByUserID(@PathVariable ("userId") int userID) {
        List<Address> addresses = userService.getAddressByUserID(userID);
        if (addresses != null && addresses.size() > 0) {
            return  ResponseEntity.status(HttpStatus.OK).body(addresses);
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empty !");
    }

    @PutMapping("/user/removeAddressByUserID/{userID}/{addressID}")
    public ResponseEntity<?> removeAddressByUserID(@PathVariable("userID") int userID,@PathVariable("addressID") int addressID) {
        log.info("UserID : {} - AddressID : {}",userID,addressID);
        if(userService.removeAddressByUserID(userID,addressID)) {
            return ResponseEntity.status(HttpStatus.OK).body("Remove Successfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Remove Failed!");
    }

    @PostMapping("user/addNewAddress")
    public ResponseEntity<?> addNewAddress(@RequestBody String addressValue) {
        Address address = userService.addNewAddress(addressValue);
        if(address != null) {
            return ResponseEntity.status(HttpStatus.OK).body(address);
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Create Address Failed!");
    }

    @PostMapping("user/setAddressForUser/{userID}/{addressID}")
    public ResponseEntity<?> addNewUserAddress(@PathVariable int userID, @PathVariable int addressID) {
        if(userService.addNewUserAddress(userID,addressID)) {
            return ResponseEntity.status(HttpStatus.OK).body("Add Address For User Successfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Add Address For User Failed");
    }
}
