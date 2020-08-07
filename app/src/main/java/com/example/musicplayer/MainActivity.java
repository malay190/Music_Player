package com.example.musicplayer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Songs songs;
    private List<Songs> songsList;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //This class provides applications access to the content model.
        ContentResolver contentResolver = getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        //This interface provides random read-write access to the result set returned by a database query.
        Cursor songCursor = contentResolver.query(songUri, null, null, null, null);

        //Adapter adapter = new Adapter(songsList);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // recyclerView.setAdapter(adapter);

        //Move the cursor to the first row.
        //This method will return false if the cursor is empty.
        if (songCursor != null && songCursor.moveToFirst()) {
            int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int songLocation = songCursor.getPosition();
            int count = songCursor.getCount();

            for (int i = 0; i <= count; i++) {
                String currentTitle = songCursor.getColumnName(songTitle);
                String currentArtist = songCursor.getColumnName(songArtist);
                songs.setSongTitle(currentTitle);
                songs.setSongArtist(currentArtist);
            }

            for()

        }


    }
}
