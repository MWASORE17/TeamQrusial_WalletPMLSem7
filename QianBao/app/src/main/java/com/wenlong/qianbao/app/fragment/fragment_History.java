package com.wenlong.qianbao.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenlong.qianbao.R;
import com.wenlong.qianbao.app.fragment.history.ExpenseAdapter;
import com.wenlong.qianbao.app.fragment.history.historyAdapter;

/**
 * Created by Ferik Enedy on 11/14/2017.
 */

public class fragment_History extends Fragment {

    private historyAdapter sectionPagerAdapter;
    private ViewPager viewPager;

    public static fragment_History newInstance() {
        fragment_History fragment = new fragment_History();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sectionPagerAdapter = new historyAdapter(getChildFragmentManager());
        viewPager= (ViewPager) view.findViewById(R.id.containerr);
        viewPager.setAdapter(sectionPagerAdapter);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabss);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);
        return v;
    }
}
