package com.example.catapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.*;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import java.util.*;


public class MainActivity extends AppCompatActivity {
    int[] movementToggle = new int[4]; //Represents state of movement, in order: up, down, left, right
    BluetoothSocket phone;
    BluetoothDevice catBot;
    BluetoothAdapter adapter; //needed to find catbot
    BluetoothServerSocket serverSocket; // needed to set up BluetoothSocket phone
    BluetoothManager manager;

    //Android App Lifecycle Methods: onCreate, onStart, onResume, onPause, onStop, onDestroy
    //https://developer.android.com/reference/android/app/Activity.html#ActivityLifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //unsure on casting, assuming this is a subclass of Context
        manager = (BluetoothManager) this.getSystemService(BLUETOOTH_SERVICE);

        //get BluetoothAdapter from BluetoothManager
        adapter = manager.getAdapter();



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 1);
            // DONE: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Set<BluetoothDevice> devices = adapter.getBondedDevices();
        




    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
    //Bluetooth setup methods
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    //Button Press methods
    public void upClick(View view) {
    }

    public void downClick(View view) {
    }

    public void leftClick(View view) {
    }

    public void rightClick(View view) {
    }

    public void meowClick(View view) {

    }

    // Button Press methods

}