package com.wjc.message.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.wjc.message.util.Config;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.io.File;
import java.io.FileInputStream;

public class ImService extends Service {

    private static String ACTIVITY_ACTION = "ACTIVITY_ACTION";
    private static String SERVICE_ACTION = "SERVICE_ACTION";

    private XMPPTCPConnection connection;
    private ChatManager chatManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SERVICE_ACTION);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();

            String action = bundle.getString("action");
            if (action == "login") {
                String userName = bundle.getString("userName");
                String password = bundle.getString("password");

                login(userName, password);
            } else if (action == "send_text") {
                String sendId = bundle.getString("sendId");
                String html = bundle.getString("html");

                sendText(sendId, html);
            } else if (action == "send_image") {
                String sendId = bundle.getString("sendId");
                String path = bundle.getString("path");

                sendImage(sendId, path);
            }
        }
    };

    private void login(final String userName, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                            .setHost(Config.SERVER_IP)//服务器IP地址
                            //服务器端口
                            .setPort(Config.PORT)
                            //设置登录状态
                            .setSendPresence(false)
                            //服务器名称
                            .setServiceName(Config.SERVER_NAME)
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

                                    Bundle bundle = new Bundle();
                                    bundle.putString("action", "receive_ok");
                                    bundle.putInt("type", 2);
                                    bundle.putString("text", messageBody);

                                    //发送广播用于更新界面
                                    Intent intent = new Intent();
                                    intent.setAction(ACTIVITY_ACTION);
                                    intent.putExtras(bundle);
                                    sendBroadcast(intent);
                                }
                            });
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void sendText(String sendId, String html) {
        Chat chat = chatManager.createChat(sendId + "@" + Config.SERVER_NAME);
        try {
            chat.sendMessage(html);

            //发送广播用于更新界面
            Intent intent = new Intent();
            intent.setAction("send_text_ok");
            sendBroadcast(intent);
        } catch (SmackException.NotConnectedException e) {
            e.printStackTrace();
        }
    }

    private void sendImage(String sendId, String path) {
        File file = new File(path);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();

            String string = android.util.Base64.encodeToString(data, 0);

            Chat chat = chatManager.createChat(sendId + "@" + Config.SERVER_NAME);
            chat.sendMessage(string + Config.BASE64_IMAGE_TAG);

            //发送广播用于更新界面
            Intent intent = new Intent();
            intent.setAction("send_image_ok");
            sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
