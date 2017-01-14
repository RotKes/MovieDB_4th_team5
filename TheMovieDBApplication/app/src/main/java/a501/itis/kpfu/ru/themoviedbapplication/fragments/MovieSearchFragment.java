package a501.itis.kpfu.ru.themoviedbapplication.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import a501.itis.kpfu.ru.themoviedbapplication.R;
import a501.itis.kpfu.ru.themoviedbapplication.adapter.SearchedListAdapter;

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
        SearchedListAdapter adapter = new SearchedListAdapter(getActivity(), list);
        rv.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchBox = (EditText) view.findViewById(R.id.edit_search_bar);
        submit = (Button) view.findViewById(R.id.button_search_bar);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = searchBox.getText().toString();
            }
        });
    }

    public void setList(List list) {
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    /*public static void recreatingFragment(){
        movieSearchFragment.setList(list);
        getFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, movieSearchFragment, MovieSearchFragment.class.getName())
                .commit();
    }*/
}
