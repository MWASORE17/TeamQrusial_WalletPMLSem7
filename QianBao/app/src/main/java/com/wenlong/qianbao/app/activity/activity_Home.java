package com.wenlong.qianbao.app.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.wenlong.qianbao.R;
import com.wenlong.qianbao.app.fragment.fragment_Dashboard;
import com.wenlong.qianbao.app.fragment.fragment_History;
import com.wenlong.qianbao.app.fragment.fragment_Transaksi;
import com.wenlong.qianbao.session.SessionManager;

/**
 * Created by Ferik Enedy on 11/12/2017.
 */

public class activity_Home extends AppCompatActivity {

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        event();

        Fragment selectedFragment = fragment_Dashboard.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, selectedFragment);
        transaction.commit();
    }

    public void init(){
        navigationView = (BottomNavigationView) findViewById(R.id.btmNav);
    }
    public void event(){
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.action_dashboard:
//                        selectedFragment = fragment_dbsh.newInstance();
                        selectedFragment = fragment_Dashboard.newInstance();
                        break;
                    case R.id.action_transaction:
                        selectedFragment = fragment_Transaksi.newInstance();
                        break;
                    case R.id.action_history:
                        selectedFragment = fragment_History.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, selectedFragment);
                transaction.commit();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                showDialog();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title dialog
        alertDialogBuilder.setTitle("Are you sure ?");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        SessionManager.with(getApplicationContext()).clearsession();
//                        System.exit(0);
                        Intent mainIntent = new Intent(activity_Home.this, activity_Login.class);
                        activity_Home.this.startActivity(mainIntent);
                        activity_Home.this.finish();

                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}