package com.minhchien.fithou.booktour.mAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.minhchien.fithou.booktour.R;
import com.minhchien.fithou.booktour.mObjects.HinhAnh;

import java.util.List;

/**
 * Created by Admin on 27-Jul-16.
 */
public class AdapterHinhAnh extends ArrayAdapter<HinhAnh> {
    Context context;
    int resID;
    List<HinhAnh> objects;
    public AdapterHinhAnh(Context context, int resource, List<HinhAnh> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resID = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context,resID,null);
        TextView tvTenAnh = (TextView)view.findViewById(R.id.tvTenAnh);
        ImageView ivHinhAnh = (ImageView)view.findViewById(R.id.ivHinhAnh);
        HinhAnh item = objects.get(position);


        Glide.with(context).load(item.getLinkAnh())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivHinhAnh);
        return view;
    }
}
