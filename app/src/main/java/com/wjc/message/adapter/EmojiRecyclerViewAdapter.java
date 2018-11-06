package com.wjc.message.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.wjc.message.R;
import com.wjc.message.dao.Emoji;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EmojiRecyclerViewAdapter extends RecyclerView.Adapter<EmojiRecyclerViewAdapter.ViewHolder> {
    private List<Emoji> list;
    private Context context;
    private AssetManager assetManager;

    private OnEmojiItemClickListener onEmojiItemClickListener;

    public EmojiRecyclerViewAdapter(List<Emoji> list, Context context, AssetManager assetManager) {
        this.list = list;
        this.context = context;
        this.assetManager = assetManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_emoji, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("emoji/" + list.get(i).getFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        Drawable drawable = (Drawable) bitmapDrawable;
        drawable.setBounds(0, 0, 96, 96);

        viewHolder.imageView.setImageDrawable(drawable);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onEmojiItemClickListener != null) {
                    onEmojiItemClickListener.onItemClick(EmojiRecyclerViewAdapter.this, v, viewHolder.getAdapterPosition(), v.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.itemEmojiImageView);
        }
    }

    public void setOnItemClickListener(OnEmojiItemClickListener onEmojiItemClickListener) {
        this.onEmojiItemClickListener = onEmojiItemClickListener;
    }

    public interface OnEmojiItemClickListener {
        void onItemClick(RecyclerView.Adapter adapter, View itemView, int position, long id);
    }
}
