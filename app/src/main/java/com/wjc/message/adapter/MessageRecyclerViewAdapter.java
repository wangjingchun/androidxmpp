package com.wjc.message.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wjc.message.R;
import com.wjc.message.dao.MessageText;

import java.util.List;

public class MessageRecyclerViewAdapter extends RecyclerView.Adapter<MessageRecyclerViewAdapter.ViewHolder> {

    private List<MessageText> list;
    private Context context;

    public MessageRecyclerViewAdapter(List<MessageText> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (list.get(i).getType() == 1) {
            viewHolder.receiveLayout.setVisibility(View.GONE);
            viewHolder.sendLayout.setVisibility(View.VISIBLE);
            viewHolder.sendText.setText(list.get(i).getText());
        } else {
            viewHolder.receiveLayout.setVisibility(View.VISIBLE);
            viewHolder.sendLayout.setVisibility(View.GONE);
            viewHolder.receiveText.setText(list.get(i).getText());
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout receiveLayout;
        LinearLayout sendLayout;
        TextView receiveText;
        TextView sendText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            receiveLayout = (LinearLayout) itemView.findViewById(R.id.receiveLayout);
            sendLayout = (LinearLayout) itemView.findViewById(R.id.sendLayout);
            receiveText = (TextView) itemView.findViewById(R.id.receiveText);
            sendText = (TextView) itemView.findViewById(R.id.sendText);
        }
    }

}
