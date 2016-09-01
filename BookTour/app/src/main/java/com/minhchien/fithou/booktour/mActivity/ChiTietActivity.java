package com.minhchien.fithou.booktour.mActivity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.minhchien.fithou.booktour.R;
import com.minhchien.fithou.booktour.mAdapter.AdapterListViewChiTiet;
import com.minhchien.fithou.booktour.mFragment.Fragment_ChiTietTour;
import com.minhchien.fithou.booktour.mFragment.Fragment_HinhAnh;
import com.minhchien.fithou.booktour.mFragment.Fragment_TTGiaTour;
import com.minhchien.fithou.booktour.mObjects.MenuTour;

import java.util.ArrayList;

public class ChiTietActivity extends AppCompatActivity {

    ListView lvTour;
    ArrayList<MenuTour> tourList;
    AdapterListViewChiTiet adapterListViewChiTiet;
    String HinhAnh,TenTour,Gia,ThoiGian,PK_TourID,NgayKhoiHanh,DiemXuatPhat,LichTrinh,TTGiaTour,DiemDen,PhuongTien,Tour,TenDiaDiem;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Bundle data = getIntent().getExtras();
        HinhAnh = data.getString("HinhAnh");
        TenTour = data.getString("TenTour");
        Gia = data.getString("Gia");
        ThoiGian = data.getString("ThoiGian");
        NgayKhoiHanh = data.getString("NgayKhoiHanh");
        LichTrinh = data.getString("LichTrinh");
        DiemDen = data.getString("DiemDen");
        PhuongTien = data.getString("PhuongTien");
        TTGiaTour = data.getString("TTGiaTour");
        TenDiaDiem = data.getString("ten");
        PK_TourID = data.getString("PK_TourID");

        toolbar.setTitle(TenDiaDiem.toString());

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chi_tiet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_chi_tiet, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            MenuTour menuTour = new MenuTour();
            menuTour.setHinhAnh(HinhAnh.toString());
            menuTour.setTenTour(TenTour.toString());
            menuTour.setGia(Integer.parseInt(Gia.toString()));
            menuTour.setThoiGian(ThoiGian.toString());
            menuTour.setNgayKhoiHanh(NgayKhoiHanh.toString());
            menuTour.setLichTrinh(LichTrinh.toString());
            menuTour.setDiemDen(DiemDen.toString());
            menuTour.setPhuongTien(PhuongTien.toString());
            menuTour.setTTGiaTour(TTGiaTour.toString());
            menuTour.setPK_TourID(Integer.parseInt(PK_TourID.toString()));
            switch (position){
                case 0:
                    Fragment fragment = new Fragment_ChiTietTour();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("tour",menuTour);
                    fragment.setArguments(bundle);
                    return fragment;
                case 1:
                    Fragment fragment2 = new Fragment_TTGiaTour();
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("TTGiaTour",TTGiaTour.toString());
                    fragment2.setArguments(bundle2);
                    return fragment2;
                case 2:
                    Fragment fragment3 = new Fragment_HinhAnh();
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("PK_TourID",PK_TourID.toString());
                    fragment3.setArguments(bundle3);
                    return fragment3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Chương Trình Tour";
                case 1:
                    return "Giá Tour";
                case 2:
                    return "Hình Ảnh";
            }
            return null;
        }
    }
}
