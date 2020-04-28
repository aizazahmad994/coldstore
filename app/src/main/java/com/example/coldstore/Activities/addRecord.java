package com.example.coldstore.Activities;

import com.example.coldstore.R;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.coldstore.ModelClasses.RecordInformation;
import com.example.coldstore.ModelClasses.UserInformation;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class addRecord extends AppCompatActivity {

    EditText name, address, contact, numofsacks,  idbyclient, idbystore, racknum;
    String namestring, address_string, contact_string, numofsackstring, typeofpotatostring,formofpotatostring, idbyclientstring, idbystorestring, racknumstring, DateString;
    RadioGroup posofrack,alu;
    Spinner roomnum,typeofpotato;
    RadioButton temp,temp1;
    DatePicker tareekh;
    int posid,typeID;
    String posidstring;
    String roomnumstring;
    long count = 0;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("clients");

    DatabaseReference myRef2 = database.getReference("Entries");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        name = (EditText) findViewById(R.id.nameText);
        address = (EditText) findViewById(R.id.addressText);
        contact = (EditText) findViewById(R.id.contactText);
        numofsacks = (EditText) findViewById(R.id.sacksText);
        typeofpotato = (Spinner) findViewById(R.id.PotatoTypeText);
        String[] types = new String[]{"Asterix", "Mozika", "Sante", "Lady Rosetta"};
        ArrayAdapter<String> aaloo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, types);
        typeofpotato.setAdapter(aaloo);
        alu = (RadioGroup) findViewById(R.id.Alu_ki_shakal);

        idbyclient = (EditText) findViewById(R.id.IDbyclientText);
        idbystore = (EditText) findViewById(R.id.IDbystoreText);
        racknum = (EditText) findViewById(R.id.RacknoText);
        posofrack = (RadioGroup) findViewById(R.id.pos);
        roomnum = (Spinner) findViewById(R.id.room);
        String[] items = new String[]{"1", "2", "3", "4", "5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        roomnum.setAdapter(adapter);
        tareekh = (DatePicker) findViewById(R.id.Tareekhpetareekh);
        Button b1 = (Button) findViewById(R.id.recupdate);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                namestring = name.getText().toString();
//                System.out.println(namestring);
                address_string = address.getText().toString();
//                System.out.println(address_string);
                contact_string = contact.getText().toString();
//                System.out.println(contact_string);

                numofsackstring = numofsacks.getText().toString();
//                System.out.println(numofsackstring);

                typeofpotatostring = typeofpotato.getSelectedItem().toString();
//                System.out.println(typeofpotatostring);

                typeID = alu.getCheckedRadioButtonId();
                temp1 = findViewById(posid);
                if (temp1 != null)
                    formofpotatostring = temp1.getText().toString();

                idbyclientstring = idbyclient.getText().toString();
//                System.out.println(idbyclientstring);

                idbystorestring = idbystore.getText().toString();
//                System.out.println(idbystorestring);

                racknumstring = racknum.getText().toString();
//                System.out.println(racknumstring);

                posid = posofrack.getCheckedRadioButtonId();
                temp = findViewById(posid);
                if (temp != null)
                    posidstring = temp.getText().toString();
                //posid = temp.getId();
//                System.out.println(posidstring);

                roomnumstring = roomnum.getSelectedItem().toString();
//                System.out.println(roomnumstring);

                DateString = tareekh.getDayOfMonth() + " / " + (tareekh.getMonth() + 1) + " / " + tareekh.getYear();
                System.out.println(DateString);

                if (name.getText().toString().isEmpty() || address.getText().toString().isEmpty() || contact.getText().toString().isEmpty()
                        || numofsacks.getText().toString().isEmpty() || idbyclient.getText().toString().isEmpty() || idbystore.getText().toString().isEmpty()
                        || typeofpotato.getSelectedItem().equals("") || racknum.getText().toString().isEmpty() || posofrack.isSelected()
                        || roomnum.getSelectedItem().equals("")
                ) {
                    Toast.makeText(getApplicationContext(), "Fill all the fields first", Toast.LENGTH_SHORT).show();
                } else {
                    String id = myRef.push().getKey();

                    RecordInformation Record = new RecordInformation(id,formofpotatostring, DateString, idbyclientstring, idbystorestring, numofsackstring, posidstring, racknumstring, roomnumstring, typeofpotatostring, contact_string);
                    myRef2.child(id).setValue(Record);
                    UserInformation User = new UserInformation(namestring, address_string, contact_string);
                    myRef.child(contact_string).setValue(User);
                    Toast.makeText(getApplicationContext(), "Record Added Successfully", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    //public void add (View v)


}
