package com.wenlong.qianbao.app.fragment.history;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by Ferik Enedy on 12/06/2017.
 */

public class historyAdapter extends FragmentPagerAdapter {
    public historyAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        // Returning the current Tabs
        switch (position) {
            case 0:
                tabToday x = new tabToday();
                return x;
            case 1:
                tabWeekly y = new tabWeekly();
                return y;
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Today";
            case 1:
                return "Weekly";
        }
        return null;
    }
}
