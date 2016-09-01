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
import com.minhchien.fithou.booktour.mObjects.DiaDiem;

import java.util.List;

/**
 * Created by Admin on 23-Jul-16.
 */
public class AdapterListViewDiaDiem extends ArrayAdapter<DiaDiem> {
    Context context;
    int resID;
    List<DiaDiem> objects;

    public AdapterListViewDiaDiem(Context context, int resource, List<DiaDiem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resID = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context,resID,null);
        ImageView ivAnhDaiDien = (ImageView)view.findViewById(R.id.ivAnhDaiDien);
        TextView tvTenDiaDiem = (TextView)view.findViewById(R.id.tvTenDiaDiem);
        //TextView tvID = (TextView)view.findViewById(R.id.tvID);

        DiaDiem item = objects.get(position);
        //tvID.setText(item.getId()+"");
        tvTenDiaDiem.setText(item.getTenDiaDiem());
        Glide.with(context).load(item.getAnhDaiDien())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivAnhDaiDien);
        return view;
    }
}
