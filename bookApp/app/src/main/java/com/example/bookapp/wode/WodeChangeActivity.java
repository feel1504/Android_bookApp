package com.example.bookapp.wode;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookapp.Database.UserDatabaseHelper;
import com.example.bookapp.R;
import com.example.bookapp.shujiaActivity;

public class WodeChangeActivity extends AppCompatActivity {

    private EditText account;
    private EditText oldPassword;
    private EditText newPassword;
    private Button change;
    private UserDatabaseHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wode_change);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("修改");

        account = (EditText) findViewById(R.id.account_c);
        oldPassword = (EditText) findViewById(R.id.password_c);
        newPassword = (EditText) findViewById(R.id.re_password_c);
        change = (Button) findViewById(R.id.btn_change);
        dbHelper = new UserDatabaseHelper(this,"User.db",
                null,1);

        String userName = getIntent().getStringExtra("userName");
        String userPwd = getIntent().getStringExtra("userPwd");
        account.setText(userName);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userAcc = account.getText().toString();
                String oldPwd = oldPassword.getText().toString();
                String newPwd = newPassword.getText().toString();
                if(oldPwd.equals("") || newPwd.equals("")){
                    Toast.makeText(WodeChangeActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    db = dbHelper.getWritableDatabase();
                    Cursor cursor = db.query("User",null,"account = ?",new String[]{userName},null,null,null);
                    if(cursor.moveToFirst() && userName==userAcc){
                        do{
                            @SuppressLint("Range") String userName = cursor.getString(cursor.getColumnIndex("account"));
                            @SuppressLint("Range") String pwd = cursor.getString(cursor.getColumnIndex("password"));
                            if(pwd.equals(oldPwd)){
                                ContentValues values = new ContentValues();
                                values.put("password",newPwd);
                                db.update("User",values,"account=?",new String[]{userName});
//                                Intent intent = new Intent(WodeChangeActivity.this, shujiaActivity.class);
                                Toast.makeText(WodeChangeActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                startActivity(intent);
                                finish();
                            }{
                                Toast.makeText(WodeChangeActivity.this, "你的原始密码不正确", Toast.LENGTH_SHORT).show();
                            }
                        }while(cursor.moveToNext());
                    }else{
                        Toast.makeText(WodeChangeActivity.this, "你的权限只能修改当前用户", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}