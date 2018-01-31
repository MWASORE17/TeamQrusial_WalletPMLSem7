package com.wenlong.qianbao.app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.wenlong.qianbao.R;
import com.wenlong.qianbao.base.ApiErrorCallback;
import com.wenlong.qianbao.base.BaseCallback;
import com.wenlong.qianbao.base.BaseInterface;
import com.wenlong.qianbao.controllers.AuthController;
import com.wenlong.qianbao.models.Auth.AuthModel;
import com.wenlong.qianbao.session.SessionManager;

import java.util.HashMap;

/**
 * Created by Ferik Enedy on 10/14/2017.
 */

public class activity_Login extends AppCompatActivity implements BaseInterface{

    private EditText email,password;
    private Button login;
    private TextView register;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog = new ProgressDialog(this);

        firebaseAuth = firebaseAuth.getInstance();
        init();
        event();
    }

    private void init(){
        progressBar = (ProgressBar) findViewById(R.id.login_progress);
        register = (TextView) findViewById(R.id.register);
        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);
        login = (Button) findViewById(R.id.login);
    }

    private void event(){

        progressBar.setVisibility(View.GONE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AuthController.getInstance(activity_Login.this).postLogin(activity_Login.this,"",paramLogin(email.getText().toString(),password.getText().toString()));
                progressBar.setVisibility(View.VISIBLE);
//                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString());
//                        .addOnCompleteListener(activity_Login.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
////                                    progressDialog.setMessage("Logging");
////                                    progressDialog.show();
//                                    progressBar.setVisibility(View.GONE);
//                                    startActivity(new Intent(getApplicationContext(),activity_Home.class));
////                                    Toast.makeText(activity_Login.this, "Welcome", Toast.LENGTH_SHORT).show();
//                                    finish();
//                                } else {
////                                    progressDialog.setMessage("Logging");
////                                    progressDialog.show();
//                                    progressBar.setVisibility(View.GONE);
//                                    Toast.makeText(activity_Login.this, "Login Gagal", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
            }
        });

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent (activity_Login.this, activity_Register.class);
                activity_Login.this.startActivity(nextActivity);
                finish();
            }
        });
    }


    private HashMap<String,String> paramLogin(String email, String password){
        HashMap<String,String> map = new HashMap<>();
        map.put("email",email);
        map.put("password",password);
        return map;
    }

    @Override
    public <T> void onSuccess(BaseCallback<T> baseGenericCallback) {
        if(baseGenericCallback.getStatus()){

            final AuthModel authModel = (AuthModel) baseGenericCallback.getData();
            progressDialog.setMessage("Logging...");
            progressDialog.show();
            Intent nextActivity = new Intent (activity_Login.this, activity_Home.class);
            activity_Login.this.startActivity(nextActivity);
            finish();
            Toast.makeText(activity_Login.this, "Welcome " +authModel.getName(), Toast.LENGTH_SHORT).show();
            SessionManager.with(this).createsession(authModel);
        } else{
            Toast.makeText(activity_Login.this, "Failed to sign in", Toast.LENGTH_SHORT).show();
        }
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(ApiErrorCallback apiErrorCallback) {
        progressBar.setVisibility(View.GONE);
    }
}