package com.example.alpha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.alpha.Adapter.ChatScreenAdapter;
import com.example.alpha.Pojo.MyListData;

import java.util.ArrayList;
import java.util.List;

public class ChatScreenActivity extends AppCompatActivity {

    private List<MyListData> msgLst;
    private EditText etTypeMsg;
    private RecyclerView recyclerView;
    private ChatScreenAdapter adapter;

    private static final String SEND = "SEND";
    private static final String RECEIVE = "RECEIVE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        msgLst = new ArrayList<>();

        MyListData myListData = new MyListData();
        myListData.setMsg("Welcome to PXL Enterprise. \n * Press 1 to start the KYC procedure. \n" +
                " * Press 2 to enter scratch card code.");
        myListData.setReceiveOrSend(RECEIVE);
        msgLst.add(myListData);

        etTypeMsg = findViewById(R.id.type_msg);
        ImageButton btnSend = findViewById(R.id.send_btn);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ChatScreenAdapter(msgLst);
        recyclerView.setAdapter(adapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnSendAction();
            }
        });
    }

    private void btnSendAction() {

        String str = etTypeMsg.getText().toString().trim();
        if(!str.isEmpty()) {
            // adding user response in the arraylist
            msgLst.add(new MyListData(str, SEND));
            if(str.equals("1")){
                msgLst.add(new MyListData("User pressed 1", RECEIVE));
            }

            // refreshing the recycler view
            recyclerView.post(new Runnable() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });

            // making editText ready for next msg from the user
            etTypeMsg.setText("");

        } else {
            Toast.makeText(getApplicationContext(),"Please enter a valid response",Toast.LENGTH_LONG).show();
        }
    }
}