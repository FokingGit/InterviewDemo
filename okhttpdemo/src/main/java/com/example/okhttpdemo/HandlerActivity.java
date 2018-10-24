package com.example.okhttpdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HandlerActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Button button;

    private UpdateHandler updateHandler;

    class UpdateHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 1) {
                System.out.println(Thread.currentThread().getName());
                mTextMessage.setText("update====");
                System.out.println("收到信息了");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);

        button = (Button) findViewById(R.id.btn_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTextViewContent();
            }
        });
    }


    private void updateTextViewContent() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("睡醒了");
               Looper.prepare();
                updateHandler = new UpdateHandler();
                Message message = new Message();
                message.what = 1;

                updateHandler.sendMessage(message);
                Looper.loop();
            }
        }.start();
    }
}