package a501.itis.kpfu.ru.themoviedbapplication.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import a501.itis.kpfu.ru.themoviedbapplication.R;
import a501.itis.kpfu.ru.themoviedbapplication.adapter.SearchedListAdapter;

/**
 * Created by Амир on 14.01.2017.
 */

public class MovieSearchFragment extends Fragment {
    RecyclerView rv;
    List list;
    EditText searchBox;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_search_fragment, container, false);
        rv = (RecyclerView) view.findViewById(R.id.searched_movies_list);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        SearchedListAdapter adapter = new SearchedListAdapter(getActivity(), list);
        rv.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchBox = (EditText) view.findViewById(R.id.edit_search_bar);
        searchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    searchBox.getText();
                    handled = true;
                }
                return handled;
            }
        });
    }

    public void setList(List list) {
        this.list = list;
    }
}
