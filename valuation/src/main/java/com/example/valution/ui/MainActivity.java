package com.example.valution.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.valution.R;
import com.example.valution.ui.homepage.CarAccessFragment;
import com.example.valution.ui.homepage.MeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fl_module_container)
    FrameLayout mFlModuleContainer;

    private Fragment mLastShowFragment;

    //底部TAB
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedModule(0);
                    return true;
                case R.id.navigation_dashboard:
                    selectedModule(1);
                    return true;
            }
            return false;
        }
    };
    private FragmentManager mSupportFragmentManager;
    private Fragment[] mFragments;
    private Unbinder mBind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBind = ButterKnife.bind(this);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //设置
        Fragment mCarAccessFragment = new CarAccessFragment();
        Fragment mMeFragment = new MeFragment();
        mFragments = new Fragment[2];
        mFragments[0] = mCarAccessFragment;
        mFragments[1] = mMeFragment;

        //获取FragmentMamager
        mSupportFragmentManager = getSupportFragmentManager();

        //默认选中第一个
        selectedModule(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) mBind.unbind();
    }

    private void selectedModule(int index) {
        Fragment opeateFragment = mFragments[index];
        FragmentTransaction fragmentTransaction = mSupportFragmentManager.beginTransaction();

        //两次选择的是同一个页面
        if (mLastShowFragment != null && mLastShowFragment.getClass().getSimpleName().equals(opeateFragment.getClass().getSimpleName())) {
            return;
        }

        //如果这个页面已经被添加进去了
        if (opeateFragment.isAdded()) {
            //已经添加则使用show
            if (mLastShowFragment != null) fragmentTransaction.hide(mLastShowFragment);
            fragmentTransaction.show(opeateFragment);
            fragmentTransaction.commit();
            mLastShowFragment = opeateFragment;
            return;
        }

        if (mLastShowFragment != null) fragmentTransaction.hide(mLastShowFragment);
        fragmentTransaction.add(R.id.fl_module_container, opeateFragment, opeateFragment.getClass().getSimpleName());
        fragmentTransaction.commit();

        mLastShowFragment = opeateFragment;

    }

}
