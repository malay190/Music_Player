package com.example.musicplayer;

import android.net.Uri;

public class Songs {

    private String songTitle;
    private String songArtist;
    private Uri songUri;



    public Songs(String songTitle, String songArtist, Uri songUri) {
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songUri = songUri;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public Uri getSongUri() {
        return songUri;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public void setSongUri(Uri songUri) {
        this.songUri = songUri;
    }
}
