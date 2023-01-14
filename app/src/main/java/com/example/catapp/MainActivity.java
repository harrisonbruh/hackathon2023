package com.example.catapp;

import androidx.appcompat.app.AppCompatActivity;
import android.bluetooth.*;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    int[] movementToggle = new int[4]; //Represents state of movement, in order: up, down, left, right.
    // 0 is toggled off, 1 is toggled on
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
        if (movementToggle[0]==1) movementToggle[0]=0; //If it is moving forward, stop it.
        if (movementToggle[0]==0) movementToggle[1]=0; movementToggle[0]=1;
        //Turns off backwards movement, turns on forwards movement
    }

    public void downClick(View view) {
        if (movementToggle[1]==1) movementToggle[1]=0; //If it is moving backwards, stop it.
        if (movementToggle[1]==0) movementToggle[0]=0; movementToggle[1]=1;
        //Turns off forwards movement, turns on backwards movement;
    }

    public void leftClick(View view) {
        if (movementToggle[2]==1) movementToggle[2]=0; //If it is turning left, stop it.
        if (movementToggle[2]==0) movementToggle[3]=0; movementToggle[2]=1;
        //Turns off right turning movement, turns on left turning;
    }

    public void rightClick(View view) {
        if (movementToggle[3]==1) movementToggle[3]=0; //If it is turning right, stop it.
        if (movementToggle[3]==0) movementToggle[2]=0; movementToggle[3]=1;
        //Turns off left turning movement, turns on right turning;
    }

    public void meowClick(View view) {

    }

    public void connectClick(View view) {

    }

    // Button Press methods

}