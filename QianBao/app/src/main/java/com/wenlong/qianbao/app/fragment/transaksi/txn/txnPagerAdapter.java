package com.wenlong.qianbao.app.fragment.transaksi.txn;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Ferik Enedy on 11/25/2017.
 */

public class txnPagerAdapter extends FragmentPagerAdapter{
    public txnPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        // Returning the current Tabs
        switch (position) {
            case 0:
                tabIncomeFragment tab1 = new tabIncomeFragment();
                return tab1;
            case 1:
                tabExpenseFragment tab2 = new tabExpenseFragment();
                return tab2;
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
                return "Income";
            case 1:
                return "Expense";
        }
        return null;
    }
}
