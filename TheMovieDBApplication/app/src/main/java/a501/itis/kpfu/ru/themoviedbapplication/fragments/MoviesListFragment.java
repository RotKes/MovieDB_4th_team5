package a501.itis.kpfu.ru.themoviedbapplication.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import a501.itis.kpfu.ru.themoviedbapplication.R;
import a501.itis.kpfu.ru.themoviedbapplication.adapter.MyListAdapter;

/**
 * Created by Амир on 13.01.2017.
 */

public class MoviesListFragment extends Fragment {
    RecyclerView rv;
    List list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page_movies_list, container, false);
        rv = (RecyclerView) view.findViewById(R.id.movies_list);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext(), LinearLayoutManager.HORIZONTAL, false));
        MyListAdapter adapter = new MyListAdapter(getActivity(), list ,1 );
        rv.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    public void setList(List list) {
        this.list = list;
    }
}
