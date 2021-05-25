package com.example.shopall;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class DetailedActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Intent intent = getIntent();
       Product selectedProduct = (Product) intent.getSerializableExtra("PRODUCT");

        ImageView image = (ImageView)findViewById(R.id.dImage);
        TextView productName = (TextView)findViewById(R.id.dName);
        TextView rating = (TextView)findViewById(R.id.dRating);
        TextView quantity = (TextView)findViewById(R.id.dQuantity);
        TextView description = (TextView)findViewById(R.id.dDesc);
        TextView sp = (TextView)findViewById(R.id.dSP);
        TextView mrp = (TextView)findViewById(R.id.dMRP);
        mrp.setPaintFlags(mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        Button sendRatingButton = (Button)findViewById(R.id.dSendRating);
        Button addToCart = (Button)findViewById(R.id.addToCartButton);
        Button buy = (Button)findViewById(R.id.buyButton);

        Glide.with(image.getContext())
                .load(selectedProduct.getPhotoUrl())
                .into(image);
        productName.setText(selectedProduct.getName());
        quantity.setText(String.valueOf(selectedProduct.getQuantity()));
        description.setText(selectedProduct.getDescription());
        sp.setText(String.valueOf(selectedProduct.getSellingPrice()));
        mrp.setText(String.valueOf(selectedProduct.getMrp()));
        rating.setText(String.valueOf(selectedProduct.getRating()));

        sendRatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ratingBar.getRating()>0) {
                    selectedProduct.setRating((int) (selectedProduct.getRating() + ratingBar.getRating()) / 2);
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference().child("products").child(selectedProduct.getId());
                    databaseReference.child("rating").setValue(selectedProduct.getRating()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),"Thanks for Rating",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference().child("products").child(selectedProduct.getId());
                selectedProduct.setQuantity(selectedProduct.getQuantity()-1);
                databaseReference.child("quantity").setValue(selectedProduct.getQuantity()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                      Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"Order Placed Successfully",Snackbar.LENGTH_SHORT);
                      snackbar.show();

            }
        });



    }
});
    }
}
