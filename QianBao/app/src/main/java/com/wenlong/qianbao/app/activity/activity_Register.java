package com.wenlong.qianbao.app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ferik Enedy on 10/15/2017.
 */

public class activity_Register extends AppCompatActivity implements BaseInterface {

    private TextInputLayout register_email;
    private TextInputLayout register_pswrd;
    private TextInputLayout register_repswrd;
    private TextInputLayout register_name;

    private EditText et_name;
    private EditText et_email;
    private EditText et_pswrd;
    private EditText et_repswrd;

    private Button btn_register;
    private TextView btn_login;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = firebaseAuth.getInstance();


        init();
        event();
    }

    public void init() {
        btn_login = (TextView) findViewById(R.id.register_login);
        btn_register = (Button) findViewById(R.id.register_register);

        register_name = (TextInputLayout) findViewById(R.id.register_name_container);
        register_email = (TextInputLayout) findViewById(R.id.register_email_container);
        register_pswrd = (TextInputLayout) findViewById(R.id.register_pswrd_container);
        register_repswrd = (TextInputLayout) findViewById(R.id.register_repswrd_container);

        et_name = (EditText) findViewById(R.id.register_name);
        et_email = (EditText) findViewById(R.id.register_email);
        et_pswrd = (EditText) findViewById(R.id.register_pswrd);
        et_repswrd = (EditText) findViewById(R.id.register_repswrd);

        progressDialog = new ProgressDialog(this);

    }

    public void event() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextActivity = new Intent(activity_Register.this, activity_Login.class);
                activity_Register.this.startActivity(nextActivity);

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean _isvalid = true;
                register_email.setErrorEnabled(false);
                register_pswrd.setErrorEnabled(false);
                register_repswrd.setErrorEnabled(false);

                if (TextUtils.isEmpty(et_email.getText())) {
                    _isvalid = false;
                    register_email.setErrorEnabled(true);
                    register_email.setError("Email is required");
                } else if (TextUtils.isEmpty(et_name.getText())) {
                    _isvalid = false;
                    register_name.setErrorEnabled(true);
                    register_name.setError("Name is required");
                }
                else if (TextUtils.isEmpty(et_pswrd.getText())) {
                    _isvalid = false;
                    register_pswrd.setErrorEnabled(true);
                    register_pswrd.setError("Password is required");
                }
                else if (TextUtils.isEmpty(et_repswrd.getText())) {
                    _isvalid = false;
                    register_repswrd.setErrorEnabled(true);
                    register_repswrd.setError("Re-Password is required");
                }
                if (_isvalid) {
//                    databaseReference.child)
//                    User userNew = new User(et_email.getText().toString(), et_pswrd.getText().toString());
//                    User.User.add(userNew);
                    progressDialog.setMessage("Registering...");
                    progressDialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(et_email.getText().toString(), et_pswrd.getText().toString())
                            .addOnCompleteListener(activity_Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(activity_Register.this, "Berhasil", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(activity_Register.this, "Gagal", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                   AuthController.getInstance(activity_Register.this).postRegister(activity_Register.this,"",paramsRegister(et_name.getText().toString(),et_pswrd.getText().toString(),et_email.getText().toString()));
                }
            }
        });
    }

    private HashMap<String,String> paramsRegister(String username, String password, String email){
        HashMap<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        map.put("email",email);
        return map;
    }

    @Override
    public <T> void onSuccess(BaseCallback<T> baseGenericCallback) {
        if(baseGenericCallback.getStatus()){
//            AuthModel data = (AuthModel) baseGenericCallback.getData();
//            data.getName();
            Intent nextActivity = new Intent(activity_Register.this, activity_Login.class);
            activity_Register.this.startActivity(nextActivity);
            finish();
        }
    }

    @Override
    public void onError(ApiErrorCallback apiErrorCallback) {

    }
}