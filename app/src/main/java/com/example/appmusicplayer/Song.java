package com.example.appmusicplayer;

import android.net.Uri;

public class Song {
    private String Title;
    private int File;


    public Song(String title, int file){
        Title= title;
        File= file;
    }
    public String getTitle(){
        return Title;
    }
    public Integer getFile(){
        return File;
    }
}
