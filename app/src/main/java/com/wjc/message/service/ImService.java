package com.wjc.message.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.wjc.message.MainActivity;
import com.wjc.message.util.Config;

import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

public class ImService extends Service {

    private static String ACTIVITY_ACTION = "ACTIVITY_ACTION";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        String userName = bundle.getString("userName");
        String password = bundle.getString("password");

        login(userName, password);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

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
                    XMPPTCPConnection connection = new XMPPTCPConnection(config);
                    connection.connect();
                    connection.login(userName, password);

                    ChatManager chatManager = ChatManager.getInstanceFor(connection);

                    MainActivity.chatManager = chatManager;

                    Bundle bundle = new Bundle();
                    bundle.putString("action", "login_ok");

                    //发送广播用于更新界面
                    Intent intent = new Intent();
                    intent.setAction(ACTIVITY_ACTION);
                    intent.putExtras(bundle);
                    sendBroadcast(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
