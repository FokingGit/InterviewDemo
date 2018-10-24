package com.example.foking.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

public class JamesService extends Service {
    private static final String TAG = "JamesService";

    public JamesService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return mJamesChatInterface;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private JamesChatInterface.Stub mJamesChatInterface = new JamesChatInterface.Stub() {
        @Override
        public boolean receiveKobeChatContent(String content) throws RemoteException {
            EventBus.getDefault().post(new EventBusBean(content));
            Log.e(TAG, content);
            return true;
        }
    };

    public static class EventBusBean {
        public String value;

        public EventBusBean(String value) {
            this.value = value;
        }
    }

}
