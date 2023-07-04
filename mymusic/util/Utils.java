package com.example.mymusic.util;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Message;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.example.mymusic.gson.Result;

public class Utils {

    //累计听歌数量
    public static int count;

    //播放模式
    public static final int TYPE_ORDER = 4212;  //顺序播放
    public static final int TYPE_SINGLE = 4313; //单曲循环
    public static final int TYPE_RANDOM = 4414; //随机播放

    // 获取音乐封面图片
    public static Bitmap getLocalMusicBmp(ContentResolver res, String musicPic) {
        InputStream in;
        Bitmap bmp = null;
        try {
//            将字符串解析为Uri对象
            Uri uri = Uri.parse(musicPic);
//            解析得到的 Uri 对象来获取图片的输入流
            in = res.openInputStream(uri);
//            配置解码图片时的选项。
            BitmapFactory.Options boptins = new BitmapFactory.Options();
//            输入流 in 解码为 Bitmap 对象
            bmp = BitmapFactory.decodeStream(in, null, boptins);
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

    //格式化歌曲时间
    public static String formatTime(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        Date data = new Date(time);
        return dateFormat.format(data);
    }
    //将返回的JSON数据解析成Result实体类
    public static List<Result> handleResultResponse(String response){
        try{
            List<Result> onlinemusic_list = new ArrayList<>();
//            将 response 字符串转换为一个 JSONObject 对象,这个对象表示整个JSON结构
            JSONObject jsonObject = new JSONObject(response);
//            然后从中提取名为 "result" 的 JSON 数组
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject x = jsonArray.getJSONObject(i);
                String resultContent = x.toString();
//                使用 Gson 库将 resultContent 转换为 Result 对象，并将其赋值给 result 变量
                Result result=new Gson().fromJson(resultContent, Result.class);
                JSONObject song=x.getJSONObject("song");
                JSONArray artists = song.getJSONArray("artists");
                JSONObject singer = artists.getJSONObject(0);
                String singerName = singer.getString("name");
                result.setArtists(singerName);
                //将JSON数据转换成result对象
                onlinemusic_list.add(result);
            }
            return onlinemusic_list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String getSongUrl(String response){
        String url=null;
        try {
            JSONObject obj = new JSONObject(response);
            JSONArray songs = new JSONArray(obj.getString("data"));
                JSONObject song=songs.getJSONObject(0);
                url=song.getString("url");
            if(url != null)
                return url;
            if(url == null){
                url = "http://m801.music.126.net/20230702144041/cbf42eb3f8981ce58efd90b746a72c84/jdymusic/obj/" +
                        "wo3DlMOGwrbDjj7DisKw/28895067044/34d3/e9e3/898d/9a21a1e4304951c6ab3ffdc34a4fc01e.mp3";
                return  url;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }
}