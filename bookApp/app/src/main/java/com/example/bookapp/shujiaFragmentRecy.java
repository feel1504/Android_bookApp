package com.example.bookapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.bookapp.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class shujiaFragmentRecy extends Fragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_shujia_fragment_recy, container, false);

        shujiaActivity activity = (shujiaActivity)getActivity();
        List<Book> bookList = activity.queryBook("BookS");
        RecyclerView recyView = (RecyclerView)view.findViewById(R.id.book_recy_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyView.setLayoutManager(layoutManager);
        BookAdapter2 adapter2 = new BookAdapter2(activity,bookList);
        recyView.setAdapter(adapter2);
        return view;
    }
}
