package com.tassioauad.moviecheck.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.tassioauad.moviecheck.R;
import com.tassioauad.moviecheck.model.entity.Image;
import com.tassioauad.moviecheck.model.entity.Media;
import com.tassioauad.moviecheck.model.entity.Video;

import java.util.List;

public class MediaListAdapter extends RecyclerView.Adapter<MediaListAdapter.ViewHolder> implements View.OnClickListener {

    private List<Media> mediaList;
    private OnItemClickListener<Media> mediaOnItemClickListener;

    public MediaListAdapter(List<Media> mediaList, OnItemClickListener<Media> mediaOnItemClickListener) {
        this.mediaList = mediaList;
        this.mediaOnItemClickListener = mediaOnItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listviewitem_media, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Media media = mediaList.get(position);

        holder.itemView.setTag(media);

        String thumbnailUrl = null;
        if(media instanceof Video) {
            thumbnailUrl = String.format(holder.itemView.getContext().getString(R.string.youtube_image_url), ((Video)media).getKey());
        } else if( media instanceof Image) {
            thumbnailUrl = holder.itemView.getContext().getString(R.string.imagetmdb_baseurl) + ((Image) media).getFilePath();
        }

        holder.progressBar.setVisibility(View.VISIBLE);
        Picasso.with(holder.itemView.getContext()).load(thumbnailUrl).into(holder.imageViewThumbnail, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                holder.progressBar.setVisibility(View.GONE);
                Picasso.with(holder.itemView.getContext()).load(R.drawable.noimage).into(holder.imageViewThumbnail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mediaList.size();
    }

    @Override
    public void onClick(View view) {
        Media media = (Media) view.getTag();
        mediaOnItemClickListener.onClick(media, view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewThumbnail;
        private ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewThumbnail = (ImageView) itemView.findViewById(R.id.imageview_thumbnail);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
        }
    }

}
