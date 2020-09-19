package com.uc.utsprogtech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uc.utsprogtech.model.User;

import com.uc.utsprogtech.MainAdapter;
import com.uc.utsprogtech.model.SaveData;
import com.uc.utsprogtech.utils.ItemClickSupport;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    FloatingActionButton button_add;
    TextView datablank;
    ArrayList<User> mcontacts = SaveData.saveList;
    RecyclerView mrecyclerview;
    RecyclerView.LayoutManager mlayoutmanager;
    RecyclerView.Adapter madapter;
    User user;

    boolean doubleclickexit = false;

    @Override
    public void onBackPressed() {
        if (doubleclickexit) {
            super.onBackPressed();
            return;
        }

        this.doubleclickexit = true;
        Toast.makeText(this, "Click Again to Exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleclickexit=false;
            }
        }, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_add = findViewById(R.id.button_add);
        datablank = findViewById(R.id.blank);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                intent.putExtra("mcontacts","main");
                startActivity(intent);
            }
        });

        if(getIntent().getParcelableExtra("dataUser") != null){
            user = getIntent().getParcelableExtra("dataUser");
        }

        if(!mcontacts.isEmpty()){
            datablank.setVisibility(View.INVISIBLE);
        }

        mrecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        mlayoutmanager = new LinearLayoutManager(this);

        mrecyclerview.setHasFixedSize(true);
        madapter = new MainAdapter(mcontacts);
        mrecyclerview.setLayoutManager(mlayoutmanager);
        mrecyclerview.setAdapter(madapter);

        ItemClickSupport.addTo(mrecyclerview).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                mcontacts.get(position);
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("dataUser",position);
//                intent.putExtra(DetailActivity.EXTRA_DETAIL,mcontacts.get(position));
                startActivity(intent);
            }
        });
    }
}
