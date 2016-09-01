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
import com.minhchien.fithou.booktour.mObjects.MenuTour;

import java.util.List;

/**
 * Created by Admin on 23-Jun-16.
 */
public class AdapterListViewTour extends ArrayAdapter<MenuTour> {
    Context context;
    int resID;
    List<MenuTour> objects;
    public AdapterListViewTour(Context context, int resource, List<MenuTour> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resID = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context,resID,null);
        ImageView hinhanh = (ImageView) view.findViewById(R.id.ivAnhDai);
        TextView TenTour = (TextView) view.findViewById(R.id.tvTienTour);
        TextView Gia = (TextView) view.findViewById(R.id.tvGia);
        TextView ThoiGian = (TextView) view.findViewById(R.id.tvThoiGian);

        MenuTour item = objects.get(position);
        Glide.with(context).load(item.getHinhAnh())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(hinhanh);
        TenTour.setText(item.getTenTour());
        Gia.setText("Giá: "+ item.getGia()+"đ");
        ThoiGian.setText("Thời Gian: "+item.getThoiGian());
        return view;
    }
}

