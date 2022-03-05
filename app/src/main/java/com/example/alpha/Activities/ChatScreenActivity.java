package com.example.alpha.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        msgLst = new ArrayList<>();
        msgLst.add(new DataPojo("Welcome to PXL Enterprise. \n * Press 1 to start the KYC procedure. \n" +
                " * Press 2 to enter scratch card code.", Constants.RECEIVE));

        initializeActions();
    }

    private void initializeActions(){

        etTypeMsg = findViewById(R.id.type_msg);

        ImageButton btnSend = findViewById(R.id.send_btn);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ChatScreenAdapter(msgLst);
        recyclerView.setAdapter(adapter);

        btnSend.setOnClickListener(v -> btnSendAction());

    }

    @SuppressLint("NotifyDataSetChanged")
    private void btnSendAction() {

        String str = etTypeMsg.getText().toString().trim();
        if(!str.isEmpty()) {
            // adding user response in the arraylist

            if(str.equals("1")){
                msgLst.add(new DataPojo(str, Constants.SEND));
                msgLst.add(new DataPojo("User pressed 1", Constants.RECEIVE));

            } else if(str.equals("2")){
                msgLst.add(new DataPojo(str, Constants.SEND));
                msgLst.add(new DataPojo("User pressed 2", Constants.RECEIVE));

            } else {
                Toast.makeText(getApplicationContext(),"Please enter a valid response",Toast.LENGTH_LONG).show();
            }

            // refreshing the recycler view
            recyclerView.post(() -> adapter.notifyDataSetChanged());

            // making editText ready for next msg from the user
            etTypeMsg.setText("");

        } else {
            Toast.makeText(getApplicationContext(),"Please enter a valid response",Toast.LENGTH_LONG).show();
        }
    }
}