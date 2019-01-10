package com.mvp.yj.study_mvp.view.main.Movie;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mvp.yj.study_mvp.R;
import com.mvp.yj.study_mvp.listener.OnItemClickListener;
import com.mvp.yj.study_mvp.model.MovieList;
import com.mvp.yj.study_mvp.view.main.Movie.adapter.MovieAdapterContract;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    ArrayList<MovieList> Items;
    OnItemClickListener onItemClickListener;

    public MovieAdapter(Context context, ArrayList<MovieList> m_list) {
        this.context = context;
        this.Items = m_list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.m_title)
        TextView m_title;
        @BindView(R.id.m_star)
        RatingBar m_star;
        @BindView(R.id.m_year)
        TextView m_year;
        @BindView(R.id.m_director)
        TextView m_director;
        @BindView(R.id.m_actor)
        TextView m_actor;

        public ViewHolder(@NonNull View v) {
            super(v);
            ButterKnife.bind(this, v);
        }


    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        if(holder ==null) return;
        holder.m_star.setNumStars(5);
        holder.m_title.setText(Html.fromHtml(Items.get(position).getTitle()));
        holder.m_director.setText(Items.get(position).getDirector());
        holder.m_year.setText(Items.get(position).getPubDate());
        holder.m_actor.setText(Items.get(position).getActor());
        holder.m_star.setRating(Items.get(position).getUserRating()/2);
        final String link = Items.get(position).getLink();

        String url = Items.get(position).getImage();
        Glide.with(context).load(url).into(holder.img);

        // click시 web 연결
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(link));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Items !=null ? Items.size() : 0;
    }
}
