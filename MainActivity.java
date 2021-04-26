package com.example.addressbook;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.addressbook.AddAddressService.AddressBinder;

public class MainActivity extends AppCompatActivity {
    private static ArrayAdapter<String> arrayAdapter;
    private static String addition;

    static EditText inputAddress;
    ListView addressList;
    Button addAddress;
    AddAddressService AddressService;
    String fname, lname,pNumber;
    //static Address temp;
   // ArrayList<Address> temp = new ArrayList<>();
    private static boolean isBound;
    private static boolean adding = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputAddress = (EditText) findViewById(R.id.inputText);
        addAddress = (Button) findViewById(R.id.addButton);
        addressList = (ListView) findViewById(R.id.addressList);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        addressList.setAdapter(arrayAdapter);
        if (adding) {
            Bundle extras = getIntent().getExtras();
            //temp = extras.getParcelable("Address");
            fname = extras.getString("fname");
            lname = extras.getString("lname");
            pNumber = extras.getString("pNumber");
            addition = fname + ", " + lname + ", " + pNumber;
            onButtonClick();
        }

        //opens AddingPage
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddingPage.class);
                startActivity(i);
            }
        });

        //constant reading field and search engine
        inputAddress.addTextChangedListener(new aTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                arrayAdapter.getFilter().filter(s);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public static void onButtonClick() {

            AddAddressService.addAddress(addition);
            arrayAdapter.clear();
            arrayAdapter.addAll(AddAddressService.getStorage());
            //inputAddress.setText("");

    }
    private final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AddressBinder binder = (AddressBinder) service;
            AddressService = binder.getService();
            isBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(MainActivity.this, AddAddressService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(isBound) {
            unbindService(mConnection);
            isBound = false;
        }
    }
    public static void setAdding(boolean b){
        adding = b;
    }

   /* public static void onButtonClick() {
            AddAddressService.addAddress(temp);
            arrayAdapter.clear();
            arrayAdapter.addAll(AddAddressService.getStorage());
    }*/


}