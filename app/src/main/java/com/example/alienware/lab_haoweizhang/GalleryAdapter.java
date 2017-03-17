package com.example.alienware.lab_haoweizhang;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/7.
 */

public class GalleryAdapter extends BaseAdapter {
    private Context mContext;
    private List<ImageInfo> mAllData;


    public GalleryAdapter(Context context) {
        mContext = context;
        mAllData = new ArrayList<>();
    }

    public void setData(List<ImageInfo> allData) {
        mAllData = allData;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mAllData.size();
    }

    @Override
    public ImageInfo getItem(int i) {
        return mAllData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View container, ViewGroup viewGroup) {
        ViewHolder holder;
        ImageInfo info = getItem(i);
        if (container == null) {
            holder = new ViewHolder();
            container = LayoutInflater.from(mContext).inflate(R.layout.simple_list_item, null);
            holder.img = (ImageView) container.findViewById(R.id.img);

            container.setTag(holder);
        } else {
            holder = (ViewHolder) container.getTag();
        }
        File file = new File(info.fullName);
        Uri imageUri = Uri.fromFile(file);
        Glide.with(mContext).load(imageUri).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().into(holder.img);
        return container;
    }

    public static class ViewHolder {
        private ImageView img;
    }
}
