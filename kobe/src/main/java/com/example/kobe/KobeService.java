package com.example.kobe;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.foking.aidldemo.JamesChatInterface;
import com.example.foking.aidldemo.KobeChatInterface;

import org.greenrobot.eventbus.EventBus;

public class KobeService extends Service {
    private static final String TAG = "KobeService";

    public KobeService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return mKobeChatInterface;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private KobeChatInterface.Stub mKobeChatInterface = new KobeChatInterface.Stub() {
        @Override
        public boolean receiveJamesChatContent(String content) throws RemoteException {
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
