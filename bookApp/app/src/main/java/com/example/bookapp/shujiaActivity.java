package com.example.bookapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bookapp.Database.MyDatabaseHelper;
import com.example.bookapp.entity.Book;
import com.example.bookapp.shangcheng.shuchengFragment;
import com.example.bookapp.wode.WodeLoginActivity;
import com.example.bookapp.wode.WodeRegisterActivity;
import com.example.bookapp.wode.wodeFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class shujiaActivity extends BaseActivity implements View.OnClickListener, Serializable {

    public static boolean flag;
    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private List<Book> bookList = new ArrayList<>();
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    private boolean dataFlag = true;
    private int mark;
    public boolean mark2;

    LinearLayout shucheng;
    LinearLayout shujia;
    LinearLayout wode;
    LinearLayout faxian;

    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shujia);
        setBar("行万里路 掘万卷金");

        shucheng = (LinearLayout) findViewById(R.id.shucheng);
        shujia = (LinearLayout) findViewById(R.id.shujia);
        wode = (LinearLayout) findViewById(R.id.wode);
        faxian = (LinearLayout) findViewById(R.id.faxian);

        img1 = findViewById(R.id.shujia_img);
        img2 = findViewById(R.id.shucheng_img);
        img3 = findViewById(R.id.faxian_img);
        img4 = findViewById(R.id.wode_img);

        shucheng.setOnClickListener(this);
        shujia.setOnClickListener(this);
        wode.setOnClickListener(this);
        faxian.setOnClickListener(this);

        mark = ONE;
        replaceFragment(new shujiaFragment());
        db = createDatabase("Book.db");

