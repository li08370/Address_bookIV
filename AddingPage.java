package com.example.addressbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddingPage extends AppCompatActivity {
    Button done;
    EditText finput, linput, pinput, addressinput;
    AddAddressService AddressService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_page);
        done = (Button) findViewById(R.id.doneButton);
        finput = (EditText) findViewById(R.id.fnameinput);
        linput = (EditText) findViewById(R.id.lnameinput);
        pinput = (EditText) findViewById(R.id.pNumber);
        MainActivity.setAdding(true);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("fname", finput.getText().toString());
                i.putExtra("lname", linput.getText().toString());
                i.putExtra("pNumber", pinput.getText().toString());
                startActivity(i);
            }
        });
/*
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("Address", new Address(finput.getText().toString(), linput.getText().toString(), pinput.getText().toString()));
                startActivity(i);
            }
        });*/
    }

}



