package a501.itis.kpfu.ru.themoviedbapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import a501.itis.kpfu.ru.themoviedbapplication.R;
import a501.itis.kpfu.ru.themoviedbapplication.activity.TvSeriesInfoActivity;
import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.searching.SearchedTvShow;

/**
 * Created by Амир on 15.01.2017.
 */

public class SearchedTvShowListAdapter extends RecyclerView.Adapter<SearchedTvShowListAdapter.SearchedTvShowListViewHolder> {
    List<SearchedTvShow> listOfTvShows;
    Context mContext;

    public SearchedTvShowListAdapter(Context mContext, List list) {
        this.mContext = mContext;
        this.listOfTvShows = list;
    }
    @Override
    public SearchedTvShowListAdapter.SearchedTvShowListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searched_item, parent, false);
        return new SearchedTvShowListAdapter.SearchedTvShowListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchedTvShowListAdapter.SearchedTvShowListViewHolder holder, final int position) {
        final SearchedTvShow tvShow = listOfTvShows.get(position);
        holder.title.setText(tvShow.getName());
        holder.info.setText(tvShow.getOverview());
        holder.rating.setText("Rating : " + Double.toString(tvShow.getVoteAverage()));
        Picasso.with(mContext)
                .load("https://image.tmdb.org/t/p/w500" + tvShow.getPosterPath())
                .into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TvSeriesInfoActivity.class);
                intent.putExtra("seriesId", listOfTvShows.get(position).getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfTvShows == null ? 0 : listOfTvShows.size();
    }

    public class SearchedTvShowListViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        TextView info;
        TextView rating;

        public SearchedTvShowListViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.searched_item_image);
            title = (TextView) itemView.findViewById(R.id.searched_item_title);
            info = (TextView) itemView.findViewById(R.id.searched_item_info);
            rating = (TextView) itemView.findViewById(R.id.searched_item_rating);
        }

    }
}
