package com.example.bookapp.wode;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookapp.Database.UserDatabaseHelper;
import com.example.bookapp.MainActivity;
import com.example.bookapp.R;
import com.example.bookapp.admin.AdminIndexActivity;
import com.example.bookapp.shujiaActivity;
import com.example.bookapp.utils.Flag;

public class WodeLoginActivity extends AppCompatActivity implements Flag {
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox adminLogin;
    private SQLiteDatabase db;
    private UserDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wode_login);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("登录");

        accountEdit = (EditText) findViewById(R.id.account_l);
        passwordEdit = (EditText) findViewById(R.id.password_l);
        login = (Button) findViewById(R.id.btn_login);
        adminLogin = (CheckBox) findViewById(R.id.admin_login);
        dbHelper = new UserDatabaseHelper(this,"User.db",
                null,1);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc = accountEdit.getText().toString();
                String pwd = passwordEdit.getText().toString();

                db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("User",null,"account = ?",new String[]{acc},null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        @SuppressLint("Range") String userName = cursor.getString(cursor.getColumnIndex("account"));
                        @SuppressLint("Range") String userPwd = cursor.getString(cursor.getColumnIndex("password"));
                        @SuppressLint("Range") int level = cursor.getInt(cursor.getColumnIndex("level"));

                        if(adminLogin.isChecked()){
                            if(level == 0){
                                judge(userName,userPwd,pwd, AdminIndexActivity.class);
                            }else{
                                Toast.makeText(WodeLoginActivity.this, "该账户不是管理员账号", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            judge(userName,userPwd,pwd,shujiaActivity.class);
                        }
                    }while(cursor.moveToNext());
                }else{
                    Toast.makeText(WodeLoginActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void judge(String userName,String userPwd,String pwd,Class cls){
        if(userPwd.equals(pwd)){
            Intent intent = new Intent(WodeLoginActivity.this, cls);
            intent.putExtra("userName",userName);
            intent.putExtra("userPwd",userPwd);
            startActivity(intent);
            Toast.makeText(WodeLoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            finish();
            db.close();
        }else{
            Toast.makeText(WodeLoginActivity.this, "密码不对", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}