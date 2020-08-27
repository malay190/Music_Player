package com.example.musicplayer;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Notification;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import static com.example.musicplayer.NotificationChannels.CHANNEL;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private NotificationManagerCompat notificationManager;
    public static final int GET_SONGS_REQUEST = 1;
    private Uri mSongUri;
    private Button button;
    private MediaPlayer mediaPlayer;
    Songs songs;
    private List<Songs> songsList;
    public static final String TAG = "MainActivity";


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        songsList = new ArrayList<>();
        button = findViewById(R.id.select_song);
        Adapter adapter = new Adapter(songsList);

        notificationManager = NotificationManagerCompat.from(this);
        //recyclerView = findViewById(R.id.recycler_view);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setAdapter(adapter);

        //To check if the user has already granted your app a particular permission, pass that permission into the
        // ContextCompat.checkSelfPermission() method.
        // This method returns either PERMISSION_GRANTED or PERMISSION_DENIED, depending on whether your app has the permission.
        /*if (ContextCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onCreate: permission granted");
            getSongs();
        }*/


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSongs();
            }
        });


    }

    public void getSongs() {

        Intent intent = new Intent();
        intent.setType("audio/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,GET_SONGS_REQUEST);


        /*
        //This class provides applications access to the content model.
        ContentResolver contentResolver = getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        //This interface provides random read-write access to the result set returned by a database query.
        Cursor songCursor = contentResolver.query(songUri, null, null, null, null);


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
                Uri currentLocation = Uri.parse(songCursor.getColumnName(songLocation));
                songs.setSongTitle(currentTitle);
                songs.setSongArtist(currentArtist);
                songs.setSongUri(currentLocation);
            }


        }
    }*/
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GET_SONGS_REQUEST && resultCode==RESULT_OK && data.getData()!=null){
            mSongUri =  data.getData();
            playSongs(mSongUri);

        }
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
}
