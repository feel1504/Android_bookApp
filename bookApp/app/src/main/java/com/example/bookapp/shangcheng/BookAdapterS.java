package com.example.bookapp.shangcheng;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.R;
import com.example.bookapp.book.BookContent;
import com.example.bookapp.entity.Book;
import com.example.bookapp.shujia.ShujiaChangeActivity;
import com.example.bookapp.shujiaActivity;

import java.util.List;

public class BookAdapterS extends RecyclerView.Adapter<BookAdapterS.ViewHolder> {

    private List<Book> mBookList;
    private shujiaActivity mActivity;

    @NonNull
    @Override
    public BookAdapterS.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.books_item, parent, false);
        final BookAdapterS.ViewHolder holder = new BookAdapterS.ViewHolder(view);

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
    public void onBindViewHolder(@NonNull BookAdapterS.ViewHolder holder, int position) {
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
    public BookAdapterS(shujiaActivity activity, List<Book> bookList){
        mBookList = bookList;
        mActivity = activity;
    }



//    private int resourceId;
//    public BookAdapter(@NonNull Context context, int resource, @NonNull List<Book> objects) {
//        super(context, resource, objects);
//        resourceId = resource;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//
//        Book book = getItem(position);
//        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
//        if(view==null){
//            Log.e("sssa","sss");
//        }else{
//            Log.e("viewa",view.toString());
//            ImageView bookImage = (ImageView)view.findViewById(R.id.book_image);
//            TextView bookName = (TextView) view.findViewById(R.id.book_name);
//            TextView bookType = (TextView) view.findViewById(R.id.book_type);
//            TextView bookReadRecord = (TextView) view.findViewById(R.id.book_readRecord);
//            TextView bookSize = (TextView) view.findViewById(R.id.book_size);
//            TextView bookAuthor = (TextView) view.findViewById(R.id.book_author);
//
//            bookImage.setImageResource(book.getImageId());
//            bookName.setText(book.getName());
//            bookType.setText(book.getType());
//            bookReadRecord.setText(book.getReadRecord());
//            bookSize.setText(book.getSize());
//            bookAuthor.setText(book.getAuthor());
//
//        }
//
//        return view;
//    }

}
