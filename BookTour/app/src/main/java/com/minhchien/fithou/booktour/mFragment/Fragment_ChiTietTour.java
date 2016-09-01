package com.minhchien.fithou.booktour.mFragment;


import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.minhchien.fithou.booktour.R;
import com.minhchien.fithou.booktour.mAdapter.AdapterListViewChiTiet;
import com.minhchien.fithou.booktour.mObjects.MenuTour;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_ChiTietTour extends Fragment {
    ListView lvTour;
    ArrayList<MenuTour> tourList;
    AdapterListViewChiTiet adapterListViewChiTiet;
    public Fragment_ChiTietTour() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chi_tiet_tour, container, false);
        Bundle bundle = getArguments();
        final MenuTour menuTour = (MenuTour) bundle.getSerializable("tour");

        lvTour = (ListView)view. findViewById(R.id.lvTour);
        tourList = new ArrayList<MenuTour>();
        tourList.add(menuTour);
        Button btnGoi = (Button)view.findViewById(R.id.btnGoi);
        Button btnDatTour = (Button)view.findViewById(R.id.btnDatTour);
        btnGoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdt = "0912942288";
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+sdt));
                startActivity(callIntent);

            }
        });

        btnDatTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setTitle("Đặt Tour");
                dialog.setContentView(R.layout.dialog_dattour);
                final TextView tvTenTour,tvGia;
                final EditText etTen,etDienThoai,etEmail,etDiaChi,etThongTinBoXung;
                final Button btnDat,btnHuy;

                tvTenTour = (TextView)dialog.findViewById(R.id.tvTour);
                tvGia = (TextView)dialog.findViewById(R.id.tvGia);

                tvTenTour.setText("Tên Tour: "+menuTour.getTenTour());
                tvGia.setText("Giá: "+ menuTour.getGia() + " VND");

                etTen = (EditText) dialog.findViewById(R.id.etTen);
                etDienThoai = (EditText) dialog.findViewById(R.id.etDienThoai);
                etEmail = (EditText) dialog.findViewById(R.id.etEmail);
                etDiaChi = (EditText) dialog.findViewById(R.id.etDiaChi);
                etThongTinBoXung = (EditText) dialog.findViewById(R.id.etThongTinBoXung);

                btnDat = (Button)dialog.findViewById(R.id.btnDatTour);
                btnHuy = (Button)dialog.findViewById(R.id.btnHuy);

                btnDat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(etEmail.getText().toString().length()>0){
                            if(etDienThoai.getText().toString().length()>0){
                                if (etTen.getText().toString().length()>0){
                                    String[] TO = {"minhchienwikipedia@gmail.com"};

                                    Intent emailIntent = new Intent(Intent.ACTION_SEND);

                                    emailIntent.setData(Uri.parse("mailto:"));
                                    emailIntent.setType("text/plain");
                                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Đặt Tour");
                                    emailIntent.putExtra(Intent.EXTRA_TEXT,
                                            "Tên đầy đủ: *" + etTen.getText().toString() +"\n"
                                                    +"Điện thoại: *"+ etDienThoai.getText().toString() +"\n"
                                                    +"Email: *"+ etEmail.getText().toString() +"\n"
                                                    +"Địa chỉ: "+ etDiaChi.getText().toString() +"\n"
                                                    +"Thông tin bổ xung: "+ etThongTinBoXung.getText().toString());

                                    try {
                                        startActivity(Intent.createChooser(emailIntent, "Gửi mail..."));


                                    }
                                    catch (android.content.ActivityNotFoundException ex) {
                                        Toast.makeText(getActivity().getApplicationContext(), "Địa chỉ mail không tôn tại.", Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(getActivity().getApplicationContext(), "OK.", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();

                                }else {
                                    Toast.makeText(getActivity().getApplicationContext(), "Bạn cần nhập Họ Tên.", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(getActivity().getApplicationContext(), "Bạn cần nhập Số Điện Thoại.", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(getActivity().getApplicationContext(), "Bạn cần nhập Email.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });

        adapterListViewChiTiet = new AdapterListViewChiTiet(getActivity().getApplicationContext(), R.layout.custom_lv_chitiet, tourList);
        lvTour.setAdapter(adapterListViewChiTiet);
        return view;
    }

}
