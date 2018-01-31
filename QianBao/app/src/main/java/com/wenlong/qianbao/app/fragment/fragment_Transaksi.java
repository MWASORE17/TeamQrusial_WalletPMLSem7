package com.wenlong.qianbao.app.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenlong.qianbao.R;
import com.wenlong.qianbao.app.fragment.transaksi.txn.txnPagerAdapter;

/**
 * Created by Ferik Enedy on 11/22/2017.
 */

public class fragment_Transaksi extends Fragment {

    private txnPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    public static fragment_Transaksi newInstance() {
        fragment_Transaksi fragment = new fragment_Transaksi();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSectionsPagerAdapter = new txnPagerAdapter(getChildFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
//        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#9933ff"));
//        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
//        tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#9933ff"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transaksi, container, false);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
//        inflater.inflate(R.menu.menu_books, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}