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


public class ChatScreenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final MyListData[] listData;

    private static final int TYPE_MSG_SEND = 1;
    private static final int TYPE_MSG_RECEIVE = 2;

    public ChatScreenAdapter(MyListData[] listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_MSG_SEND) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.incoming_cart, parent, false);
            return new ViewHolderOutgoingMsg(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.outgoing_cart, parent, false);
            return new ViewHolderIncomingMsg(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case TYPE_MSG_SEND:
                initLayoutOne((ViewHolderOutgoingMsg) holder, position);
                break;
            case TYPE_MSG_RECEIVE:
                initLayoutTwo((ViewHolderIncomingMsg) holder, position);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listData.length;
    }

    @Override
    public int getItemViewType(int position) {
        double counter = Math.random();
        if (counter > 0.5) {
            return TYPE_MSG_SEND;
        } else {
            return TYPE_MSG_RECEIVE;
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

    private void initLayoutOne(ViewHolderOutgoingMsg holder, int pos) {
        /*
        DataObject current = data.get(pos);
        holder.tvEventTitle.setText(current.getCategoryName());
        holder.tvEventDescription.setText(current.getmText2());
        userImage = current.getImage();
        if (userImage.isEmpty()) {

        } else {
            Picasso.with(context)
                    .load(userImage)
                    .placeholder(R.drawable.app_icons)
                    .into((holder).ivEventImage);
        }*/
    }

    private void initLayoutTwo(ViewHolderIncomingMsg holder, int pos) {

    }
}
