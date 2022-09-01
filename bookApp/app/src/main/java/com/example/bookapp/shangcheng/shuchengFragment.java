package com.example.bookapp.shangcheng;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp.BookAdapter;
import com.example.bookapp.R;
import com.example.bookapp.entity.Book;
import com.example.bookapp.shujiaActivity;

import java.util.List;

public class shuchengFragment extends Fragment {

    private View view;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_shucheng_fragment, container, false);

        shujiaActivity activity = (shujiaActivity) getActivity();
        List<Book> bookList = activity.queryBook("Book");
        RecyclerView recyView = (RecyclerView) view.findViewById(R.id.shucheng_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);

        recyView.setLayoutManager(layoutManager);
        BookAdapter adapter = new BookAdapter(activity, bookList);
        recyView.setAdapter(adapter);
        return view;
    }
}
