package com.wenlong.qianbao.models.Transaction;

import java.util.ArrayList;

/**
 * Created by Ferik Enedy on 12/19/2017.
 */

public class HistoryData {
    private int id_transaksi;
    private int id_user;
    private String kategori;
    private int nilai;
    private int nilai_expense;
    private String deskripsi;
    private String tanggal;
//    public static ArrayList<HistoryData> arrayRecordExpense = new ArrayList<>();

    public HistoryData() {}

    public int getId_transaksi() {
        return id_transaksi;
    }

    public int getId_user() {
        return id_user;
    }

    public String getKategori() {
        return kategori;
    }

    public int getIncome() {
        return nilai;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public int getNilai_expense() {
        return nilai_expense;
    }

}
