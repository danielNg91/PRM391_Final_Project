package com.ngoctin.intuitionmobile.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.adapter.ProductRecyclerViewAdapter;
import com.ngoctin.intuitionmobile.models.AuthenticatedUser;
import com.ngoctin.intuitionmobile.services.ProductService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JeanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JeanFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public JeanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JeanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JeanFragment newInstance(String param1, String param2) {
        JeanFragment fragment = new JeanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_jean, container, false);
        ProductRecyclerViewAdapter adapter = new ProductRecyclerViewAdapter(container.getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(container.getContext(),2);
        RecyclerView productRecyclerView = view.findViewById(R.id.rvAllProducts);
        productRecyclerView.setLayoutManager(gridLayoutManager);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_store", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("authenticated_user","");
        AuthenticatedUser authenticatedUser = gson.fromJson(json,AuthenticatedUser.class);
        ProductService.getProducts(authenticatedUser.getJwt(),productRecyclerView,adapter,2,null);
        return view;
    }
}