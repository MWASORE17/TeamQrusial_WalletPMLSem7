package com.wenlong.qianbao.app.fragment.transaksi.txn;

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
import android.widget.Toast;

import com.wenlong.qianbao.R;
import com.wenlong.qianbao.base.ApiErrorCallback;
import com.wenlong.qianbao.base.BaseCallback;
import com.wenlong.qianbao.base.BaseInterface;
import com.wenlong.qianbao.controllers.AuthController;
import com.wenlong.qianbao.session.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Ferik Enedy on 11/22/2017.
 */

public class tabIncomeFragment extends Fragment implements BaseInterface{

    public tabIncomeFragment() {

    }
    private FloatingActionButton btnSave;
    private TextInputEditText etDeskripsi;
    private TextInputEditText etValue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.txn_income_fragment, container, false);
        btnSave = (FloatingActionButton) rootView.findViewById(R.id.fab_save_income);
        etDeskripsi = (TextInputEditText) rootView.findViewById(R.id.income_et_deskripsi);
        etValue = (TextInputEditText) rootView.findViewById(R.id.income_value);

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
                String income = etValue.getText().toString().trim();
                String deskripsi = etDeskripsi.getText().toString().trim();
                int getNominal = Integer.parseInt(income);
                if(income==null||income.trim().equals("")){
                    Toast.makeText(getContext(), "Value is Empety", Toast.LENGTH_SHORT).show();
                }
                else{
//                    arrayList.add(getInput + " = IDR" + getDeskripsi);
                    String tanggal = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                    AuthController.getInstance(getContext()).postTransaksi(paramIncome("Income",income,"0",deskripsi,tanggal,id));
                    Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
                    etValue.setText("");
                    etDeskripsi.setText("");
                }
            }
        });

        return rootView;
    }
    private HashMap<String,String> paramIncome(String kategori, String nilai, String nilai_expense,String deskripsi, String tanggal,String id_user){
        HashMap<String,String> map = new HashMap<>();
        map.put("kategori",kategori);
        map.put("nilai",nilai);
        map.put("nilai_expense",nilai_expense);
        map.put("deskripsi",deskripsi);
        map.put("tanggal",tanggal);
        map.put("id_user",id_user);

        return map;
    }

    @Override
    public <T> void onSuccess(BaseCallback<T> baseGenericCallback) {
        if(baseGenericCallback.getStatus()){
        }
    }

    @Override
    public void onError(ApiErrorCallback apiErrorCallback) {
        Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
    }
}
