package com.wjc.message.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
            CharSequence text = list.get(i).getText();
            if (text != null) {
                viewHolder.receiveLayout.setVisibility(View.GONE);
                viewHolder.sendLayout.setVisibility(View.VISIBLE);
                viewHolder.sendText.setText(text);
            } else {
                String image = list.get(i).getImage();

                byte[] data = Base64.decode(image, 0);
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                viewHolder.receiveLayout.setVisibility(View.GONE);
                viewHolder.sendLayout.setVisibility(View.VISIBLE);
                viewHolder.sendText.setVisibility(View.GONE);
                viewHolder.sendImage.setVisibility(View.VISIBLE);
                viewHolder.sendImage.setImageBitmap(bitmap);
            }
        } else {
            CharSequence text = list.get(i).getText();
            if (text != null) {
                viewHolder.sendLayout.setVisibility(View.GONE);
                viewHolder.receiveLayout.setVisibility(View.VISIBLE);
                viewHolder.receiveText.setText(text);
            } else {
                String image = list.get(i).getImage();

                byte[] data = Base64.decode(image, 0);
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                viewHolder.sendLayout.setVisibility(View.GONE);
                viewHolder.receiveLayout.setVisibility(View.VISIBLE);
                viewHolder.receiveText.setVisibility(View.GONE);
                viewHolder.receiveImage.setVisibility(View.VISIBLE);
                viewHolder.receiveImage.setImageBitmap(bitmap);
            }
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
        ImageView receiveImage;
        ImageView sendImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            receiveLayout = (LinearLayout) itemView.findViewById(R.id.receiveLayout);
            sendLayout = (LinearLayout) itemView.findViewById(R.id.sendLayout);
            receiveText = (TextView) itemView.findViewById(R.id.receiveText);
            sendText = (TextView) itemView.findViewById(R.id.sendText);
            receiveImage = (ImageView) itemView.findViewById(R.id.receiveImage);
            sendImage = (ImageView) itemView.findViewById(R.id.sendImage);
        }
    }

}
