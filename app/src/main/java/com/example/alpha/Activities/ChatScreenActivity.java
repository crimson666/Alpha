package com.example.alpha.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.alpha.Adapters.ChatScreenAdapter;
import com.example.alpha.Constants;
import com.example.alpha.Pojo.DataPojo;
import com.example.alpha.R;

import java.util.ArrayList;
import java.util.List;

public class ChatScreenActivity extends AppCompatActivity {

    private List<DataPojo> msgLst;
    private EditText etTypeMsg;
    private RecyclerView recyclerView;
    private ChatScreenAdapter adapter;
    Boolean isKYC, isScratchCard;

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        msgLst = new ArrayList<>();
        msgLst.add(new DataPojo("Welcome to PXL Enterprise Family! " +
                "Please choose from the following option to move forward:\n" +
                " * Type 1 to start the KYC procedure. \n" +
                " * Type 2 to enter scratch card code.", Constants.RECEIVE));

        initializeActions();
    }

    private void initializeActions() {

        isKYC = false;
        isScratchCard = false;

        etTypeMsg = findViewById(R.id.type_msg);

        ImageButton btnSend = findViewById(R.id.send_btn);
        btnSend.setOnClickListener(v -> btnSendAction());

        ImageButton btnAttch = findViewById(R.id.attach_btn);
        btnAttch.setOnClickListener(v -> btnAttachAction());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ChatScreenAdapter(msgLst);
        recyclerView.setAdapter(adapter);


    }

    @SuppressLint("NotifyDataSetChanged")
    private void btnSendAction() {

        String str = etTypeMsg.getText().toString().trim();
        if (!str.isEmpty()) {
            // adding user response in the arraylist

            if (!isKYC && !isScratchCard) {
                if (str.equals("1")) {
                    msgLst.add(new DataPojo(str, Constants.SEND));
                    msgLst.add(new DataPojo("Your KYC is not submitted. \n" +
                            "Register with us by providing your name and PAN / Adhaar image. \n\n" +
                            "Please enter your name", Constants.RECEIVE));

                    isKYC = true;

                } else if (str.equals("2")) {
                    msgLst.add(new DataPojo(str, Constants.SEND));
                    msgLst.add(new DataPojo("User pressed 2", Constants.RECEIVE));

                    isScratchCard = true;

                } else {
                    Toast.makeText(getApplicationContext(), "Please enter a valid response", Toast.LENGTH_LONG).show();
                }
            } else if (isKYC) {
                msgLst.add(new DataPojo(str, Constants.SEND));
                msgLst.add(new DataPojo("Welcome " + str + "\n" +
                        "Please upload your PAN / Adhaar image", Constants.RECEIVE));

            }

            // refreshing the recycler view
            recyclerView.post(() -> adapter.notifyDataSetChanged());

            // making editText ready for next msg from the user
            etTypeMsg.setText("");

        }
    }


    private void btnAttachAction() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
            }
            else {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            DataPojo dataPojo = new DataPojo();
            dataPojo.setImgBitmap(photo);
            dataPojo.setReceiveOrSend(Constants.SEND);

            msgLst.add(dataPojo);

            recyclerView.post(() -> adapter.notifyDataSetChanged());
        }
    }

}