package com.minhchien.fithou.booktour.mFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.minhchien.fithou.booktour.R;
import com.minhchien.fithou.booktour.mActivity.ChiTietActivity;
import com.minhchien.fithou.booktour.mAdapter.AdapterListViewTour;
import com.minhchien.fithou.booktour.mObjects.MenuTour;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_TourNuocNgoai extends Fragment {
    ListView lvTour;
    ArrayList<MenuTour> tourList;
    AdapterListViewTour adapterListViewTour;
    String URL = "http://www.booktour.esy.es/api/danhsachtournuocngoai.php";
    String HinhAnh,TenTour,Gia,ThoiGian,PK_TourID,NgayKhoiHanh,DiemXuatPhat,LichTrinh,TTGiaTour,DiemDen,PhuongTien,Tour,TenDiaDiem;

    public Fragment_TourNuocNgoai() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tour_nuoc_ngoai, container, false);
        lvTour = (ListView) view.findViewById(R.id.lvTour);
        tourList = new ArrayList<MenuTour>();
        loginToServer();
        adapterListViewTour = new AdapterListViewTour(getActivity().getApplicationContext(), R.layout.custom_layout_tour, tourList);
        lvTour.setAdapter(adapterListViewTour);
        lvTour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MenuTour item = tourList.get(position);
                HinhAnh = item.getHinhAnh();
                TenTour = item.getTenTour();
                Gia = String.valueOf(item.getGia());
                ThoiGian = item.getThoiGian();
                NgayKhoiHanh = item.getNgayKhoiHanh();
                LichTrinh = item.getLichTrinh();
                DiemDen = item.getDiemDen();
                PhuongTien = item.getPhuongTien();
                TTGiaTour = item.getTTGiaTour();
                PK_TourID = String.valueOf(item.getPK_TourID());
                Intent intent = new Intent(getActivity().getApplicationContext(), ChiTietActivity.class);
                intent.putExtra("HinhAnh",HinhAnh.toString());
                intent.putExtra("TenTour",TenTour.toString());
                intent.putExtra("Gia",Gia.toString());
                intent.putExtra("ThoiGian",ThoiGian.toString());
                intent.putExtra("NgayKhoiHanh",NgayKhoiHanh.toString());
                intent.putExtra("LichTrinh",LichTrinh.toString());
                intent.putExtra("DiemDen",DiemDen.toString());
                intent.putExtra("PhuongTien",PhuongTien.toString());
                intent.putExtra("TTGiaTour",TTGiaTour.toString());
                intent.putExtra("PK_TourID",PK_TourID.toString());
                intent.putExtra("ten",TenTour.toString());
                startActivity(intent);
            }
        });
        return view;
    }

    public void loginToServer() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.get(URL, params,
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String str = new String(responseBody, "UTF-8");
                            getListTour(str);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(getActivity().getApplicationContext(), "Không thể kết nối với server, vui lòng kiểm tra lại internet hoặc thử lại sau.", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void getListTour(String response) {
        Log.d("ListTour", response);
        try {
            JSONObject obj = new JSONObject(response);
            int trangthai = Integer.parseInt(obj.getString("code"));

            String message = obj.getString("message");
            if (trangthai == 0) {
                //Toast.makeText(getActivity().getApplicationContext(), message.toString(), Toast.LENGTH_LONG).show();
                JSONArray jsonArray = new JSONArray(obj.getString("result"));
                for (int i = 0; i < jsonArray.length(); ++i) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    MenuTour tour = new MenuTour();
                    tour.setHinhAnh(jsonObject.getString("AnhDaiDien"));
                    tour.setTenTour(jsonObject.getString("TenChiTiet"));
                    tour.setThoiGian(jsonObject.getString("ThoiGian"));
                    tour.setNgayKhoiHanh(jsonObject.getString("NgayKhoiHanh"));
                    tour.setLichTrinh(jsonObject.getString("LichTrinh"));
                    tour.setDiemDen(jsonObject.getString("DiemDen"));
                    tour.setPhuongTien(jsonObject.getString("PhuongTien"));
                    tour.setTTGiaTour(jsonObject.getString("TTGiaTour"));
                    tour.setGia(jsonObject.getInt("GiaTour"));
                    tour.setPK_TourID(jsonObject.getInt("PK_TourID"));
                    tourList.add(tour);
                    adapterListViewTour.notifyDataSetChanged();
                }

            } else if (trangthai == 1) {
                Toast.makeText(getActivity().getApplicationContext(), message.toString(), Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
    }
}
