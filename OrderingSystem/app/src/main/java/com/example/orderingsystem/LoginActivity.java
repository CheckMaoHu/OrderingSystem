package com.example.orderingsystem;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    public MyApplication app;
    private Button btnReg, btnLogin;
    private EditText et_userName, et_password;
    private CheckBox remuser;
    private String ufile = "logindata";
    private SharedPreferences message;
    private String username, ukey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_userName = findViewById(R.id.editUid);
        et_password = findViewById(R.id.editPwd);
        remuser = findViewById(R.id.cbRemember);

        app = (MyApplication) getApplication();

        btnReg = findViewById(R.id.btnReg);
        btnLogin = findViewById(R.id.btnLogin);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.setLoginstatus(false);
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = et_userName.getText().toString().trim();
                ukey = et_password.getText().toString().trim();
                if (username.isEmpty() || ukey.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "账号密码不能为空！", Toast.LENGTH_SHORT).show();
                    et_userName.setFocusable(true);
                    et_userName.requestFocus();
                    return;
                } else {
                    message = LoadUserPreference();
                    String uname = message.getString("userName", "");
                    String uukey = message.getString("password", "");

                    if (!username.equalsIgnoreCase(uname) || !ukey.equalsIgnoreCase(uukey)) {
                        Toast.makeText(LoginActivity.this, "账号或密码输入错误！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (remuser.isChecked()) {
                        WriteUserPreference(username, ukey, 1);
                    } else {
                        WriteUserPreference(username, ukey, 0);
                    }
                    app.setLoginstatus(true);

                    Intent intent = new Intent(LoginActivity.this, DishMainActivity.class);
                    setResult(RESULT_OK, intent);
                    // TODO
                    app.setUid(10);
                    finish();
                }
            }
        });

        message = LoadUserPreference();
        username = message.getString("userName", "");
        ukey = message.getString("password", "");
        int ifrem = message.getInt("ifrem", 0);

        if (ifrem == 1) {
            et_userName.setText(username);
            et_password.setText(ukey);
            remuser.setChecked(true);

        } else {
            et_userName.setText("");
            et_password.setText("");
            remuser.setChecked(false);
        }


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bundle udata = data.getExtras();
                String username = udata.getString("userName");
                String password = udata.getString("password");
                et_userName.setText(username);
                et_password.setText(password);
            }
        }
    }

    private SharedPreferences LoadUserPreference() {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences uSetting = getSharedPreferences(ufile, mode);
        return uSetting;
    }

    private void WriteUserPreference(String name, String key, int ifrem) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences uSetting = getSharedPreferences(ufile, mode);
        SharedPreferences.Editor editor = uSetting.edit();
        editor.putString("userName", name);
        editor.putString("password", key);
        editor.putInt("ifrem", ifrem);
        editor.commit();
    }
}
