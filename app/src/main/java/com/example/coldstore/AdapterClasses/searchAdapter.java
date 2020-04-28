package com.example.coldstore.AdapterClasses;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.coldstore.Activities.UpdateActivity;
import com.example.coldstore.Activities.rec;

import com.example.coldstore.ModelClasses.RecordInformation;
import com.example.coldstore.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class searchAdapter extends RecyclerView.Adapter<searchAdapter.SearchViewHolder>{
    Context SearchContext;
    Bundle bundle ;
    ArrayList<RecordInformation> Entries_data;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference EntryRef = database.getReference("Entries");

    public searchAdapter(Context c, ArrayList<RecordInformation> p) {
        SearchContext = c;
        Entries_data = p;
        bundle = new Bundle();
    }

    @Override
    public searchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchViewHolder(LayoutInflater.from(SearchContext).inflate(R.layout.cardview_search, parent, false));
    }

    @Override
    public void onBindViewHolder(searchAdapter.SearchViewHolder mysearchViewHolder, final int position) {

        mysearchViewHolder.date.setText(Entries_data.get(position).getDate());
        mysearchViewHolder.IDByClient.setText(Entries_data.get(position).getIdByClient());
        mysearchViewHolder.IDByStore.setText(Entries_data.get(position).getIdByStore());
        mysearchViewHolder.NoOfSacks.setText(Entries_data.get(position).getNoOfSacks());
        mysearchViewHolder.Position.setText(Entries_data.get(position).getPosition());
        mysearchViewHolder.RackNo.setText(Entries_data.get(position).getRackNo());
        mysearchViewHolder.RoomNo.setText(Entries_data.get(position).getRoomNo());
        mysearchViewHolder.TypeOfPotato.setText(Entries_data.get(position).getTypeOfPotato());

        mysearchViewHolder.btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(SearchContext)
                        .setTitle("Confirm Delete")
                        .setMessage("Do you really want to DELETE this entry?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                EntryRef.child(Entries_data.get(position).getKey()).removeValue();
                                Toast.makeText(SearchContext, "Entry Deleted", Toast.LENGTH_SHORT).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();

            }
        });
        mysearchViewHolder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchContext,  " Edit screen is opening", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SearchContext, UpdateActivity.class);
                bundle.putSerializable("entry_edit", Entries_data.get(position));
                i.putExtras(bundle);
                SearchContext.startActivity(i);
            }
        });
        mysearchViewHolder.zyada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchContext,  " Showing more information", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SearchContext, rec.class);
                bundle.putSerializable("entry_edit", Entries_data.get(position));
                i.putExtras(bundle);
                SearchContext.startActivity(i);
            }
        });
    }

    public int getItemCount() {
        return Entries_data.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView name,phone,address, date, IDByClient,IDByStore, NoOfSacks,Position,RackNo,RoomNo,TypeOfPotato;
        Button btn_del,btn_edit,zyada;

        public SearchViewHolder(View itemView) {
            super(itemView);


            date = itemView.findViewById(R.id.date_s);
            IDByClient = itemView.findViewById(R.id.ID_by_client_s);
            IDByStore = itemView.findViewById(R.id.ID_by_store_s);
            NoOfSacks = itemView.findViewById(R.id.No_of_sacks_s);
            Position = itemView.findViewById(R.id.Position_s);
            RackNo = itemView.findViewById(R.id.Rack_no_s);
            RoomNo = itemView.findViewById(R.id.room_no_s);
            TypeOfPotato = itemView.findViewById(R.id.PotatoType_s);
            btn_edit= itemView.findViewById(R.id.Edit_Btn_s);
            btn_del= itemView.findViewById(R.id.Delete_Btn_s);
            zyada=itemView.findViewById(R.id.show_more_info);
        }
    }
}
