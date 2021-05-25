package com.example.shopall.ui.slideshow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.shopall.Login;
import com.example.shopall.MainActivity;
import com.example.shopall.R;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private EditText currentPin_textView;
    private EditText newPin_textView;
    private TextView invalidCurrentPin;
    private TextView invalidNewPin;
    private Button changePassword_Button;
    public static final String savedPassword = "com.example.shopall.passwordKey";

    public void writeSavedPassword(String psw)
    {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(savedPassword,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("PASSWORD", psw);
        editor.commit();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        currentPin_textView = (EditText)root.findViewById(R.id.currentPin_textView);
        newPin_textView = (EditText)root.findViewById(R.id.newPin_textView);
        invalidCurrentPin = (TextView)root.findViewById(R.id.invalidCurrentPassword);
        invalidNewPin = (TextView)root.findViewById(R.id.invalidNewPassword);

        changePassword_Button = (Button)root.findViewById(R.id.changePassword_button);

        changePassword_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPin_textView.getText().toString().equalsIgnoreCase(Login.getPassword())){

                    if(newPin_textView.getText().toString().length()>=4){

                        Login.setPassword(newPin_textView.getText().toString());
                        writeSavedPassword(newPin_textView.getText().toString());
                        Toast.makeText(getContext(),"PIN Changed Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else{
                            invalidNewPin.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    invalidCurrentPin.setVisibility(View.VISIBLE);
                    currentPin_textView.setText("");
                    newPin_textView.setText("");
                }
            }
        });













        return root;
    }
}