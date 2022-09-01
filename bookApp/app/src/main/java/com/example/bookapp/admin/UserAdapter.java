package com.example.bookapp.admin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.Database.UserDatabaseHelper;
import com.example.bookapp.R;
import com.example.bookapp.entity.User;
import com.example.bookapp.wode.WodeChangeActivity;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.VieHolder> {
    private List<User> mUser;
    private UserDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private EditText getCategoryName;
    private EditText getSort;
    private String oldAcc;

    public UserAdapter(List<User> userList){
        mUser = userList;
    }

    @NonNull
    @Override
    public VieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        VieHolder holder = new VieHolder(view);

        holder.userChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new UserDatabaseHelper(v.getContext(),"User.db",null,1);
                LayoutInflater inflater = LayoutInflater.from(v.getContext());
                View viewCategory = inflater.inflate(R.layout.test_alert, null);
                getCategoryName = viewCategory.findViewById(R.id.input_category_name);
                getSort = viewCategory.findViewById(R.id.input_sort);

                int position = holder.getAdapterPosition();
                User user = mUser.get(position);
                getCategoryName.setText(user.getAccount());
                oldAcc = user.getAccount();
                getSort.setText(user.getPassword());
                AlertDialog.Builder builder = new AlertDialog
                        .Builder(v.getContext());
                builder.setTitle("请输入你要修改的内容");
                builder.setView(viewCategory);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String acc = getCategoryName.getText().toString();
                        String pwd = getSort.getText().toString();
                        change(acc, pwd, oldAcc, v);
                        Intent intent = new Intent(v.getContext(), AdminIndexActivity.class);
                        AdminIndexActivity activity = (AdminIndexActivity) v.getContext();
                        v.getContext().startActivity(intent);
                        activity.finish();
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
            }
        });
        holder.userRise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new UserDatabaseHelper(v.getContext(),"User.db",null,1);
                int position = holder.getAdapterPosition();
                User user = mUser.get(position);
                oldAcc = user.getAccount();
                int level = user.getLevel();
                if(level == 0){
                    Toast.makeText(v.getContext(), "该用户已经管理员了", Toast.LENGTH_SHORT).show();
                }else{
                    changeLevel(oldAcc,v,0);
                    Intent intent = new Intent(v.getContext(), AdminIndexActivity.class);
                    AdminIndexActivity activity = (AdminIndexActivity) v.getContext();
                    v.getContext().startActivity(intent);
                    activity.finish();
                }
            }
        });
        holder.userDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new UserDatabaseHelper(v.getContext(),"User.db",null,1);
                int position = holder.getAdapterPosition();
                User user = mUser.get(position);
                oldAcc = user.getAccount();
                int level = user.getLevel();
                if(level == 1){
                    Toast.makeText(v.getContext(), "该用户已经是普通用户了", Toast.LENGTH_SHORT).show();
                }else{
                    changeLevel(oldAcc,v,1);
                    Intent intent = new Intent(v.getContext(), AdminIndexActivity.class);
                    AdminIndexActivity activity = (AdminIndexActivity) v.getContext();
                    v.getContext().startActivity(intent);
                    activity.finish();
                }
            }
        });
        holder.userDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new UserDatabaseHelper(v.getContext(),"User.db",null,1);
                db = dbHelper.getWritableDatabase();
                int position = holder.getAdapterPosition();
                User user = mUser.get(position);
                oldAcc = user.getAccount();
                int row = db.delete("User", "account=?", new String[]{oldAcc});
                if (row==1){
                    Toast.makeText(v.getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(v.getContext(), "删除失败", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(v.getContext(), AdminIndexActivity.class);
                AdminIndexActivity activity = (AdminIndexActivity) v.getContext();
                v.getContext().startActivity(intent);
                activity.finish();
            }
        });
        return holder;
    }

    //修改普通用户
    private void change(String acc,String pwd,String oldAcc,View v){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("account",acc);
        values.put("password",pwd);
        Log.e("aass",acc+":"+pwd+":"+oldAcc);
        int user = db.update("User", values, "account=?",new String[]{oldAcc});
        Log.d("aass",new String(String.valueOf(user)));
        if (user==1){
            Toast.makeText(v.getContext(), "修改成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(v.getContext(), "修改失败", Toast.LENGTH_SHORT).show();
        }
    }

    //修改普通用户
    private void changeLevel(String oldAcc,View v,int level){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("level",level);
        int user = db.update("User", values, "account=?",new String[]{oldAcc});
        if (user==1){
            Toast.makeText(v.getContext(), "修改成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(v.getContext(), "修改失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull VieHolder holder, int position) {
        User user = mUser.get(position);
        holder.userName.setText(user.getAccount());
        holder.userPassword.setText(user.getPassword());
        if(user.getLevel()==0){
            holder.userLevel.setText("管理员");
        }else{
            holder.userLevel.setText("普通用户");
        }
    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }

    public class VieHolder extends RecyclerView.ViewHolder {
        TextView userName;
        TextView userPassword;
        TextView userLevel;
        Button userChange;
        Button userRise;
        Button userDecline;
        Button userDelete;

        public VieHolder(@NonNull View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.username_u);
            userPassword = (TextView) itemView.findViewById(R.id.password_u);
            userLevel = (TextView) itemView.findViewById(R.id.level);
            userChange = (Button) itemView.findViewById(R.id.change_admin);
            userRise = (Button) itemView.findViewById(R.id.rise);
            userDecline = (Button) itemView.findViewById(R.id.decline);
            userDelete = (Button) itemView.findViewById(R.id.delete);
        }
    }
}
