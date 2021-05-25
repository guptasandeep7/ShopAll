package com.example.shopall;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shopall.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;
import java.util.Date;

public class EditorActivity extends AppCompatActivity{

    private ImageButton  image;
    private EditText productName;
    private EditText desTextView;
    private EditText quantity;
    private EditText rating;
    private EditText sp;
    private EditText mrp;
    private Button uploadButton;
    private ProgressBar progressBar;
    Uri selectedPhoto;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;
    StorageReference storageRef;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            selectedPhoto = data.getData();
            Log.i("selected photo",selectedPhoto.toString());

        }
        else{
            Toast.makeText(getApplicationContext(),"101 request denied",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);




        firebaseStorage = FirebaseStorage.getInstance();
        storageRef = firebaseStorage.getReference().child("productPhotos");

        image = (ImageButton) findViewById(R.id.imageButton);
        productName = (EditText)findViewById(R.id.productName);
        desTextView = (EditText)findViewById(R.id.productDescription);
        quantity = (EditText)findViewById(R.id.productQuantity);
        sp = (EditText)findViewById(R.id.productSP);
        mrp = (EditText)findViewById(R.id.productMRP);
        uploadButton = (Button)findViewById(R.id.uploadButton);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("products");

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(i, "Complete action using"), 101);

            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setIndeterminate(false);
                Date date = Calendar.getInstance().getTime();
                final StorageReference photoRef = storageRef.child(selectedPhoto.getLastPathSegment());
                photoRef.putFile(selectedPhoto).addOnSuccessListener(
                        EditorActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                photoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String url = uri.toString();
                                        Product newProduct = new Product(date.toString(),
                                                productName.getText().toString(),
                                                url,
                                                desTextView.getText().toString(),
                                                5, Long.parseLong(sp.getText().toString()),
                                                Long.parseLong(mrp.getText().toString()),
                                                Long.parseLong(quantity.getText().toString()));

                                        DatabaseReference newDatabaseRef = databaseReference.child(date.toString());
                                        newDatabaseRef.setValue(newProduct);
                                        progressBar.setIndeterminate(true);
                                        finish();

                                    }
                                });
                            }
                        });

            }
        });







    }
}
