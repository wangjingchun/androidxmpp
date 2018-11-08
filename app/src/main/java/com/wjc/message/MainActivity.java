package com.wjc.message;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wjc.message.adapter.EmojiRecyclerViewAdapter;
import com.wjc.message.adapter.MessageRecyclerViewAdapter;
import com.wjc.message.dao.Emoji;
import com.wjc.message.dao.MessageText;
import com.wjc.message.util.Utils;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.filetransfer.FileTransferListener;
import org.jivesoftware.smackx.filetransfer.FileTransferManager;
import org.jivesoftware.smackx.filetransfer.FileTransferRequest;
import org.jivesoftware.smackx.filetransfer.IncomingFileTransfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String SERVER_IP = "47.98.53.86";
    public static final int PORT = 5222;
    public static final String SERVER_NAME = "iZbp14vtdec3c85k11gn6tZ";

    private XMPPTCPConnection connection;
    private ChatManager chatManager;
    private FileTransferManager transferManager;

    private Handler handler;

    private ActionBar actionBar;
    private ImageView actionbarBack;
    private TextView actionBarTextView;

    private RecyclerView recyclerView;
    private EditText messageEditText;
    private ImageView emojiImageView;
    private ImageView imageImageView;
    private Button sendButton;
    private LinearLayout emojiLinearLayout;
    private RecyclerView emojiRecyclerView;

    private List<MessageText> list;
    private MessageRecyclerViewAdapter adapter;

    private List<Emoji> emojiFilenameList;
    private EmojiRecyclerViewAdapter emojiAdapter;

    private AssetManager assetManager;

    private final int WHAT_RECEIVE = 2;
    private final String BASE64_IMAGE_TAG = "base64_image:nt52S6^Ng#dnzUj%BbDMu8qsv7&$LknK:base64_image";

    private final String USER_ID = "wjc";
    private final String SEND_ID = "wsh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.actionbar);

        View actionBarView = actionBar.getCustomView();
        actionbarBack = (ImageView) actionBarView.findViewById(R.id.actionbarBack);
        actionbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        actionBarTextView = (TextView) actionBarView.findViewById(R.id.actionBarTextView);
        actionBarTextView.setText(SEND_ID);

        assetManager = getResources().getAssets();


        setComponentView();
        setKeyboard();
        setRequestPermissions();
    }

    private void setComponentView() {
        list = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        adapter = new MessageRecyclerViewAdapter(list, MainActivity.this);
        recyclerView.setAdapter(adapter);

        messageEditText = (EditText) findViewById(R.id.messageEditText);

        emojiLinearLayout = (LinearLayout) findViewById(R.id.emojiLinearLayout);
        emojiFilenameList = Utils.getEmojiFilename();
        emojiRecyclerView = (RecyclerView) findViewById(R.id.emojiRecyclerView);
        emojiRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 5, GridLayoutManager.VERTICAL, false));
        emojiAdapter = new EmojiRecyclerViewAdapter(emojiFilenameList, MainActivity.this, assetManager);
        emojiAdapter.setOnItemClickListener(new EmojiRecyclerViewAdapter.OnEmojiItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.Adapter adapter, View itemView, int position, long id) {
                Emoji emojiObj = emojiFilenameList.get(position);
                String source = "emoji/" + emojiObj.getFilename();
                String html = "<img src='" + source + "'/>";

                CharSequence charSequence = Html.fromHtml(html, new Html.ImageGetter() {
                    @Override
                    public Drawable getDrawable(String source) {
                        Drawable drawable = null;
                        try {
                            InputStream inputStream = assetManager.open(source);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
                            drawable = (Drawable) bitmapDrawable;
                            drawable.setBounds(0, 0, 80, 80);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        return drawable;
                    }
                }, null);

                CharSequence newValue = messageEditText.getText().append(charSequence);

                messageEditText.setText(newValue);
                messageEditText.setSelection(newValue.length());
            }
        });
        emojiRecyclerView.setAdapter(emojiAdapter);

        emojiImageView = (ImageView) findViewById(R.id.emojiImageView);
        emojiImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isKeyboardShow()) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(messageEditText.getWindowToken(), 0);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SharedPreferences preferences = getSharedPreferences("keyboard_height", MODE_PRIVATE);
                            int px = preferences.getInt("keyboard_height", 0);
                            if (px != 0) {
                                ViewGroup.LayoutParams layoutParams = emojiLinearLayout.getLayoutParams();
                                layoutParams.height = px;
                            }

                            emojiLinearLayout.setVisibility(View.VISIBLE);
                            emojiImageView.setImageResource(R.drawable.icon_emoji_pink);
                        }
                    }, 60);
                } else {
                    if (emojiLinearLayout.getVisibility() == View.GONE) {
                        SharedPreferences preferences = getSharedPreferences("keyboard_height", MODE_PRIVATE);
                        int px = preferences.getInt("keyboard_height", 0);
                        if (px != 0) {
                            ViewGroup.LayoutParams layoutParams = emojiLinearLayout.getLayoutParams();
                            layoutParams.height = px;
                        }

                        emojiLinearLayout.setVisibility(View.VISIBLE);
                        emojiImageView.setImageResource(R.drawable.icon_emoji_pink);
                    } else {
                        emojiLinearLayout.setVisibility(View.GONE);
                        emojiImageView.setImageResource(R.drawable.icon_emoji_black);
                    }
                }
            }
        });

        imageImageView = (ImageView) findViewById(R.id.imageImageView);
        imageImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Matisse.from(MainActivity.this)
                        .choose(MimeType.allOf()) // 选择 mime 的类型
                        .countable(true)
                        .maxSelectable(1) // 图片选择的最多数量
                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f) // 缩略图的比例
                        .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                        .theme(R.style.PickerTheme)
                        .forResult(1000); // 设置作为标记的请求码
            }
        });

        sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendText();
            }
        });

        login(USER_ID, "666666");


        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == WHAT_RECEIVE) {
                    Bundle bundle = msg.getData();

                    String html = bundle.getString("text");
                    if (html.indexOf(BASE64_IMAGE_TAG) == -1) {
                        CharSequence charSequence = Html.fromHtml(html, new Html.ImageGetter() {
                            @Override
                            public Drawable getDrawable(String source) {
                                Drawable drawable = null;
                                try {
                                    InputStream inputStream = assetManager.open(source);
                                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                    BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
                                    drawable = (Drawable) bitmapDrawable;
                                    drawable.setBounds(0, 0, 80, 80);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                return drawable;
                            }
                        }, null);


                        list.add(new MessageText(bundle.getInt("type"), charSequence));
                        adapter.notifyDataSetChanged();
                        recyclerView.scrollToPosition(adapter.getItemCount() - 1);
                    } else {
                        html = html.replace(BASE64_IMAGE_TAG, "");

                        list.add(new MessageText(bundle.getInt("type"), null, html));
                        adapter.notifyDataSetChanged();
                        recyclerView.scrollToPosition(adapter.getItemCount() - 1);
                    }
                }
                super.handleMessage(msg);
            }
        };
    }

    private void setKeyboard() {
        final View activityRootView = findViewById(R.id.parent);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();

                SharedPreferences preferences = getSharedPreferences("keyboard_height", MODE_PRIVATE);

                if (heightDiff > Utils.dp2px(MainActivity.this, 200)) {
                    if (emojiLinearLayout.getVisibility() == View.VISIBLE) {
                        emojiLinearLayout.setVisibility(View.GONE);
                        emojiImageView.setImageResource(R.drawable.icon_emoji_black);
                    }

                    int oldHeight = preferences.getInt("keyboard_height", 0);
                    if (oldHeight == 0) {
                        int hideHeight = preferences.getInt("hide_height", 0);
                        if (hideHeight != 0) {
                            int keyboardHeight = heightDiff - hideHeight;

                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putInt("keyboard_height", keyboardHeight);
                            editor.commit();
                        }
                    }
                } else {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("hide_height", heightDiff);
                    editor.commit();
                }
            }
        });
    }

    private void setRequestPermissions() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }
    }

    private void login(final String userName, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                            .setHost(SERVER_IP)//服务器IP地址
                            //服务器端口
                            .setPort(PORT)
                            //设置登录状态
                            .setSendPresence(false)
                            //服务器名称
                            .setServiceName(SERVER_NAME)
                            //是否开启安全模式
                            .setSecurityMode(XMPPTCPConnectionConfiguration.SecurityMode.disabled)
                            //是否开启压缩
                            .setCompressionEnabled(false)
                            //开启调试模式
                            .setDebuggerEnabled(true)
                            .setSendPresence(true)
                            .build();
                    connection = new XMPPTCPConnection(config);
                    connection.connect();
                    connection.login(userName, password);

                    chatManager = ChatManager.getInstanceFor(connection);
                    chatManager.addChatListener(new ChatManagerListener() {
                        @Override
                        public void chatCreated(Chat chat, boolean b) {
                            chat.addMessageListener(new ChatMessageListener() {
                                @Override
                                public void processMessage(Chat chat, Message message) {
                                    String messageBody = message.getBody();

                                    android.os.Message msg = new android.os.Message();
                                    msg.what = WHAT_RECEIVE;
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("type", 2);
                                    bundle.putString("text", messageBody);
                                    msg.setData(bundle);

                                    handler.sendMessage(msg);
                                }
                            });
                        }
                    });

                    transferManager = FileTransferManager.getInstanceFor(connection);
                    transferManager.addFileTransferListener(new FileTransferListener() {
                        @Override
                        public void fileTransferRequest(FileTransferRequest fileTransferRequest) {
                            IncomingFileTransfer incomingTransfer = fileTransferRequest.accept();
                            String filename = incomingTransfer.getFileName();
                            File file = new File(filename);
                            try {
                                incomingTransfer.recieveFile(file);
                            } catch (SmackException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void sendText() {
        Chat chat = chatManager.createChat(SEND_ID + "@" + SERVER_NAME);
        try {
            Editable editable = messageEditText.getText();
            String html = Html.toHtml(editable).replace("<p dir=\"ltr\">", "").replace("</p>", "");

            chat.sendMessage(html);

            list.add(new MessageText(1, editable));
            adapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(adapter.getItemCount() - 1);
            messageEditText.setText("");
        } catch (SmackException.NotConnectedException e) {
            e.printStackTrace();
        }
    }

    private void sendImage(String path) {
        File file = new File(path);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();

            String string = android.util.Base64.encodeToString(data, 0);

            Chat chat = chatManager.createChat(SEND_ID + "@" + SERVER_NAME);
            chat.sendMessage(string + BASE64_IMAGE_TAG);

            list.add(new MessageText(1, null, string));
            adapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(adapter.getItemCount() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isKeyboardShow() {
        boolean isKeyboardShow = false;

        final View activityRootView = findViewById(R.id.parent);
        int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
        if (heightDiff > Utils.dp2px(MainActivity.this, 200)) {
            isKeyboardShow = true;
        }

        return isKeyboardShow;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            List<Uri> list = Matisse.obtainResult(data);

            String path = Utils.getRealPathFromUri(MainActivity.this, list.get(0));

            sendImage(path);
        }
    }
}