//        initBooks();
//        for(Book b : bookList){
//            addBook(b,"Book");
//        }

        Intent intent = getIntent();
        if(intent.getStringExtra("userName")==null){
            dataFlag = false;
            AlertDialog.Builder dialog = new AlertDialog.Builder (shujiaActivity.this);
            dialog.setTitle("请去登录或注册");
            dialog.setMessage("请去登录或注册，谢谢！");
            dialog.setCancelable(true);
            dialog.setPositiveButton("去登录", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(shujiaActivity.this, WodeLoginActivity.class);
                    startActivity(intent);
                }});
            dialog.setNegativeButton("去注册", new DialogInterface.
                    OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(shujiaActivity.this, WodeRegisterActivity.class);
                    startActivity(intent);
                }
            });
            dialog.show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(getIntent().getStringExtra("userName")!=null){
            mark2 = false;
        }
        if(!flag){
            replaceFragment(new shujiaFragment());
        }else{
            replaceFragment(new shujiaFragmentRecy());
        }
        switch (mark){
//            case ONE:
//                replaceFragment(new shujiaFragment());
//                break;
            case TWO:
                replaceFragment(new shuchengFragment());
                setBar("读一书 增一智");
                break;
            case THREE:
                replaceFragment(new wodeFragment());
                setBar("读万卷书 行万里路");
                img1.setImageResource(R.drawable.sj);
                break;
            case FOUR:
                replaceFragment(new shuchengFragment());
                setBar("非静无以成学");
                break;
            default:
                break;
        }
    }

    //修改
    public void changeBook(){

    }

    //删除数据
    public void removeBook(String bookName,String table){
        db.delete(table,"name = ?",new String[]{bookName});
        Toast.makeText(this, bookName+"删除成功！", Toast.LENGTH_SHORT).show();
        if(flag){
            replaceFragment(new shujiaFragmentRecy());
        }else{
            replaceFragment(new shujiaFragment());
        }
    }
    //查询数据
    public List<Book> queryBook(String table){
        Cursor cursor = db.query(table, null, null, null, null, null, null);
        List<Book> selectBooks = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
            // 遍历Cursor对象， 取出数据并打印
                @SuppressLint("Range") int imageId = cursor
                        .getInt(cursor.getColumnIndex("imageId"));
                @SuppressLint("Range") String name = cursor
                        .getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String type = cursor
                        .getString(cursor.getColumnIndex("type"));
                @SuppressLint("Range") String readRecord = cursor
                        .getString(cursor.getColumnIndex("readRecord"));
                @SuppressLint("Range") String size = cursor
                        .getString(cursor.getColumnIndex("size"));
                @SuppressLint("Range") String author = cursor
                        .getString(cursor.getColumnIndex("author"));
                Book book = new Book();
                book.setImageId(imageId);
                book.setName(name);
                book.setType(type);
                book.setReadRecord(readRecord);
                book.setSize(size);
                book.setAuthor(author);
                Log.d("book_data",book.toString());
                selectBooks.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        if (dataFlag){
            return selectBooks;
        }
        return new ArrayList<>();
    }
    //添加数据
    public void addBook(Book book,String table){
        ContentValues values = new ContentValues();
        values.put("imageId",book.getImageId());
        values.put("name",book.getName());
        values.put("type",book.getType());
        values.put("readRecord",book.getReadRecord());
        values.put("size",book.getSize());
        values.put("author",book.getAuthor());
        db.insert(table,null,values);
    }
    //测试数据
    private void initBooks() {
        Book b1 = new Book(R.drawable.p1,"灵境行者","科幻","第二章","122.9万字","卖报小郎");
        Book b2 = new Book(R.drawable.p2,"夜的命名术","都市","第三章","372万字","会说话的");
        Book b3 = new Book(R.drawable.p3,"光阴之外","仙侠","第一章","70.54万字","耳根");
        Book b4 = new Book(R.drawable.p4,"深空彼岸","都市","第二十章","341.5万字","辰东");
        Book b5 = new Book(R.drawable.p5,"不科学御兽","玄幻","第四章","305.4万字","清泉流响");
        Book b6 = new Book(R.drawable.p6,"大夏文圣","仙侠","第七章","182.63万字","七月末时");
        Book b7 = new Book(R.drawable.p7,"神秘复苏","仙侠","第一章","458.72万字","佛前献花");
        Book b8 = new Book(R.drawable.p8,"这游戏也太真实了","轻小说","第二章","302.47万字","晨星");

        bookList.add(b1); bookList.add(b2);
        bookList.add(b3); bookList.add(b4);
        bookList.add(b5); bookList.add(b6);
        bookList.add(b7); bookList.add(b8);
    }

    //创建数据库
    public SQLiteDatabase createDatabase(String dbName){
        dbHelper = new MyDatabaseHelper(this,dbName,null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db;
    }
    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shujia:
                if(mark2){
                    replaceFragment(new test_frame());
                }else{
                    replaceFragment(new shujiaFragment());
                    mark = ONE;
                    light(ONE);
                }
                break;
            case R.id.shucheng:
                replaceFragment(new shuchengFragment());
                mark = TWO;
                setBar("读一书 增一智");
                light(TWO);
                break;
            case R.id.wode:
                replaceFragment(new wodeFragment());
                mark = THREE;
                light(THREE);
                setBar("读万卷书 行万里路");
                break;
            case R.id.faxian:
                replaceFragment(new test_frame());
                mark = FOUR;
                light(FOUR);
                setBar("非静无以成学");
                break;
        }
    }

    //切换页面
    public void replaceFragment(Fragment fragment){
        img1.setImageResource(R.drawable.shujia);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_fragment,fragment);
        transaction.commit();
    }



    //设置菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shujia_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_book:
                Toast.makeText(this, "添加图书", Toast.LENGTH_SHORT).show();
                break;
            case R.id.down_book:
                Toast.makeText(this, "正在下载", Toast.LENGTH_SHORT).show();
                break;
            case R.id.change_layout:
                if(flag){
                    replaceFragment(new shujiaFragment());
                }else{
                    replaceFragment(new shujiaFragmentRecy());
                }
                img2.setImageResource(R.drawable.sc);
                flag = !flag;
                break;
        }
        return true;
    }
    @SuppressLint("ResourceType")
    private void light(int id){
        switch (id){
            case ONE:
                img1.setImageResource(R.drawable.shujia);
                img2.setImageResource(R.drawable.sc);
                img3.setImageResource(R.drawable.fx);
                img4.setImageResource(R.drawable.wd);
                break;
            case TWO:
                img1.setImageResource(R.drawable.sj);
                img2.setImageResource(R.drawable.shucheng);
                img3.setImageResource(R.drawable.fx);
                img4.setImageResource(R.drawable.wd);
                break;
            case THREE:
                img1.setImageResource(R.drawable.sj);
                img2.setImageResource(R.drawable.sc);
                img3.setImageResource(R.drawable.fx);
                img4.setImageResource(R.drawable.wode);
                break;
            case FOUR:
                img1.setImageResource(R.drawable.sj);
                img2.setImageResource(R.drawable.sc);
                img3.setImageResource(R.drawable.faxian);
                img4.setImageResource(R.drawable.wd);
                break;
            default:
                break;
        }
    }
}