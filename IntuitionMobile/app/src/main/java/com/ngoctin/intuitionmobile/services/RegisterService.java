package com.ngoctin.intuitionmobile.services;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.ngoctin.intuitionmobile.apis.RegisterAPI;
import com.ngoctin.intuitionmobile.models.RegisterUserRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterService {

    public static void register(RegisterUserRequest registerUserRequest,String message ,Context context){
        RegisterAPI.registerApi.register(registerUserRequest)
                .enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        System.out.println(response);
                        if(response.code() == 200){
                            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(context, "Register User Failed !", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });
    }
    public static int validate(RegisterUserRequest registerUserRequest, String confirmPassword){
        if(!registerUserRequest.getUsername()
                .matches("^[a-zA-Z]*([._](?![._])|[a-zA-Z0-9]){3,12}[a-zA-Z0-9]$")){
            // ngoctin.1908
            return 1;
        }
        if(!registerUserRequest.getPassword().
                matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")){
            // 123456Tin@
            return 2;
        }
        if(!registerUserRequest.getPassword().equals(confirmPassword)){
            return 3;
        }
        if(!registerUserRequest.getFullname().matches("[a-zA-Z\\s]*")
            || registerUserRequest.getFullname().length() == 0
        ){
            return 4;
        }
        if(!registerUserRequest.getPhoneNumber().matches("^\\d{10,12}$")){
            return 5;
        }
        return 0;
    }

}
