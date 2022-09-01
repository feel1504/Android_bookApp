package com.example.bookapp.shujia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookapp.MainActivity;
import com.example.bookapp.Database.MyDatabaseHelper;
import com.example.bookapp.R;
import com.example.bookapp.entity.Book;
import com.example.bookapp.shujiaActivity;


public class ShujiaChangeActivity extends AppCompatActivity {

    private MyDatabaseHelper helper;
    ImageView bookImageId;
    TextView bookName;
    TextView bookType;
    TextView bookReadRecord;
    TextView bookSize;
    TextView bookAuthor;
    Button changeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shujia_change);
        Book book = (Book) getIntent().getSerializableExtra("book");

        bookName = (TextView) findViewById(R.id.change_name);
        bookType = (TextView) findViewById(R.id.change_type);
        bookReadRecord = (TextView) findViewById(R.id.change_readCord);
        bookSize = (TextView) findViewById(R.id.change_size);
        bookAuthor = (TextView) findViewById(R.id.change_author);
        changeBtn = (Button) findViewById(R.id.change_btn);
        bookImageId = (ImageView) findViewById(R.id.change_imageId);

        bookName.setText(book.getName());
        bookType.setText(book.getType());
        bookReadRecord.setText(book.getReadRecord());
        bookSize.setText(book.getSize());
        bookAuthor.setText(book.getAuthor());
        helper = new MyDatabaseHelper(this,"Book.db",null,1);
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put("name",bookName.getText().toString());
                values.put("type",bookType.getText().toString());
                values.put("readRecord",bookReadRecord.getText().toString());
                values.put("size",bookSize.getText().toString());
                values.put("author",bookAuthor.getText().toString());
                db.update("Book",values,"name = ?",new String[]{bookName.getText().toString()});
                Toast.makeText(ShujiaChangeActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                db.close();
//                Intent intent = new Intent(ShujiaChangeActivity.this, shujiaActivity.class);
//                startActivity(intent);
                finish();
            }
        });


    }
}