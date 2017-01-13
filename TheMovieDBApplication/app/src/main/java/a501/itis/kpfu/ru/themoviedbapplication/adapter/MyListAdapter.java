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
import a501.itis.kpfu.ru.themoviedbapplication.apiObjects.RequestPopularFilmObject;

/**
 * Created by Амир on 13.01.2017.
 */

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyListViewHolder> {
    List<RequestPopularFilmObject> filmObjectList;
    Context mContext;

    public MyListAdapter(List<RequestPopularFilmObject> filmObjectList, Context context) {
        this.filmObjectList = filmObjectList;
        this.mContext = context;
    }

    @Override
    public MyListAdapter.MyListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_page_item, parent, false);
        return new MyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyListAdapter.MyListViewHolder holder, int position) {
        final RequestPopularFilmObject film = filmObjectList.get(position);
        Picasso.with(mContext)
                .load("https://image.tmdb.org/t/p/w500" + film.getPosterPath())
                .into(holder.imageView);
        holder.textView.setText(film.getTitle());
    }

    @Override
    public int getItemCount() {
        return filmObjectList.size();
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
