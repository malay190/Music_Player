package com.example.musicplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ListHolder> {
    private  List<Songs> songsList;

    public Adapter(List<Songs> songsList) {
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

        holder.songTile.setText(songsList.get(position).getSongTitle());
        holder.songArtist.setText(songsList.get(position).getSongArtist());

    }

    @Override
    public int getItemCount() {
        return 0;
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
