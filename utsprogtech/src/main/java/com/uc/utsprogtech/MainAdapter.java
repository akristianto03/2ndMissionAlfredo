package com.uc.utsprogtech;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uc.utsprogtech.model.User;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private ArrayList<User> mContacts;



    public MainAdapter(ArrayList<User> mcontacts) {
        mContacts = mcontacts;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mname;
        public TextView mage;
        public TextView maddress;

        public ViewHolder(View itemView){
            super (itemView);

            mname = itemView.findViewById(R.id.card_name);
            mage = itemView.findViewById(R.id.card_age);
            maddress = itemView.findViewById(R.id.card_address);

        }
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_user, parent,false);
        ViewHolder evh = new ViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        User contactitem = mContacts.get(position);

        holder.mname.setText(contactitem.getName());
        holder.mage.setText(contactitem.getAge().concat(" years old"));
        holder.maddress.setText(contactitem.getAddress());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }


}
