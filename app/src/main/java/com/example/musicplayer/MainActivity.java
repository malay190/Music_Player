package com.example.musicplayer;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public Songs songs;
    RecyclerView recyclerView;
    private NotificationManagerCompat notificationManager;
    public static final int GET_SONGS_REQUEST = 1;
    private Uri mSongUri;
    private Button button;
    private MediaPlayer mediaPlayer;
    private List<Songs> songsList;
    public static final String TAG = "MainActivity";


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getListOfSongs();

        songsList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        SongAdapter adapter = new SongAdapter(songsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        notificationManager = NotificationManagerCompat.from(this);


    }

    private void playSongs(Uri mSongUri){
        if(mediaPlayer==null){
            mediaPlayer = MediaPlayer.create(this,mSongUri);
        }
        mediaPlayer.start();
        Intent intent = new Intent(this,NotificationServices.class);
        this.startService(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();

    }

    private void getListOfSongs(){

        songsList = new ArrayList<>();

        String[] projection = new String[]{
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DISPLAY_NAME

        };

        String sortOrder = MediaStore.Audio.Media.DISPLAY_NAME + " ASC";

        Cursor cursor = getApplicationContext().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,null,null,sortOrder);

        int artistColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST );
        int songColumn = cursor.getColumnIndex( MediaStore.Audio.Media.DISPLAY_NAME);
        int id = cursor.getColumnIndex(MediaStore.Audio.Media._ID);

        while (cursor.moveToNext()){
            String artistName = cursor.getColumnName(artistColumn);
            String songName = cursor.getColumnName(songColumn);

            Uri contentUri = ContentUris.withAppendedId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id);

            songs = new Songs(songName,artistName,contentUri);

            songsList.add(songs);



        }

        cursor.close();



    }
}
