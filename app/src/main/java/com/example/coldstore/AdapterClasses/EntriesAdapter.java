package com.example.coldstore.AdapterClasses;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.example.coldstore.Activities.UpdateActivity;
import com.example.coldstore.R;
import com.example.coldstore.ModelClasses.RecordInformation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.os.Bundle;
import android.widget.Toast;
import android.content.Intent;
import java.util.ArrayList;
public class EntriesAdapter extends RecyclerView.Adapter<EntriesAdapter.MyViewHolder> {
    Context Mycontext;
    Bundle bundle ;

    ArrayList<RecordInformation> Entries_list;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Entries");

    public EntriesAdapter(Context c, ArrayList<RecordInformation> p) {
        Mycontext = c;
        Entries_list = p;
        bundle = new Bundle();
    }

    @Override
    public EntriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(Mycontext).inflate(R.layout.cardview_entries, parent, false));
    }

    @Override
    public void onBindViewHolder(EntriesAdapter.MyViewHolder myViewHolder, final int position) {
        myViewHolder.date.setText(Entries_list.get(position).getDate());
        myViewHolder.IDByClient.setText(Entries_list.get(position).getIdByClient());
        myViewHolder.IDByStore.setText(Entries_list.get(position).getIdByStore());
        myViewHolder.NoOfSacks.setText(Entries_list.get(position).getNoOfSacks());
        myViewHolder.Position.setText(Entries_list.get(position).getPosition());
        myViewHolder.RackNo.setText(Entries_list.get(position).getRackNo());
        myViewHolder.RoomNo.setText(Entries_list.get(position).getRoomNo());
        myViewHolder.TypeOfPotato.setText(Entries_list.get(position).getTypeOfPotato());

        myViewHolder.btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(Mycontext)
                        .setTitle("Confirm Delete")
                        .setMessage("Do you really want to DELETE this entry?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                myRef.child(Entries_list.get(position).getKey()).removeValue();
                                Toast.makeText(Mycontext, "Entry Deleted", Toast.LENGTH_SHORT).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();


            }
        });
        myViewHolder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Mycontext,  " Edit screen is opening", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Mycontext, UpdateActivity.class);
                bundle.putSerializable("entry_edit", Entries_list.get(position));
                i.putExtras(bundle);
                Mycontext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Entries_list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, IDByClient,IDByStore, NoOfSacks,Position,RackNo,RoomNo,TypeOfPotato;
        Button btn_del,btn_edit;

        public MyViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.Date);
            IDByClient = itemView.findViewById(R.id.ID_by_client);
            IDByStore = itemView.findViewById(R.id.ID_by_store);
            NoOfSacks = itemView.findViewById(R.id.No_of_sacks);
            Position = itemView.findViewById(R.id.Position);
            RackNo = itemView.findViewById(R.id.Rack_no);
            RoomNo = itemView.findViewById(R.id.room_no);
            TypeOfPotato = itemView.findViewById(R.id.PotatoType);
            btn_edit= itemView.findViewById(R.id.Edit_Btn);
            btn_del= itemView.findViewById(R.id.Delete_Btn);
        }
    }
}
