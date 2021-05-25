package com.example.shopall.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopall.CustomAdapter;
import com.example.shopall.DetailedActivity;
import com.example.shopall.EditorActivity;
import com.example.shopall.Product;
import com.example.shopall.ProductAdapter;
import com.example.shopall.R;
import com.example.shopall.Recycler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements Serializable {



    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;


    ListView productListView;
    List<Product> productsList;
    ProductAdapter productAdapter;

    RecyclerView mRecyclerView;
    ArrayList<Recycler> recyclerList;
    RecyclerView mRecyclerView2;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //RecyclerView
//        mRecyclerView = (RecyclerView)root.findViewById(R.id.recyclerView);
//        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
//        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerList = new ArrayList<Recycler>();
//
//        recyclerList.add(new Recycler(R.drawable.bgimage,"first"));
//        recyclerList.add(new Recycler(R.drawable.bagimage,"Second"));
//
//        CustomAdapter customAdapter = new CustomAdapter(getContext(),recyclerList);
//        mRecyclerView.setAdapter(customAdapter);

//        //RecyclerView 2
//        mRecyclerView2 = (RecyclerView)root.findViewById(R.id.recyclerView2);
//        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
//        mRecyclerView2.setLayoutManager(layoutManager2);
//        mRecyclerView2.setItemAnimator(new DefaultItemAnimator());
//        CustomAdapter customAdapter2 = new CustomAdapter(getContext(),recyclerList);
//        mRecyclerView2.setAdapter(customAdapter2);


        //ListView
        productListView = (ListView)root.findViewById(R.id.list);
        productsList = new ArrayList<Product>();
        productAdapter = new ProductAdapter(getContext(),R.layout.list_item,productsList);
        productListView.setAdapter(productAdapter);

        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetailedActivity.class);
               Serializable s =  productsList.get(position);
                intent.putExtra("PRODUCT", s);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditorActivity.class);

                startActivity(intent);

            }
        });



// Write a message to the database
         firebaseDatabase = FirebaseDatabase.getInstance();
         databaseReference = firebaseDatabase.getReference().child("products");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Product addProduct = snapshot.getValue(Product.class);
                productAdapter.add(addProduct);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
             

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        return root;
    }







}