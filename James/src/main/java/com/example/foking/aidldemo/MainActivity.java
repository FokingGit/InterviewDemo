package com.example.foking.aidldemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerview;
    private EditText mEtChatInput;
    private Button mBtSend;
    private List<CharContentBean> mCharContentBeans = new ArrayList<>();
    private KobeChatInterface mKobeChatInterface;
    private ChatContentAdapter mChatContentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_main);
        linkService();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_send:
                submit();
                break;
        }
    }

    private void initView() {
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        mEtChatInput = (EditText) findViewById(R.id.et_chat_input);
        mBtSend = (Button) findViewById(R.id.bt_send);

        mBtSend.setOnClickListener(this);
        mChatContentAdapter = new ChatContentAdapter(new SoftReference<Activity>(this));
        mChatContentAdapter.setDisPlayData(mCharContentBeans);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setLayoutManager(linearLayoutManager);
        mRecyclerview.setAdapter(mChatContentAdapter);
    }

    private void linkService() {
        Intent intent = new Intent("foking.kobe");
        intent.setPackage("com.example.kobe");
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mKobeChatInterface = KobeChatInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveOtherContent(JamesService.EventBusBean eventBusBean) {
        mCharContentBeans.add(new CharContentBean(0, eventBusBean.value, R.drawable.kobe));
        mChatContentAdapter.notifyDataSetChanged();
        mRecyclerview.scrollToPosition(mCharContentBeans.size() - 1);
    }

    private void submit() {
        // validate
        String input = mEtChatInput.getText().toString().trim();
        if (TextUtils.isEmpty(input)) {
            Toast.makeText(this, "input不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        mEtChatInput.setText("");
        mEtChatInput.clearFocus();
        mCharContentBeans.add(new CharContentBean(1, input, R.drawable.james));
        mChatContentAdapter.notifyDataSetChanged();
        mRecyclerview.scrollToPosition(mCharContentBeans.size() - 1);

        try {
            mKobeChatInterface.receiveJamesChatContent(input);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
