package a501.itis.kpfu.ru.themoviedbapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import a501.itis.kpfu.ru.themoviedbapplication.R;
import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.searching.SearchedMovie;

/**
 * Created by Амир on 14.01.2017.
 */

public class SearchedListAdapter extends RecyclerView.Adapter<SearchedListAdapter.SearchedListViewHolder> {
    List<SearchedMovie> listOfMovies;
    Context mContext;

    public SearchedListAdapter(Context mContext, List list) {
        this.mContext = mContext;
        this.listOfMovies = list;
    }

    @Override
    public SearchedListAdapter.SearchedListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searched_item, parent, false);
        return new SearchedListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchedListAdapter.SearchedListViewHolder holder, int position) {
        final SearchedMovie movie = listOfMovies.get(position);
        holder.title.setText(movie.getTitle());
        holder.info.setText(movie.getOverview());
        holder.rating.setText(Double.toString(movie.getVoteAverage()));
        Picasso.with(mContext)
                .load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return listOfMovies.size();
    }

    public class SearchedListViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView info;
        TextView rating;

        public SearchedListViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.searched_item_image);
            title = (TextView) itemView.findViewById(R.id.searched_item_title);
            info = (TextView) itemView.findViewById(R.id.searched_item_info);
            rating = (TextView) itemView.findViewById(R.id.searched_item_rating);
        }
    }
}
