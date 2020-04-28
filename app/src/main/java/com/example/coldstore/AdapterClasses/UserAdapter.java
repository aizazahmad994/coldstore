package com.example.coldstore.AdapterClasses;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.example.coldstore.Activities.ViewActivityEntries;
import com.example.coldstore.R;
import com.example.coldstore.ModelClasses.UserInformation;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    Context context;
    ArrayList<UserInformation> user_info;
Bundle bundle ;
    public UserAdapter(Context c, ArrayList<UserInformation> p) {
        context = c;
        user_info = p;
        bundle = new Bundle();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_users, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.name.setText(user_info.get(position).getName());
        holder.phone.setText(user_info.get(position).getNumber());
        holder.address.setText(user_info.get(position).getAddress());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, position + " is clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ViewActivityEntries.class);
                bundle.putSerializable("value", user_info.get(position));
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return user_info.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, phone, address;
        Button btn;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.naam);
            phone = itemView.findViewById(R.id.mobail_no);
            address = itemView.findViewById(R.id.pata);
            btn = itemView.findViewById(R.id.button2);
        }
    }
}
