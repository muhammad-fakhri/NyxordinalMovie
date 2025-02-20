package id.cybershift.fakhrimovie.ui.tvshow;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import id.cybershift.fakhrimovie.R;
import id.cybershift.fakhrimovie.data.source.local.entity.TVShowEntity;
import id.cybershift.fakhrimovie.ui.detail.DetailActivity;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder> {
    private ArrayList<TVShowEntity> listTVShows = new ArrayList<>();
    private Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    void setListTVShows(ArrayList<TVShowEntity> tvshows) {
        if (tvshows != null) {
            listTVShows.clear();
            listTVShows.addAll(tvshows);
        }
    }

    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new TVShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowViewHolder holder, int position) {
        holder.bind(listTVShows.get(position), holder, position);
    }

    @Override
    public int getItemCount() {
        return listTVShows.size();
    }

    class TVShowViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView tvName, tvDescription;

        TVShowViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.item_poster);
            tvName = itemView.findViewById(R.id.item_name);
            tvDescription = itemView.findViewById(R.id.item_description);
        }

        void bind(TVShowEntity tvshowItem, TVShowViewHolder viewHolder, final int position) {

            Glide.with(viewHolder.itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w185" + tvshowItem.getPoster())
                    .apply(new RequestOptions().override(200, 250))
                    .into(viewHolder.imgPoster);
            tvName.setText(tvshowItem.getTitle());
            tvDescription.setText(tvshowItem.getOverview());

            //Set click listener
            viewHolder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_ITEM_TYPE, "tv");
                intent.putExtra(DetailActivity.EXTRA_ITEM_POSITION, position);
                activity.startActivity(intent);
            });
        }
    }
}
