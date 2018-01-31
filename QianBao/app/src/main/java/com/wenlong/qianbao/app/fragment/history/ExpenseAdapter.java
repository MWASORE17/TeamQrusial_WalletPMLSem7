package com.wenlong.qianbao.app.fragment.history;

import android.content.Context;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wenlong.qianbao.R;
import com.wenlong.qianbao.models.Auth.AuthModel;
import com.wenlong.qianbao.models.Transaction.HistoryData;

import java.util.ArrayList;

/**
 * Created by Ferik Enedy on 12/01/2017.
 */

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.SingleItemRowHolder> {

    private Context context;

    private ArrayList<HistoryData> expenseHistoryArrayList;
    public ExpenseAdapter(Context context, ArrayList<HistoryData> expenseHistoryArrayList) {
        this.context = context;
        this.expenseHistoryArrayList = expenseHistoryArrayList;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_history, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, final int i) {

        String tanggal =expenseHistoryArrayList.get(i).getTanggal();
        NumberFormat nf = NumberFormat.getInstance();
        DecimalFormat df = (DecimalFormat)nf;
        df.applyPattern(",###");


//        Toast.makeText(context,String.valueOf(a), Toast.LENGTH_SHORT).show();
        if(expenseHistoryArrayList.get(i).getIncome()!=0){
            holder.tv_income.setText(String.valueOf("+ IDR. " + df.format(expenseHistoryArrayList.get(i).getIncome())));
//            holder.tv_expense.setText(String.valueOf("- IDR. " + df.format(expenseHistoryArrayList.get(i).getNilai_expense())));
        }
        else{
            holder.tv_expense.setText(String.valueOf("- IDR. " + df.format(expenseHistoryArrayList.get(i).getNilai_expense())));

        }
        holder.tv_category.setText(expenseHistoryArrayList.get(i).getKategori());
        holder.tv_deskripsi.setText(expenseHistoryArrayList.get(i).getDeskripsi());
        holder.tv_date.setText(tanggal);
//        holder.exCount.setText(expenseHistoryArrayList.get(i).getCountFoods());
    }


    @Override
    public int getItemCount() {
        return (null != expenseHistoryArrayList ? expenseHistoryArrayList.size() : 0);
    }

    public static class SingleItemRowHolder extends RecyclerView.ViewHolder {
        protected TextView tv_expense;
        protected TextView tv_category;
        protected TextView tv_income;
        protected TextView tv_date;
        protected TextView tv_deskripsi;
        protected TextView exCount;
        public RelativeLayout viewBackground, viewForeground;

        public SingleItemRowHolder(View view) {
            super(view);

            this.tv_expense = (TextView) view.findViewById(R.id.tv_value);
            this.tv_date    = (TextView) view.findViewById(R.id.tv_date);
            this.tv_income  = (TextView) view.findViewById(R.id.tv_valuein);
            this.tv_category  = (TextView) view.findViewById(R.id.tv_kategori);
            this.tv_deskripsi = (TextView) view.findViewById(R.id.tv_deskripsi);
            this.exCount = (TextView) view.findViewById(R.id.exCount);
        }
    }
    public void removeItem(int i) {
        expenseHistoryArrayList.remove(i);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(i);
    }

    public void restoreItem(HistoryData historyData, int i) {
        expenseHistoryArrayList.add(i, historyData);
        // notify item added by position
        notifyItemInserted(i);
    }
}