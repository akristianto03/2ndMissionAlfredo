package com.uc.utsprogtech;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.uc.utsprogtech.model.SaveData;
import com.uc.utsprogtech.model.User;

public class AddUserActivity extends AppCompatActivity implements TextWatcher{

    TextInputLayout inputFName, inputAge, inputAddress;
    Button button_tambah;
    String fname, address, age;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        inputFName = findViewById(R.id.input_fname);
        inputAge = findViewById(R.id.input_age);
        inputAddress = findViewById(R.id.input_address);
        button_tambah = findViewById(R.id.button_tambah);
        final LoadingDialog loadingDialog =  new LoadingDialog(AddUserActivity.this);

        inputFName.getEditText().addTextChangedListener(this);
        inputAge.getEditText().addTextChangedListener(this);
        inputAddress.getEditText().addTextChangedListener(this);

        toolbar = findViewById(R.id.toolbaradd);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        button_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User contact = new User(fname, address, age + " years old");
                final Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
                startActivity(intent);
                intent.putExtra("dataUser",contact);
                SaveData.saveList.add(contact);
                loadingDialog.startLoadingDialog();
                Handler handler =new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                        startActivity(intent);
                        finish();
                    }
                },5000);
            }
        });

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        fname = inputFName.getEditText().getText().toString().trim();
        age = inputAge.getEditText().getText().toString().trim();
        address = inputAddress.getEditText().getText().toString().trim();

        if (!fname.isEmpty() && !address.isEmpty() && !age.isEmpty()){
            button_tambah.setEnabled(true);
        }else{
            button_tambah.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public static class CardActivity {
    }
}

