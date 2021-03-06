package com.example.addressbook;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import java.util.ArrayList;
import java.util.Collections;


public class AddAddressService extends Service {
    static ArrayList<String> storage  = new ArrayList<>();
    private final IBinder mBinder = new AddressBinder();
    public AddAddressService() {
    }
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
            storage.add(address);
            Collections.sort(storage);
        }

     public static ArrayList<String> getStorage(){
        return storage;
     }

}