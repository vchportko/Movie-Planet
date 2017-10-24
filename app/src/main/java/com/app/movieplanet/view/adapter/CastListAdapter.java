package com.app.movieplanet.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.app.movieplanet.R;
import com.app.movieplanet.model.entity.Cast;

import java.util.List;

public class CastListAdapter extends RecyclerView.Adapter<CastListAdapter.ViewHolder> implements View.OnClickListener {

    private List<Cast> castList;
    private OnItemClickListener<Cast> castOnItemClickListener;

    public CastListAdapter(List<Cast> castList, OnItemClickListener<Cast> castOnItemClickListener) {
        this.castList = castList;
        this.castOnItemClickListener = castOnItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listviewitem_cast, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Cast cast = castList.get(position);

        holder.itemView.setTag(cast);
        holder.textViewName.setText(cast.getName());
        holder.textViewCharacter.setText(cast.getCharacter());
        String posterUrl = holder.itemView.getContext().getString(R.string.imagetmdb_baseurl) + cast.getProfilePath();
        holder.progressBar.setVisibility(View.VISIBLE);
        Picasso.with(holder.itemView.getContext()).load(posterUrl).placeholder(R.drawable.noimage).into(holder.imageViewPoster, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                holder.progressBar.setVisibility(View.GONE);
                Picasso.with(holder.itemView.getContext()).load(R.drawable.noimage).into(holder.imageViewPoster);
            }
        });
    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    @Override
    public void onClick(View view) {
        Cast cast = (Cast) view.getTag();
        castOnItemClickListener.onClick(cast, view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewPoster;
        private TextView textViewName;
        private TextView textViewCharacter;
        private ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.imageview_poster);
            textViewName = (TextView) itemView.findViewById(R.id.textview_name);
            textViewCharacter = (TextView) itemView.findViewById(R.id.textview_character);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
        }
    }

}
