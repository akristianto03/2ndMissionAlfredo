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

import java.util.ArrayList;

public class AddUserActivity extends AppCompatActivity implements TextWatcher{

    TextInputLayout inputFName, inputAge, inputAddress;
    Button button_tambah;
    String fname, address, age;
    String con;
    int ind;
    Intent intent;
    Intent intent2;

    ArrayList<User> mContacts = SaveData.saveList;

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

        intent = getIntent();
        con = intent.getStringExtra("mcontacts");

        intent2 = getIntent();
        ind = intent2.getIntExtra("position",0);

        toolbar = findViewById(R.id.toolbaradd);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (con.equalsIgnoreCase("main")){
            toolbar.setTitle("Add User");
            button_tambah.setText("Add User");
        }else {
            toolbar.setTitle("Edit User");
            button_tambah.setText("Update Data");

            String nama,umur,alamat;
            nama = mContacts.get(ind).getName();
            umur = mContacts.get(ind).getAge();
            alamat = mContacts.get(ind).getAddress();

            inputFName.getEditText().setText(nama);
            inputAge.getEditText().setText(umur);
            inputAddress.getEditText().setText(alamat);
        }


        button_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (con.equalsIgnoreCase("main")){
                    User contact = new User(fname, address, age);
                    final Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
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
                }else{
                    mContacts.get(ind).setName(fname);
                    mContacts.get(ind).setAge(age);
                    mContacts.get(ind).setAddress(address);

                    final Intent intent = new Intent(AddUserActivity.this,MainActivity.class);
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

