package com.example.alpha.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alpha.Pojo.MyListData;
import com.example.alpha.R;

import java.util.List;


public class ChatScreenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final List<MyListData> msgLst;

    private static final int TYPE_USER_MSG = 1;
    private static final int TYPE_APP_MSG = 2;

    public ChatScreenAdapter(List<MyListData> msgLst) {
        this.msgLst = msgLst;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_USER_MSG) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.outgoing_cart, parent, false);
            return new ViewHolderOutgoingMsg(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.incoming_cart, parent, false);
            return new ViewHolderIncomingMsg(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case TYPE_USER_MSG:
                initLayoutAppMsg((ViewHolderOutgoingMsg) holder, position);
                break;
            case TYPE_APP_MSG:
                initLayoutUserMsg((ViewHolderIncomingMsg) holder, position);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return msgLst.size();
    }

    @Override
    public int getItemViewType(int position) {

        String msgType = msgLst.get(position).getReceiveOrSend();

        if(msgType.equals("SEND")){
            return TYPE_USER_MSG;
        } else {
            return TYPE_APP_MSG;
        }

    }

    public static class ViewHolderIncomingMsg extends RecyclerView.ViewHolder {
        public TextView textView;
        public LinearLayout linearLayout;
        public ViewHolderIncomingMsg(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textView);
            this.linearLayout = itemView.findViewById(R.id.incoming_cart);
        }
    }

    public static class ViewHolderOutgoingMsg extends RecyclerView.ViewHolder {
        public TextView textView;
        public LinearLayout linearLayout;
        public ViewHolderOutgoingMsg(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textView);
            this.linearLayout = itemView.findViewById(R.id.outgoing_cart);
        }
    }

    private void initLayoutUserMsg(ViewHolderIncomingMsg holder, int pos) {
        MyListData myListData = msgLst.get(pos);
        holder.textView.setText(myListData.getMsg());
    }

    private void initLayoutAppMsg(ViewHolderOutgoingMsg holder, int pos) {
        MyListData myListData = msgLst.get(pos);
        holder.textView.setText(myListData.getMsg());
    }
}

