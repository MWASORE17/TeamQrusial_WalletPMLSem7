package com.wenlong.qianbao.app.fragment.history;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wenlong.qianbao.R;
import com.wenlong.qianbao.base.ApiErrorCallback;
import com.wenlong.qianbao.base.BaseCallback;
import com.wenlong.qianbao.base.BaseInterface;
import com.wenlong.qianbao.controllers.AuthController;
import com.wenlong.qianbao.models.Transaction.HistoryData;
import com.wenlong.qianbao.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ferik Enedy on 12/06/2017.
 */

public class tabToday extends Fragment implements BaseInterface{


    public tabToday(){

    }

    private ExpenseAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.his_today_fragment, container, false);
        recyclerView = rootView.findViewById(R.id.today_recycleView);

        AuthController.getInstance(getActivity()).getHistoryTransaksi(this,SessionManager.with(getActivity()).getuserloggedin().getId().toString());
        return rootView;
    }

    public void showListExpense(ArrayList<HistoryData> historyDataArrayList){

        adapter = new ExpenseAdapter(getActivity(), historyDataArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public <T> void onSuccess(BaseCallback<T> baseGenericCallback) {
        if(baseGenericCallback.getStatus()){
            ArrayList<HistoryData> historyDataArrayList = (ArrayList<HistoryData>) baseGenericCallback.getData_array();
            showListExpense(historyDataArrayList);
        }
    }

    @Override
    public void onError(ApiErrorCallback apiErrorCallback) {

    }
}
