package com.uc.utsprogtech;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uc.utsprogtech.model.SaveData;
import com.uc.utsprogtech.model.User;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

//    public static final String EXTRA_DETAIL = "extra";
//    User user;

    ArrayList<User>mContacts = SaveData.saveList;

    TextView detail_name,detail_age,detail_address;
    ImageView btnedit, btndelete;
    Toolbar toolbardetail;
    String nama,umur,alamat;
    int ind;
    Intent intent;

    final LoadingDialog loadingDialog =  new LoadingDialog(DetailActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        if(getIntent().getParcelableExtra(EXTRA_DETAIL)!=null){
//            user = getIntent().getParcelableExtra(EXTRA_DETAIL);
//        }

        intent = getIntent();
        ind = intent.getIntExtra("dataUser",0);

        detail_name = findViewById(R.id.detail_name);
        detail_age = findViewById(R.id.detail_age);
        detail_address = findViewById(R.id.detail_address);
        btnedit = findViewById(R.id.btnedit);
        btndelete = findViewById(R.id.btndelete);
        toolbardetail = findViewById(R.id.toolbardetail);

        nama = mContacts.get(ind).getName();
        umur = mContacts.get(ind).getAge();
        alamat = mContacts.get(ind).getAddress();

        setuptoolbar();
        setupTextView();
        setupDeleteUser();
        setupEditUser();

    }

    private void setupEditUser(){
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(DetailActivity.this,AddUserActivity.class);
                intent.putExtra("mcontacts","value");
                intent.putExtra("position",ind);
                startActivity(intent);
            }
        });
    }

    private void setupDeleteUser(){
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:

                                mContacts.remove(ind);
                                Log.d("test", String.valueOf(ind));
                                Toast.makeText(DetailActivity.this,"User deleted successfuly",Toast.LENGTH_SHORT).show();

                                final Intent intent = new Intent(DetailActivity.this,MainActivity.class);

                                loadingDialog.startLoadingDialog();
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        loadingDialog.dismissDialog();
                                        startActivity(intent);
                                        finish();
                                    }
                                },5000);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                dialog.dismiss();

                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                builder.setMessage("Are you sure to delete " + nama + " data?")
                        .setIcon(R.drawable.button_enabled)
                        .setTitle("Confirmation")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

            }
        });
    }

    private void setupTextView(){
        detail_name.setText(nama);
        detail_age.setText(umur.concat(" years old"));
        detail_address.setText(alamat);
    }

    private void setuptoolbar(){
        toolbardetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
