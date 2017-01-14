package a501.itis.kpfu.ru.themoviedbapplication.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import a501.itis.kpfu.ru.themoviedbapplication.R;
import a501.itis.kpfu.ru.themoviedbapplication.adapter.MyListAdapter;


public class TvSerialsListFragment extends Fragment {
    RecyclerView rv;
    List list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tv_series_fragment, container, false);
        rv = (RecyclerView) view.findViewById(R.id.tv_series_recycler);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext(), LinearLayoutManager.HORIZONTAL, false));
        MyListAdapter adapter = new MyListAdapter(getActivity(), list, 2);
        rv.setAdapter(adapter);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setList( List list) {
        this.list = list;
    }
}
