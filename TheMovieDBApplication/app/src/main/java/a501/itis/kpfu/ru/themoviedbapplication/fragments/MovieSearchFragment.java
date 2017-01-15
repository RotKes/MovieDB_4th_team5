package a501.itis.kpfu.ru.themoviedbapplication.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import a501.itis.kpfu.ru.themoviedbapplication.R;
import a501.itis.kpfu.ru.themoviedbapplication.adapter.SearchedMovieListAdapter;
import a501.itis.kpfu.ru.themoviedbapplication.fragments.async.SearchMovieFragment;

/**
 * Created by Амир on 14.01.2017.
 */

public class MovieSearchFragment extends Fragment {
    public final String SEARCH_MOVIE_REQUEST_FRAGMENT = "search_movie_request_fragment";
    RecyclerView rv;
    List list;
    EditText searchBox;
    Button submit;
    String title;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_search_fragment, container, false);
        rv = (RecyclerView) view.findViewById(R.id.searched_movies_list);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        SearchedMovieListAdapter adapter = new SearchedMovieListAdapter(getActivity(), list);
        rv.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchBox = (EditText) view.findViewById(R.id.edit_search_bar);
        searchBox.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(searchBox.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
        submit = (Button) view.findViewById(R.id.button_search_bar);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = searchBox.getText().toString().equals("") ? " " : searchBox.getText().toString();
                updateFragment(title);
            }
        });
    }

    public void setList(List list) {
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void updateFragment(String title) {
        SearchMovieFragment searchMovieFragment = (SearchMovieFragment) getFragmentManager().findFragmentByTag(SEARCH_MOVIE_REQUEST_FRAGMENT);
        if (searchMovieFragment == null) {
            searchMovieFragment = new SearchMovieFragment();
            getFragmentManager().beginTransaction()
                    .add(searchMovieFragment, SEARCH_MOVIE_REQUEST_FRAGMENT)
                    .commit();
        }
        searchMovieFragment.sendRequest(title);
    }
}
