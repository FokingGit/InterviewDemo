package com.example.valution.ui.homepage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.valution.R;
import com.example.valution.adapter.ViewPagerAdapter;
import com.example.valution.common.base.BaseStateFragment;
import com.example.valution.ui.homepage.state.ALLOrderFragment;
import com.example.valution.ui.homepage.state.BackedFragment;
import com.example.valution.ui.homepage.state.NeedAccessFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 车辆评估
 */
public class CarAccessFragment extends Fragment {

    public static String TAG = CarAccessFragment.class.getSimpleName();
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private Unbinder mBind;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_caraccess, container, false);
        mBind = ButterKnife.bind(this, inflate);

        String[] titles = {"全部", "待评估"};
        Fragment[] fragments = {new ALLOrderFragment(), new NeedAccessFragment()};

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), titles, fragments);
        viewpager.setAdapter(viewPagerAdapter);
        tablayout.setupWithViewPager(viewpager);

        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBind != null) mBind.unbind();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
