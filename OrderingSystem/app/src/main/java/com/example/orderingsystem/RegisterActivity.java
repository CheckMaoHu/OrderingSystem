package com.example.orderingsystem;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    //private Spinner m_spiSpinneerAct=null//定义Spinn控件
    private final String TAG = "RegisterActivity";
    private TextView tv_note;
    private EditText et_userName;
    private EditText et_password;
    private EditText et_confirm;
    private EditText et_trueName;
    private EditText et_age;
    private ImageView iv_headPhoto;
    private Button btn_takePhoto;
    private Button btn_register;
    private Button btn_back;
    private String ufile = "logindata";
    private int result;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        res = getResources();//获取当前系统资源，必须有Context（Activity，Service）。
        //获取布局中的各组件
        tv_note = (TextView) findViewById(R.id.reg_note);
        et_userName = (EditText) findViewById(R.id.reg_userName);
        et_password = (EditText) findViewById(R.id.reg_passowrd);
        et_confirm = (EditText) findViewById(R.id.reg_confirm);
        et_trueName = (EditText) findViewById(R.id.reg_trueName);
        et_age = (EditText) findViewById(R.id.reg_age);
        btn_back = (Button) findViewById(R.id.reg_btn_login);
        btn_register = (Button) findViewById(R.id.reg_btn_register);
        btn_register.setOnClickListener(new RegisterListener());//按钮注册监听器
        btn_back.setOnClickListener(new BackListener());
    }

    class BackListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (result != RESULT_OK)
                setResult(RESULT_CANCELED);
            finish();
        }
    }

    class RegisterListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String userName;
            String password;
            String confirm;
            String trueName;
            String age;

            userName = et_userName.getText().toString();
            password = et_password.getText().toString();
            confirm = et_confirm.getText().toString();
            trueName = et_trueName.getText().toString();
            age = et_age.getText().toString();

            if (userName != null && "".equals(userName)) {
                tv_note.setText(res.getString(R.string.reg_error_userNameEmpty));
                et_userName.setFocusable(true);
                et_userName.requestFocus();
                return;
            } else if (password != null && "".equals(password)) {
                tv_note.setText(res.getString(R.string.reg_error_passwordEmpty));
                et_password.setFocusable(true);
                et_password.requestFocus();
                return;
            } else if (confirm != null && "".equals(confirm)) {
                tv_note.setText(res.getString(R.string.reg_error_confirmEmpty));
                et_confirm.setFocusable(true);
                et_confirm.requestFocus();
                return;
            } else if (!confirm.equals(password)) {
                tv_note.setText(res.getString(R.string.reg_error_confirmError));
                et_password.setText("");
                et_confirm.setText("");
                et_password.setFocusable(true);
                et_password.requestFocus();
                return;
            }
            tv_note.setText("注册成功");

            Bundle bundle = new Bundle();
            bundle.putString("userName", userName);
            bundle.putString("password", password);
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            result = RESULT_OK;

            int mode = Activity.MODE_PRIVATE;
            SharedPreferences uSetting = getSharedPreferences(ufile, mode);
            SharedPreferences.Editor editor = uSetting.edit();
            editor.putString("userName", userName);
            editor.putString("password", password);
            editor.commit();

        }
    }

}


