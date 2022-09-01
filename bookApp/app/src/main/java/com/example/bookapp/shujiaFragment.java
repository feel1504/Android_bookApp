package com.example.bookapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.bookapp.entity.Book;
import com.example.bookapp.shangcheng.BookAdapterS;

import java.util.ArrayList;
import java.util.List;

public class shujiaFragment extends Fragment {
    private View view;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_shujia_fragment, container, false);

        shujiaActivity activity = (shujiaActivity)getActivity();
        List<Book> bookList = activity.queryBook("BookS");
        RecyclerView recyView = (RecyclerView)view.findViewById(R.id.book_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);

        recyView.setLayoutManager(layoutManager);
        BookAdapterS adapter = new BookAdapterS(activity,bookList);
        recyView.setAdapter(adapter);
        return view;


        /*view = inflater.inflate(R.layout.activity_shujia_fragment, container, false);
        shujiaActivity activity = (shujiaActivity)getActivity();
        List<Book> bookList = activity.queryBook();
        BookAdapter adapter = new BookAdapter(activity,
                R.layout.book_item,bookList);
        ListView listView = (ListView)view.findViewById(R.id.book_listview);
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
        return view;*/
    }
}
