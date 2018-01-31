package com.wenlong.qianbao.app.fragment;

import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.wenlong.qianbao.R;
import com.wenlong.qianbao.base.ApiErrorCallback;
import com.wenlong.qianbao.base.BaseCallback;
import com.wenlong.qianbao.base.BaseInterface;
import com.wenlong.qianbao.controllers.AuthController;
import com.wenlong.qianbao.models.Auth.AuthModel;
import com.wenlong.qianbao.models.Transaction.HistoryData;
import com.wenlong.qianbao.models.Transaction.Kategori;
import com.wenlong.qianbao.session.SessionManager;

import java.util.ArrayList;

/**
 * Created by Ferik Enedy on 11/14/2017.
 */

public class fragment_Dashboard extends Fragment implements BaseInterface{

    private PieChart pieChart;
    private TextView tv_orang,tv_money,tv_bnykPengeluaran,tv_countOthers,tv_countFoods,tv_countTraveling,tv_countShopping,tv_countTransport;

    private int others;
    private int foodies;
    private int traveling;
    private int shopping;
    private int transport;

    private int totalExpense;
    final String[] kategoriArray = {
            "Others",
            "Foods and Drinks",
            "Traveling",
            "Shopping",
            "Transport"
    };
    public fragment_Dashboard(){}
    public static fragment_Dashboard newInstance() {
        fragment_Dashboard fragment = new fragment_Dashboard();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        init(v);
        String id_user = SessionManager.with(getActivity()).getuserloggedin().getId().toString();
        AuthController.getInstance(getActivity()).getExpense(this, id_user);
        AuthController.getInstance(getActivity()).getTotalBalance(this,id_user);
        AuthController.getInstance(getActivity()).getUsername(this, id_user);
        AuthController.getInstance(getActivity()).getPersenItem(this, id_user);
        setChart(v);

        return v;
    }

    public void setChart(View view){
        pieChart = (PieChart)view.findViewById(R.id.idPieChart);
        pieChart.setUsePercentValues(true);
    }

    public void init(View v){
        tv_orang             = (TextView) v.findViewById(R.id.tv_nama);
        tv_money             = (TextView) v.findViewById(R.id.totalMoney);
        tv_bnykPengeluaran   = (TextView) v.findViewById(R.id.exCount);
        tv_countOthers       = (TextView) v.findViewById(R.id.count_Others);
        tv_countFoods        = (TextView) v.findViewById(R.id.count_Foods);
        tv_countShopping     = (TextView) v.findViewById(R.id.count_Shopping);
        tv_countTraveling    = (TextView) v.findViewById(R.id.count_Traveling);
        tv_countTransport    = (TextView) v.findViewById(R.id.count_Transport);
    }

    @Override
    public <T> void onSuccess(BaseCallback<T> baseGenericCallback) {
        if(baseGenericCallback.getStatus()){
            NumberFormat nf = NumberFormat.getInstance();
            DecimalFormat df = (DecimalFormat)nf;
            df.applyPattern(",###");
            if (baseGenericCallback.getMessage().equals("Success get balance")){
                final Integer balance = (Integer) baseGenericCallback.getData();
                tv_money.setText(String.valueOf(df.format(balance))+ " IDR");
            }
            else if(baseGenericCallback.getMessage().equals("Done")){
                final Kategori kategori = (Kategori) baseGenericCallback.getData();
                this.others = kategori.getKategoriOthers();
                this.foodies = kategori.getFoodsandDrinks();
                this.traveling = kategori.getTravelling();
                this.shopping = kategori.getShopping();
                this.transport = kategori.getTransport();

                if(others!=0){
                    totalExpense+=1;
                }
                if(foodies!=0){
                    totalExpense+=1;
                }
                if(traveling!=0){
                    totalExpense+=1;
                }
                if(shopping!=0){
                    totalExpense+=1;
                }
                if(traveling!=0){
                    totalExpense+=1;
                }

                final ArrayList<String> xVals = new ArrayList<String>();
                final ArrayList<Entry> yValues = new ArrayList<Entry>();

                if(others!=0){
                    float percentOther = convertToPercent(others);
                    yValues.add(new Entry(percentOther,0));
                    xVals.add(kategoriArray[0]);
                }
                if(foodies!=0){
                    float percentFoodes = convertToPercent(foodies);
                    yValues.add(new Entry(percentFoodes,1));
                    xVals.add(kategoriArray[1]);
                }
                if(traveling!=0){
                    float percentTraveling = convertToPercent(traveling);
                    yValues.add(new Entry(percentTraveling,2));
                    xVals.add(kategoriArray[2]);
                }
                if(shopping!=0){
                    float percentShoping = convertToPercent(shopping);
                    yValues.add(new Entry(percentShoping,3));
                    xVals.add(kategoriArray[3]);
                }
                if(transport!=0){
                    float percentTransport = convertToPercent(transport);
                    yValues.add(new Entry(percentTransport,4));
                    xVals.add(kategoriArray[4]);
                }
                PieDataSet dataSet = new PieDataSet(yValues, "Expenses Results");

                PieData data = new PieData(xVals, dataSet);
                data.setValueFormatter(new PercentFormatter());
                data.setValueFormatter(new DefaultValueFormatter(0));
                pieChart.setData(data);
                pieChart.setDescription("Expenses Chart");
                pieChart.setDrawHoleEnabled(false);
                pieChart.setTransparentCircleRadius(25f);
                pieChart.setHoleRadius(25f);
                dataSet.setColors(ColorTemplate.PASTEL_COLORS);
                data.setValueTextSize(13f);
                data.setValueTextColor(Color.WHITE);
                pieChart.animateXY(1400, 1400);

                tv_countTraveling.setText(String.valueOf(df.format(kategori.getTravelling())));
                tv_countTransport.setText(String.valueOf(df.format(kategori.getTransport())));
                tv_countShopping.setText(String.valueOf(df.format(kategori.getShopping())));
                tv_countFoods.setText(String.valueOf(df.format(kategori.getFoodsandDrinks())));
                tv_countOthers.setText(String.valueOf(df.format(kategori.getKategoriOthers())));


            }
            else if(baseGenericCallback.getMessage().equals("Expense")){
                final Integer totalExpense = (Integer) baseGenericCallback.getData();
            }
            else{
                final AuthModel authModel = (AuthModel) baseGenericCallback.getData();
                tv_orang.setText(authModel.getName() + "'s Balance :");
            }
        }
    }

    @Override
    public void onError(ApiErrorCallback apiErrorCallback) {

    }

    private float convertToPercent(Integer value){
        return (value*100)/totalExpense;
    }
}