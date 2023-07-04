package com.example.mymusic.db;

import org.litepal.crud.LitePalSupport;
//播放列表
public class PlayingTable extends LitePalSupport {

    public String artist;   //歌手
    public String title;     //歌曲名
    public String songUrl;     //歌曲地址
    public String imgUrl;
    public boolean isOnlineMusic;

    public PlayingTable(String songUrl, String title, String artist, String imgUrl, boolean isOnlineMusic) {
        this.title = title;
        this.artist = artist;
        this.songUrl = songUrl;
        this.imgUrl = imgUrl;
        this.isOnlineMusic = isOnlineMusic;
    }
}