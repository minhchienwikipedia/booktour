package com.minhchien.fithou.booktour.mFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minhchien.fithou.booktour.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_TTGiaTour extends Fragment {


    public Fragment_TTGiaTour() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ttgia_tour, container, false);
        TextView tvTTGiaTour = (TextView)view.findViewById(R.id.tvTTGiaTour);
        tvTTGiaTour.setMovementMethod(new ScrollingMovementMethod());
        Bundle bundle = getArguments();
        tvTTGiaTour.setText(bundle.getString("TTGiaTour").toString());
        // Inflate the layout for this fragment
        return view;
    }

}
