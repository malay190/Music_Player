package com.example.musicplayer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ListHolder> {
    private  List<Songs> songsList;
    private static final String TAG = "Adapter";

    public SongAdapter(List<Songs> songsList) {
        this.songsList = songsList;
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout,parent, false);
        return new ListHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        Songs currentSongs = songsList.get(position);

        holder.songTile.setText(currentSongs.getSongTitle());
        holder.songArtist.setText(currentSongs.getSongArtist());
        holder.showImage.setImageDrawable(null);

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+ songsList.size());
        return songsList.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder{
        private TextView songTile;
        private TextView songArtist;
        private ImageView showImage;

        public ListHolder(@NonNull View itemView) {
            super(itemView);

            songTile = itemView.findViewById(R.id.song_name);
            songArtist = itemView.findViewById(R.id.artist_name);
            showImage = itemView.findViewById(R.id.show_image);

        }
    }
}
