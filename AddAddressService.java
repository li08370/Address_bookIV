package com.example.addressbook;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.Collections;


public class AddAddressService extends Service {
    static ArrayList<String> storage1  = new ArrayList<>();
    static ArrayList<Address> storage  = new ArrayList<>();
    private final IBinder mBinder = new AddressBinder();

    public class AddressBinder extends Binder {
        AddAddressService getService() {
            return AddAddressService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    public static void addAddress(String address) {
            storage1.add(address);
            Collections.sort(storage1);
        }
    public static ArrayList<String> getStorage() {
        return storage1;
    }
        /* public static void addAddress(Address a) {
        storage.add(a);
    }*
     public static ArrayList<String> getStorage(){
         Address address;
         ArrayList<String> stringList = new ArrayList<>();
        for(int i = 0; i < storage.size(); i++) {
            address = storage.get(i);
            String temp = address.getFirst_name() + ", " +
                    address.getLast_name() + ", " +
                    address.getPhone_number();
             stringList.add(temp);
        }
        return stringList;
     }*/


}