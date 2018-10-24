package com.example.valution.ui.homepage.state;

import android.view.View;
import android.widget.TextView;

import com.example.valution.R;
import com.example.valution.common.base.BaseStateFragment;

public class ALLOrderFragment extends BaseStateFragment {

    public static String TAG = ALLOrderFragment.class.getSimpleName();

    @Override
    protected void configFragmentView(View view) {
        ((TextView)view.findViewById(R.id.tv_test)).setText(TAG);
    }
}
