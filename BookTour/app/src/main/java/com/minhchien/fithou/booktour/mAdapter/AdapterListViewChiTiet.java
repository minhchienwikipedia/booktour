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
 * Created by Admin on 25-Jul-16.
 */
public class AdapterListViewChiTiet extends ArrayAdapter<MenuTour>{
    Context context;
    int resID;
    List<MenuTour> objects;
    public AdapterListViewChiTiet(Context context, int resource, List<MenuTour> objects) {
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
        TextView DiemDen = (TextView) view.findViewById(R.id.tvDiemDen);
        TextView PhuongTien = (TextView) view.findViewById(R.id.tvPhuongTien);
        TextView LichTrinh = (TextView) view.findViewById(R.id.tvLichTrinh);
        TextView NgayKhoiHanh = (TextView) view.findViewById(R.id.tvKhoiHanh);

        MenuTour item = objects.get(position);
        Glide.with(context).load(item.getHinhAnh())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(hinhanh);
        TenTour.setText(item.getTenTour());
        Gia.setText(item.getGia()+" VND");
        ThoiGian.setText(item.getThoiGian());
        DiemDen.setText(item.getDiemDen());
        PhuongTien.setText(item.getPhuongTien());
        LichTrinh.setText(item.getLichTrinh());
        NgayKhoiHanh.setText("Ngày Khởi Hành: " + item.getNgayKhoiHanh());
//        LichTrinh.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }
}
