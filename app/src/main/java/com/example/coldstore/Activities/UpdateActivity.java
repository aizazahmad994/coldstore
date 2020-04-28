package com.example.coldstore.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.coldstore.ModelClasses.RecordInformation;
import com.example.coldstore.ModelClasses.UserInformation;
import com.example.coldstore.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateActivity extends AppCompatActivity {

    EditText numofsacks, typeofpotato, idbyclient, idbystore, racknum;
    String  numofsackstring, formofpotatostring, typeofpotatostring, idbyclientstring, idbystorestring, racknumstring ,DateString,contact_string,key_p;
    RadioButton frontbtn,midbtn,backbtn;
    Spinner roomnum;
    RadioButton temp;
    RadioGroup posofrack ;
    DatePicker tareekh;
    int posid;
    String posidstring;
    String roomnums;
    long count = 0;
    RecordInformation rec_info;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        String date_p,idbyclient_p,idbystore_p,numberofsacks_p,position_p,rackno_p,roomno_p,typeofpotato_p; //_p means the value is passed from previous activity
        rec_info = (RecordInformation) bundle.getSerializable("entry_edit");
        date_p = rec_info.getDate();
        idbyclient_p = rec_info.getIdByClient();
        idbystore_p = rec_info.getIdByStore();
        numberofsacks_p=rec_info.getNoOfSacks();
        position_p=rec_info.getPosition();
        rackno_p=rec_info.getRackNo();
        roomno_p=rec_info.getRoomNo();
        typeofpotato_p=rec_info.getTypeOfPotato();
        String[] splited = date_p.split("\\s+");
        key_p=rec_info.getKey();
         myRef2 = database.getReference("Entries");

        int month_in = Integer.valueOf(splited[0]);
        int date_in = Integer.valueOf(splited[2]);
        int year_in = Integer.valueOf(splited[4]);
int real_mon=month_in-1;
        numofsacks = (EditText)findViewById(R.id.sacksText);
        numofsacks.setText(numberofsacks_p);
        typeofpotato = (EditText)findViewById(R.id.PotatoTypeText);
        typeofpotato.setText(typeofpotato_p);
        idbyclient = (EditText)findViewById(R.id.IDbyclientText);
        idbyclient.setText(idbyclient_p);
        idbystore = (EditText)findViewById(R.id.IDbystoreText);
        idbystore.setText(idbystore_p);
        racknum = (EditText)findViewById(R.id.RacknoText);
        racknum.setText(rackno_p);
        posofrack = (RadioGroup)findViewById(R.id.pos);
        frontbtn = (RadioButton)findViewById(R.id.front);
        if (position_p.equals("Front"))
        frontbtn.setChecked(true);
        midbtn = (RadioButton)findViewById(R.id.mid);
        if (position_p.equals("Mid"))
            midbtn.setChecked(true);
        backbtn = (RadioButton)findViewById(R.id.back);
        if (position_p.equals("Back"))
            backbtn.setChecked(true);
        roomnum = (Spinner) findViewById(R.id.room);
        String[] items = new String[]{"1", "2", "3", "4", "5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        roomnum.setAdapter(adapter);
        if (roomno_p != null) {
            int spinnerPosition = adapter.getPosition(roomno_p);
            roomnum.setSelection(spinnerPosition);
        }
        tareekh = (DatePicker) findViewById(R.id.Tareekhpetareekh);
        tareekh.updateDate(year_in,real_mon,date_in);

    }

    public void add (View v)
    {


        numofsackstring = numofsacks.getText().toString();
        System.out.println(numofsackstring);

        typeofpotatostring = typeofpotato.getText().toString();
        System.out.println(typeofpotatostring);

        idbyclientstring = idbyclient.getText().toString();
        System.out.println(idbyclientstring);

        idbystorestring = idbystore.getText().toString();
        System.out.println(idbystorestring);

        racknumstring = racknum.getText().toString();
        System.out.println(racknumstring);

        posid = posofrack.getCheckedRadioButtonId();
        temp = (RadioButton)findViewById(posid);
        posidstring = temp.getText().toString();
        System.out.println(posidstring);

        roomnums = roomnum.getSelectedItem().toString();
        System.out.println(roomnums);


        DateString = tareekh.getDayOfMonth() + " / " + (tareekh.getMonth()+1)+ " / " + tareekh.getYear();
        System.out.println(DateString);

        contact_string = rec_info.getNumber();

            String id =rec_info.getKey();

            RecordInformation Record = new RecordInformation(id,formofpotatostring, DateString,idbyclientstring,idbystorestring,numofsackstring,posidstring,racknumstring,roomnums,typeofpotatostring,contact_string);
            myRef2.child(id).setValue(Record);


            Toast.makeText(this, "Record updated Successfully", Toast.LENGTH_SHORT).show();

         this.finish();
    }

}
