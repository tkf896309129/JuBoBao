package com.example.huoshangkou.jubowan.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;


/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.service
 * 类名：LocalService
 * 描述：
 * 创建时间：2020-04-10  09:30
 */

public class LocalService extends Service{

    private ServiceConnection conn;
    private MyService myService;

    @Override
    public IBinder onBind(Intent intent) {
        return myService;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        if (conn == null) {
            conn = new MyServiceConnection();
        }
        myService = new MyService();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent intents = new Intent();
        intents.setClass(this, RemoteService.class);
        bindService(intents, conn, Context.BIND_IMPORTANT);
        return START_STICKY;
    }

    class MyService extends ServiceKeep.Stub {


        @Override
        public String getName() throws RemoteException {
            return null;
        }
    }

    class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("获取连接");

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(LocalService.this, "远程连接被干掉了", Toast.LENGTH_SHORT).show();
            LocalService.this.startService(new Intent(LocalService.this,
                    RemoteService.class));
            LocalService.this.bindService(new Intent(LocalService.this,
                    RemoteService.class), conn, Context.BIND_IMPORTANT);

        }

    }

}
