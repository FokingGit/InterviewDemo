package com.example.valution.ui.homepage.state;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.valution.R;
import com.example.valution.common.base.BaseStateFragment;

public class ALLOrderFragment extends BaseStateFragment {


    public static String TAG = ALLOrderFragment.class.getSimpleName();

    int[] resIds = {R.drawable.shape_blue, R.drawable.shape_red, R.drawable.shape_yellow};

    @Override
    protected void configFragmentView(View view) {
        ViewPager viewPager = view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyViewPager());
    }

    class MyViewPager extends PagerAdapter {

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((ImageView) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setBackgroundResource(resIds[position]);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public int getCount() {
            return resIds.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }
    }

}
