package com.minhchien.fithou.booktour.mFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.minhchien.fithou.booktour.R;
import com.minhchien.fithou.booktour.mAdapter.AdapterHinhAnh;
import com.minhchien.fithou.booktour.mObjects.HinhAnh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_HinhAnh extends Fragment {
    String URL = "http://www.booktour.esy.es/api/timkiemhinhanh.php";
    ArrayList<HinhAnh> hinhAnhs;
    AdapterHinhAnh adapterHinhAnh;
    ListView lvHinhAnh;
    int PK_TourID;
    public Fragment_HinhAnh() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hinh_anh, container, false);
        lvHinhAnh = (ListView)view.findViewById(R.id.lvHinhAnh);

        Bundle bundle = getArguments();
        PK_TourID = Integer.parseInt(bundle.getString("PK_TourID"));

        hinhAnhs = new ArrayList<HinhAnh>();
        loginToServer();
        adapterHinhAnh = new AdapterHinhAnh(getActivity().getApplicationContext(),R.layout.custom_lv_hinhanh,hinhAnhs);
        lvHinhAnh.setAdapter(adapterHinhAnh);
        // Inflate the layout for this fragment
        return view;
    }

    public void loginToServer() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("FK_TourID",PK_TourID);
        client.post(URL, params,
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
                        Toast.makeText(getActivity().getApplicationContext(),"Không thể kết nối với server, vui lòng kiểm tra lại internet hoặc thử lại sau.",Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void getListTour(String response) {
        Log.d("ListTour", response);
        try {
            JSONObject obj = new JSONObject(response);
            int trangthai = Integer.parseInt(obj.getString("code"));

            String message = obj.getString("message");
            if(trangthai == 0){
                //Toast.makeText(getActivity().getApplicationContext(),message.toString(),Toast.LENGTH_LONG).show();
                JSONArray jsonArray = new JSONArray(obj.getString("result"));
                for (int i= 0;i<jsonArray.length();++i){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    HinhAnh hinhAnh = new HinhAnh();
                    hinhAnh.setPK_HinhAnhID(jsonObject.getInt("PK_HinhAnhID"));
                    hinhAnh.setTenAnh(jsonObject.getString("TenAnh"));
                    hinhAnh.setLinkAnh(jsonObject.getString("LinkAnh"));
                    hinhAnhs.add(hinhAnh);
                    adapterHinhAnh.notifyDataSetChanged();
                }

            }
            else if(trangthai == 1){
                Toast.makeText(getActivity().getApplicationContext(),message.toString(),Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
    }

}
