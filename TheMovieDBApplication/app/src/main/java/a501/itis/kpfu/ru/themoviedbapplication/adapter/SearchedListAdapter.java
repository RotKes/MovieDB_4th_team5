package a501.itis.kpfu.ru.themoviedbapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a501.itis.kpfu.ru.themoviedbapplication.R;

/**
 * Created by Амир on 14.01.2017.
 */

public class SearchedListAdapter extends RecyclerView.Adapter<SearchedListAdapter.SearchedListViewHolder> {
    @Override
    public SearchedListAdapter.SearchedListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searched_item, parent, false);
        return new SearchedListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchedListAdapter.SearchedListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SearchedListViewHolder extends RecyclerView.ViewHolder {
        public SearchedListViewHolder(View itemView) {
            super(itemView);
        }
    }
}
