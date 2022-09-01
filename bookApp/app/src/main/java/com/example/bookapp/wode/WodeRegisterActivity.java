
package com.example.bookapp.wode;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookapp.Database.UserDatabaseHelper;
import com.example.bookapp.R;

public class WodeRegisterActivity extends AppCompatActivity {
    private EditText account;
    private EditText password;
    private EditText rePassword;
    private Button register;
    private UserDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wode_register);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("注册");

        account = (EditText) findViewById(R.id.account_r);
        password = (EditText) findViewById(R.id.password_r);
        rePassword = (EditText) findViewById(R.id.re_password_r);
        register = (Button) findViewById(R.id.btn_register);

        dbHelper = new UserDatabaseHelper(this,"User.db",
                null,1);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc = account.getText().toString();
                String pad = password.getText().toString();
                String rePad = rePassword.getText().toString();
                if(acc.equals("") || pad.equals("") || rePad.equals("")){
                    Toast.makeText(WodeRegisterActivity.this, "填写不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    Cursor cursor = db.query("User",null,"account = ?",new String[]{acc},null,null,null);
                    if(cursor.moveToFirst()){
                        Toast.makeText(WodeRegisterActivity.this, "用户已存在，不可重复创建", Toast.LENGTH_SHORT).show();
                    }else{
                        if(pad.equals(rePad)){
                            ContentValues values = new ContentValues();
                            values.put("account",acc);
                            values.put("password",pad);
                            values.put("level",1);
                            db.insert("User",null,values);
                            Toast.makeText(WodeRegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Intent intent = new Intent(WodeRegisterActivity.this,WodeLoginActivity.class);
                            intent.putExtra("acc",acc);
                            intent.putExtra("pad",pad);
                            db.close();
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(WodeRegisterActivity.this, "你两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });
    }
}