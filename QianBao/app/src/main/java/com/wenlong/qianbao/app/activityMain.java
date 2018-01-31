package com.wenlong.qianbao.app;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.wenlong.qianbao.R;
import com.wenlong.qianbao.app.activity.activity_Home;
import com.wenlong.qianbao.app.activity.activity_Login;
import com.wenlong.qianbao.models.Auth.AuthModel;
import com.wenlong.qianbao.session.SessionManager;


/**
 * Created by Ferik Enedy on 10/14/2017.
 */

public class activityMain  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Wallet");

        setContentView(R.layout.splash_screen);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
            /* Create an Intent that will start the Menu-Activity. */
            if (SessionManager.with(getApplicationContext()).isuserlogin()) {
                Intent mainIntent = new Intent(activityMain.this, activity_Home.class);
                activityMain.this.startActivity(mainIntent);
                activityMain.this.finish();
            }
            else{
                Intent mainIntent = new Intent(activityMain.this, activity_Login.class);
                activityMain.this.startActivity(mainIntent);
                activityMain.this.finish();
            }

            }
        }, 200);
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
///
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_logout:
//                SessionManager.with(getApplicationContext()).clearsession();
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//        return true;
//    }
}