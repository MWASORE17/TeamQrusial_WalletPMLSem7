package com.wenlong.qianbao.app.fragment.transaksi.txn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.wenlong.qianbao.R;
import com.wenlong.qianbao.base.ApiErrorCallback;
import com.wenlong.qianbao.base.BaseCallback;
import com.wenlong.qianbao.base.BaseInterface;
import com.wenlong.qianbao.controllers.AuthController;
import com.wenlong.qianbao.session.SessionManager;;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Ferik Enedy on 11/22/2017.
 */

public class tabExpenseFragment extends Fragment implements BaseInterface{

    public tabExpenseFragment() {
    }
//    ArrayList<String> arrayList = new ArrayList<String>();
    private FloatingActionButton btnSave;
    private TextInputEditText etDeskripsi;
    private TextInputEditText etValue;
    private Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AuthController.getInstance(getActivity()).getTotalBalance(this, SessionManager.with(getActivity()).getuserloggedin().getId().toString());
        View rootView = inflater.inflate(R.layout.txn_expense_fragment, container, false);
        btnSave = (FloatingActionButton) rootView.findViewById(R.id.fab_save_expense);
        etDeskripsi = (TextInputEditText) rootView.findViewById(R.id.expense_et_deskripsi);
        etValue = (TextInputEditText) rootView.findViewById(R.id.expense_value);
        spinner = (Spinner) rootView.findViewById(R.id.spinnerExpense);

        return rootView;

    }

    private HashMap<String,String> paramExpense(String kategori, String nilai, String nilai_expense, String deskripsi, String tanggal, String id_user){
        HashMap<String,String> map = new HashMap<>();
        map.put("kategori",kategori);
        map.put("nilai",nilai);
        map.put("nilai_expense",nilai_expense);
        map.put("deskripsi",deskripsi);
        map.put("tanggal",tanggal);
        map.put("id_user",id_user);

        return map;
    }

    public void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext());

        // set title dialog
        alertDialogBuilder.setTitle("Not Enough Balance");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Your expense is higher then your balance, go to income to add your balance")
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.dismiss();
                    }
                });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public <T> void onSuccess(BaseCallback<T> baseGenericCallback) {
        final Integer historyData = (Integer) baseGenericCallback.getData();
        final int saldo = historyData;
        etValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = s.toString();
                if(input.startsWith("0")){
                    if (input.length() > 0) {
                        etValue.setText(input.substring(1));
                    } else {
                        etValue.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = SessionManager.with(getActivity()).getuserloggedin().getId().toString();
                String expense = etValue.getText().toString().trim();
                String getDeskripsi = etDeskripsi.getText().toString().trim();
                String kategori = spinner.getSelectedItem().toString();
                String tanggal = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                int getNominal = Integer.parseInt(expense);
                if(expense==null||expense.trim().equals("")){
                    Toast.makeText(getContext(), "Value is Empety", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(!(getNominal <= saldo)){
                        showDialog();
                        etDeskripsi.setText("");
                        etValue.setText("");
                    }
                    else {
                        AuthController.getInstance(getContext()).postTransaksi(paramExpense(kategori,"0",expense,getDeskripsi,tanggal, id));
                        Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
                        etValue.setText("");
                        etDeskripsi.setText("");
                    }
                }
            }
        });


    }

    @Override
    public void onError(ApiErrorCallback apiErrorCallback) {

    }
}