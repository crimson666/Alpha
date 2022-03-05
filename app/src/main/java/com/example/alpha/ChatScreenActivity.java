package com.example.alpha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.alpha.Adapter.ChatScreenAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChatScreenActivity extends AppCompatActivity {

    private List<String> userMsgLst;
    private List<String> appMsgLst;
    private EditText etTypeMsg;
    private RecyclerView recyclerView;
    private ChatScreenAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        userMsgLst = new ArrayList<>();
        appMsgLst = new ArrayList<>();
        appMsgLst.add("Welcome to PXL Enterprise. \n * Press 1 to start the KYC procedure. \n * Press 2 to enter scratch card code.");

        etTypeMsg = findViewById(R.id.type_msg);
        ImageButton btnSend = findViewById(R.id.send_btn);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ChatScreenAdapter(userMsgLst, appMsgLst);
        recyclerView.setAdapter(adapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnSendAction();
            }
        });
    }

    private void btnSendAction() {

        String str = etTypeMsg.getText().toString().trim();
        userMsgLst.add(str);

        recyclerView.post(new Runnable() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
        etTypeMsg.setText("");

    }
}