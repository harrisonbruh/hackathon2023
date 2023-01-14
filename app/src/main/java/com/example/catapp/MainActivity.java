package com.example.catapp;

import androidx.appcompat.app.AppCompatActivity;
import android.bluetooth.*;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    BluetoothSocket phone;
    BluetoothDevice catBot;
    BluetoothAdapter adapter; //needed to find catbot
    BluetoothServerSocket serverSocket; // needed to set up BluetoothSocket phone

    //Android App Lifecycle Methods: onCreate, onStart, onResume, onPause, onStop, onDestroy
    //https://developer.android.com/reference/android/app/Activity.html#ActivityLifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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