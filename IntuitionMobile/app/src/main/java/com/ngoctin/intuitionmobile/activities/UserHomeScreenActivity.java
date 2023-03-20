package com.ngoctin.intuitionmobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.fragments.AllFragment;
import com.ngoctin.intuitionmobile.fragments.JeanFragment;
import com.ngoctin.intuitionmobile.fragments.ShirtFragment;
import com.ngoctin.intuitionmobile.services.ProductService;
import com.ngoctin.intuitionmobile.services.UtilService;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

public class UserHomeScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_screen);
        Button forwardMenuButton = this.findViewById(R.id.btnForwardMenu);
        Button allFragmentButton = this.findViewById(R.id.forwardAllFragment);
        Button TShirtFragmentButton = this.findViewById(R.id.forwardTshirtFragment);
        Button JeanFragmentButton = this.findViewById(R.id.forwardJeansFragment);
        Button btnSearchProduct = this.findViewById(R.id.btnSearchProduct);
        replaceFragment(new AllFragment());

        EditText edtSearchProductValue = this.findViewById(R.id.edtSeacrchProduct);
        btnSearchProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchValue = edtSearchProductValue.getText().toString();

                System.out.println("UserHomeScreenActivity - create : " + searchValue);
                replaceFragment(new AllFragment(searchValue));
            }
        });


        allFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new AllFragment());
            }
        });
        TShirtFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new ShirtFragment());
            }
        });
        JeanFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new JeanFragment());
            }
        });
        forwardMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHomeScreenActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,fragment);
        fragmentTransaction.commit();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Button forwardMenuButton = this.findViewById(R.id.btnForwardMenu);
        Button allFragmentButton = this.findViewById(R.id.forwardAllFragment);
        Button TShirtFragmentButton = this.findViewById(R.id.forwardTshirtFragment);
        Button JeanFragmentButton = this.findViewById(R.id.forwardJeansFragment);
        Button btnSearchProduct = this.findViewById(R.id.btnSearchProduct);
        replaceFragment(new AllFragment());
        EditText edtSearchProductValue = this.findViewById(R.id.edtSeacrchProduct);
        btnSearchProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchValue = edtSearchProductValue.getText().toString();
                replaceFragment(new AllFragment(searchValue));
            }
        });


        allFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new AllFragment());
            }
        });
        TShirtFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new ShirtFragment());
            }
        });
        JeanFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new JeanFragment());
            }
        });
        forwardMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHomeScreenActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        });
    }


}