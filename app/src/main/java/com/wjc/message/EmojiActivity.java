package com.wjc.message;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class EmojiActivity extends AppCompatActivity {

    private TextView emoji;

    private AssetManager assetManager;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_emoji);

        assetManager = getResources().getAssets();


        emoji = (TextView) findViewById(R.id.emoji);
        String html = "<span><font color='#FF0000'>nihao,shijie</font></span><img src='emoji/202_tiger.png' /><img src='emoji/109_scream.png' /><img src='emoji/102_blush.png' />";
        CharSequence sp = Html.fromHtml(html, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                /*
                InputStream inputStream = getResources().openRawResource(Integer.parseInt(source));
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
                Drawable drawable = (Drawable) bitmapDrawable;
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                */

                Drawable drawable = null;
                try {
                    InputStream inputStream = assetManager.open(source);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
                    drawable = (Drawable) bitmapDrawable;
                    drawable.setBounds(0, 0, 96, 96);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return drawable;
            }
        }, null);

        emoji.setText(sp);
        emoji.setMovementMethod(LinkMovementMethod.getInstance());



        /*
        imageView = (ImageView) findViewById(R.id.imageView);
        try {
            InputStream inputStream = assetManager.open("emoji/astonished.png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */


    }


}
