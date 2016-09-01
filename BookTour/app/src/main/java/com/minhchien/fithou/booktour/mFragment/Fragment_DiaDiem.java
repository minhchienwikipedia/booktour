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
import com.minhchien.fithou.booktour.mActivity.DiaDiemActivity;
import com.minhchien.fithou.booktour.mAdapter.AdapterListViewDiaDiem;
import com.minhchien.fithou.booktour.mObjects.DiaDiem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_DiaDiem extends Fragment {
    ListView lvDiaDiem;
    ArrayList<DiaDiem> list;
    AdapterListViewDiaDiem adapterListViewDiaDiem;
    String URL = "http://www.booktour.esy.es/api/danhsachdiemden.php";
    public Fragment_DiaDiem() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dia_diem, container, false);
        lvDiaDiem = (ListView)view.findViewById(R.id.lvDiaDiem);
        list = new ArrayList<DiaDiem>();

        loginToServer();

        adapterListViewDiaDiem = new AdapterListViewDiaDiem(getActivity().getApplicationContext(),R.layout.custom_lv_diadiem,list);
        lvDiaDiem.setAdapter(adapterListViewDiaDiem);

        lvDiaDiem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DiaDiem item = list.get(position);
                String id1 = String.valueOf(item.getId());
                String ten = item.getTenDiaDiem();
                Intent intent = new Intent(getActivity().getApplicationContext(), DiaDiemActivity.class);
                intent.putExtra("id",id1.toString());
                intent.putExtra("ten",ten.toString());
                //Toast.makeText(getActivity().getApplicationContext(),id1.toString(),Toast.LENGTH_LONG).show();
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
                    DiaDiem diaDiem = new DiaDiem();
                    diaDiem.setId(jsonObject.getInt("PK_DiaDiemID"));
                    diaDiem.setTenDiaDiem(jsonObject.getString("TenDiaDiem"));
                    diaDiem.setAnhDaiDien(jsonObject.getString("AnhDaiDien"));
                    list.add(diaDiem);
                    adapterListViewDiaDiem.notifyDataSetChanged();
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
