package com.example.shopall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    public CustomAdapter(Context context,ArrayList<Recycler> recyclerArrayList) {
        this.recyclerArrayList = recyclerArrayList;
        this.context = context;
    }

    ArrayList<Recycler> recyclerArrayList;
    Context context;
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            imageView = (ImageView)view.findViewById(R.id.recyclerImage);
            textView = (TextView) view.findViewById(R.id.recyclerText);
        }

        public TextView getTextView() {
            return textView;
        }

        public ImageView getImageView(){
            return imageView;
        }
    }



    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getImageView().setImageResource(recyclerArrayList.get(position).getImage());
        viewHolder.getTextView().setText(recyclerArrayList.get(position).getText());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return recyclerArrayList.size();
    }
}

