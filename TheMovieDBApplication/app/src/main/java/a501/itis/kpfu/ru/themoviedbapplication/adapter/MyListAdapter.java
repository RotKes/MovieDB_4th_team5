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
import a501.itis.kpfu.ru.themoviedbapplication.activity.FilmInfoActivity;
import a501.itis.kpfu.ru.themoviedbapplication.activity.TvSeriesInfoActivity;
import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.RequestPopularFilmObject;
import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.RequestPopularTvSeriesObject;

/**
 * Created by Амир on 13.01.2017.
 */

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyListViewHolder> {
    List<RequestPopularFilmObject> filmObjectList;
    Context mContext;
    int id;
    List<RequestPopularTvSeriesObject> tvSeriesObjectsList;
    public MyListAdapter(Context context, List list, int id) {
        this.mContext = context;
        this.id = id;
        if(id == 1) {
            filmObjectList = list;
        } else {
            tvSeriesObjectsList = list;
        }
    }

    @Override
    public MyListAdapter.MyListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new MyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyListAdapter.MyListViewHolder holder, final int position) {
        if(id == 1) {
            final RequestPopularFilmObject film = filmObjectList.get(position);
            Picasso.with(mContext)
                    .load("https://image.tmdb.org/t/p/w500" + film.getPosterPath())
                    .into(holder.imageView);
            holder.textView.setText(film.getTitle());
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, FilmInfoActivity.class);
                    intent.putExtra("filmId", filmObjectList.get(position).getId());
                    mContext.startActivity(intent);
                }
            });
        } else {
            final RequestPopularTvSeriesObject seriesObject = tvSeriesObjectsList.get(position);
            Picasso.with(mContext)
                    .load("https://image.tmdb.org/t/p/w500" + seriesObject.getPosterPath())
                    .into(holder.imageView);
            holder.textView.setText(seriesObject.getName());
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, TvSeriesInfoActivity.class);
                    intent.putExtra("seriesId", tvSeriesObjectsList.get(position).getId());
                    mContext.startActivity(intent);
                }
            });

        }


    }



    @Override
    public int getItemCount() {
        if(id == 1) {
            return filmObjectList.size();
        } else {
            return tvSeriesObjectsList.size();
        }
    }

    public class MyListViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public MyListViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
            textView = (TextView) itemView.findViewById(R.id.item_title);
        }
    }
}
