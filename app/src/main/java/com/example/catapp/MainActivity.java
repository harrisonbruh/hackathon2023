package com.example.catapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.*;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ImageButton;
import android.os.ParcelUuid;


import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {
    int[] movementToggle = new int[5]; //Represents state of movement, in order: up, down, left, right, laser
    // 0 is toggled off, 1 is toggled on
    BluetoothSocket phone;
    BluetoothDevice catBot;
    BluetoothAdapter adapter; //needed to find catbot
    BluetoothServerSocket serverSocket; // needed to set up BluetoothSocket phone
    BluetoothManager manager;
    OutputStream outputStream;

    //Initialize all image buttons
    ImageButton laButton;

    ImageButton rButton;
    ImageButton lButton;
    ImageButton uButton;
    ImageButton dButton;

    //Initialize meow media files
    MediaPlayer meow1;
    MediaPlayer funnyMeow;
    MediaPlayer somberMeow;
    MediaPlayer happyMeow;
    MediaPlayer meow2;

    MediaPlayer[] media = new MediaPlayer[5];

    //Android App Lifecycle Methods: onCreate, onStart, onResume, onPause, onStop, onDestroy
    //https://developer.android.com/reference/android/app/Activity.html#ActivityLifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meow1 = MediaPlayer.create(this, R.raw.catmeow);
        media[0]=meow1;

        funnyMeow = MediaPlayer.create(this, R.raw.funnymeow);
        media[1]=funnyMeow;

        somberMeow = MediaPlayer.create(this, R.raw.sombermeow);
        media[2]=somberMeow;

        happyMeow = MediaPlayer.create(this, R.raw.happymeow);
        media[3]=happyMeow;

        happyMeow = MediaPlayer.create(this, R.raw.meow2);
        media[4]=meow2;

        laButton = findViewById(R.id.laserButton);
        laButton.setImageResource(R.drawable.laser);

        rButton = findViewById(R.id.rightButton);
        rButton.setImageResource(R.drawable.rightarrow);

        dButton = findViewById(R.id.downButton);
        dButton.setImageResource(R.drawable.downarrow);

        lButton = findViewById(R.id.leftButton);
        lButton.setImageResource(R.drawable.leftarrow);

        uButton = findViewById(R.id.upButton);
        uButton.setImageResource(R.drawable.uparrow);



        //unsure on casting, assuming this is a subclass of Context
        manager = (BluetoothManager) this.getSystemService(BLUETOOTH_SERVICE);

        //get BluetoothAdapter from BluetoothManager
        adapter = manager.getAdapter();




        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 0);
            // DONE: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //Set<BluetoothDevice> devices = adapter.getBondedDevices();




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
        movementToggle[4]=0;

        laButton.setImageResource(R.drawable.laser);

        if (movementToggle[0]==1) {
            movementToggle[0] = 0; //If it is moving forward, stop it
            uButton.setImageResource(R.drawable.uparrow);
        }
        else if (movementToggle[0]==0) {
            movementToggle[1] = 0;
            movementToggle[0] = 1;
            uButton.setImageResource(R.drawable.eviluparrow);
            dButton.setImageResource(R.drawable.downarrow);

        }
        //Turns off backwards movement, turns on forwards movement
    }

    public void downClick(View view) {
        movementToggle[4]=0;
        laButton.setImageResource(R.drawable.laser);

        if (movementToggle[1]==1){
            movementToggle[1]=0; //If it is moving backwards, stop it.
            dButton.setImageResource(R.drawable.downarrow);

        }
        else if (movementToggle[1]==0) {
            movementToggle[0]=0;
            movementToggle[1]=1;
            dButton.setImageResource(R.drawable.evildownarrow);
            uButton.setImageResource(R.drawable.uparrow);
        }
        //Turns off forwards movement, turns on backwards movement;
    }

    public void leftClick(View view) {
        movementToggle[4]=0;
        laButton.setImageResource(R.drawable.laser);

        if (movementToggle[2]==1) {
            movementToggle[2]=0; //If it is turning left, stop it.
            lButton.setImageResource(R.drawable.leftarrow);

        }
        else if (movementToggle[2]==0) {
            movementToggle[3] = 0;
            movementToggle[2] = 1;
            lButton.setImageResource(R.drawable.evilleftarrow);
            rButton.setImageResource(R.drawable.rightarrow);
        }
        //Turns off right turning movement, turns on left turning;
    }

    public void rightClick(View view) {
        movementToggle[4]=0;
        laButton.setImageResource(R.drawable.laser);

        if (movementToggle[3]==1) {
            movementToggle[3] = 0;
            rButton.setImageResource(R.drawable.rightarrow);//If it is turning right, stop it.
        }
        else if (movementToggle[3]==0) {
            movementToggle[2] = 0;
            movementToggle[3] = 1;
            rButton.setImageResource(R.drawable.evilrightarrow);
            lButton.setImageResource(R.drawable.leftarrow);
        }
        //Turns off left turning movement, turns on right turning;
    }

    public void meowClick(View view) {
        //Plays a random media file for the meowing
        media[(int)(Math.random()*media.length)].start();
    }

    public void connectClick(View view) {


        //unsure on casting, assuming this is a subclass of Context
        manager = (BluetoothManager) this.getSystemService(BLUETOOTH_SERVICE);

        //get BluetoothAdapter from BluetoothManager
        adapter = manager.getAdapter();


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 0);
            // DONE: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        //Address of Raspberry Pi provided by Ben
        catBot = adapter.getRemoteDevice("B8:27:EB:85:6E:2F");
        ParcelUuid[] parcelUuid= catBot.getUuids();
        try {
            phone = catBot.createInsecureRfcommSocketToServiceRecord(UUID.fromString("1e0ca4ea-299d-4335-93eb-27fcfe7fa848"));
            phone.connect();

            outputStream = phone.getOutputStream();

        } catch (IOException e) {
            Log.e("error", "connection failed", e);
            //throw new RuntimeException(e);
        }


        /*Set<BluetoothDevice> devices = adapter.getBondedDevices();
        for (BluetoothDevice device : devices) {
            Log.i( "test",(device.getName()+"\t"+device.getBondState()+"\t"+device.getType()+"\n") );
        }*/
        //Log.i("test", "test");
    }

    public void laserClick(View view) {
        if (movementToggle[4]==0) {
            movementToggle[0] = 0;
            movementToggle[1] = 0;
            movementToggle[2] = 0;
            movementToggle[3] = 0;
            movementToggle[4] = 1;
            laButton.setImageResource(R.drawable.evillaser);
            rButton.setImageResource(R.drawable.rightarrow);
            lButton.setImageResource(R.drawable.leftarrow);
            uButton.setImageResource(R.drawable.uparrow);
            dButton.setImageResource(R.drawable.downarrow);

            //Disables all toggle movement, enables laser mode
        }
        else{
            movementToggle[4]=0;
            laButton.setImageResource(R.drawable.laser);
        }

    }

    // Button Press methods

}