package com.example.foking.aidldemo;

import android.app.Activity;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.SoftReference;
import java.util.List;

public class ChatContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private SoftReference<Activity> mActivitySoftReference;

    public ChatContentAdapter(SoftReference<Activity> activitySoftReference) {
        mActivitySoftReference = activitySoftReference;
    }

    private List<CharContentBean> mDisPlayData;

    public void setDisPlayData(List<CharContentBean> disPlayData) {
        mDisPlayData = disPlayData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemViewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (itemViewType == 0) {
            View inflate = LayoutInflater.from(mActivitySoftReference.get()).inflate(R.layout.item_other, viewGroup, false);
            viewHolder = new OtherViewHolder(inflate);
        } else {
            View inflate = LayoutInflater.from(mActivitySoftReference.get()).inflate(R.layout.item_self, viewGroup, false);
            viewHolder = new SelfViewHolder(inflate);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int viewType = getItemViewType(i);
        if (viewType == 0) {
            ((OtherViewHolder) viewHolder).mOtherImageView.setImageResource(mDisPlayData.get(i).getPicResId());
            ((OtherViewHolder) viewHolder).mOtherTextView.setText(mDisPlayData.get(i).getContent());
        } else {
            ((SelfViewHolder) viewHolder).mSelfImageView.setImageResource(mDisPlayData.get(i).getPicResId());
            ((SelfViewHolder) viewHolder).mSelfTextView.setText(mDisPlayData.get(i).getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mDisPlayData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDisPlayData.get(position).getType();
    }

    public static class SelfViewHolder extends RecyclerView.ViewHolder {

        public TextView mSelfTextView;
        public ImageView mSelfImageView;

        public SelfViewHolder(View view) {
            super(view);
            mSelfImageView = view.findViewById(R.id.iv_photo_self);
            mSelfTextView = view.findViewById(R.id.tv_self);
        }

    }

    public static class OtherViewHolder extends RecyclerView.ViewHolder {
        public TextView mOtherTextView;
        public ImageView mOtherImageView;

        public OtherViewHolder(View view) {
            super(view);
            mOtherImageView = view.findViewById(R.id.iv_photo_other);
            mOtherTextView = view.findViewById(R.id.tv_other);
        }
    }
}

