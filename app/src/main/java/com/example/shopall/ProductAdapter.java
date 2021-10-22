     package com.example.shopall;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(@NonNull Context context, int resource, List<Product> object) {
        super(context, resource, object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.list_item, parent, false);
        }

        Product currentProduct = getItem(position);

        ImageView imageView = (ImageView)convertView.findViewById(R.id.image);
        TextView nameTextView = (TextView)convertView.findViewById(R.id.name);
        TextView desTextView  = (TextView)convertView.findViewById(R.id.description);
        TextView rating = (TextView)convertView.findViewById(R.id.rating);
        TextView sp = (TextView)convertView.findViewById(R.id.sellingPrice);
        TextView mrp = (TextView)convertView.findViewById(R.id.mrp);
//
//        Glide.with(imageView.getContext())
//                .load(currentProduct.getPhotoUrl())
//                .into(imageView);

        Glide.with(getContext())
                .load(currentProduct.getPhotoUrl())
                .asBitmap()
                .centerCrop()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        imageView.setImageBitmap(resource);

                    }
                });

        nameTextView.setText(currentProduct.getName());
        desTextView.setText(currentProduct.getDescription());
        rating.setText(String.valueOf(currentProduct.getRating()));
        sp.setText(String.valueOf(currentProduct.getSellingPrice()));
        mrp.setText(String.valueOf(currentProduct.getMrp()));
        mrp.setPaintFlags(mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);




        return convertView;

    }
}
