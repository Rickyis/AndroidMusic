package com.example.mymusic.bean;

import java.util.Objects;

public class Music {

    public String artist;     //歌手
    public String title;      //歌曲名
    public String songUrl;    //歌曲地址
    public String imgUrl;     //专辑图片地址
    public boolean isOnlineMusic;

    public Music(String songUrl, String title, String artist, String imgUrl, boolean isOnlineMusic) {
        this.songUrl = songUrl;
        this.title = title;
        this.artist = artist;
        this.imgUrl = imgUrl;
        this.isOnlineMusic = isOnlineMusic;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    //重写equals方法, 使得可以用contains方法来判断列表中是否存在Music类的实例
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Music music = (Music) obj;
        return Objects.equals(title,music.title);
    }
}
