package com.example.bookapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.book.BookContent;
import com.example.bookapp.entity.Book;
import com.example.bookapp.shujia.ShujiaChangeActivity;

import java.util.List;

public class BookAdapter2 extends RecyclerView.Adapter<BookAdapter2.ViewHolder> {

    private List<Book> mBookList;
    private shujiaActivity mActivity;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item2, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        //显示内容
        holder.bookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Book book = mBookList.get(position);
                Intent intent = new Intent(mActivity, BookContent.class);
                intent.putExtra("content","书名："+book.getName()+"/n作者："+book.getAuthor());
                intent.putExtra("bookName",book.getName());
                v.getContext().startActivity(intent);
            }
        });
        //移除
        holder.bookMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Book book = mBookList.get(position);
                mActivity.removeBook(book.getName(),"BookS");
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = mBookList.get(position);
        holder.bookImage.setImageResource(book.getImageId());
        holder.bookName.setText(book.getName());
        holder.bookType.setText(book.getType());
        holder.bookReadRecord.setText(book.getReadRecord());
        holder.bookSize.setText(book.getSize());
        holder.bookAuthor.setText(book.getAuthor());
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookName;
        TextView bookType;
        TextView bookReadRecord;
        TextView bookSize;
        TextView bookAuthor;
        Button bookMove;
        public ViewHolder(View view) {
            super(view);
            bookImage = (ImageView)view.findViewById(R.id.book_image);
            bookName = (TextView) view.findViewById(R.id.book_name);
            bookType = (TextView) view.findViewById(R.id.book_type);
            bookReadRecord = (TextView) view.findViewById(R.id.book_readRecord);
            bookSize = (TextView) view.findViewById(R.id.book_size);
            bookAuthor = (TextView) view.findViewById(R.id.book_author);
            bookMove = (Button) view.findViewById(R.id.move_book);
        }
    }
    public BookAdapter2 (shujiaActivity activity,List<Book> bookList){
        mBookList = bookList;
        mActivity = activity;
    }
}
