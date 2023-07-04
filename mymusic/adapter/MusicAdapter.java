package com.example.mymusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mymusic.bean.Music;
import com.example.mymusic.R;

import java.util.List;

public class MusicAdapter extends BaseAdapter {

    private List<Music>  Lom;
    //    将布局文件中的视图实例化并添加到活动中
    private LayoutInflater mInflater;
    private int mInt;
    private onMoreButtonListener monMoreButtonListener;

    public MusicAdapter(Context context, int resId, List<Music> data) {
        mInflater = LayoutInflater.from(context);
        mInt = resId;
        Lom = data;
    }
    @Override
    public int getCount() {
        return Lom != null ? Lom.size() : 0;
    }
    @Override
    public Object getItem(int position) {
        return Lom != null ? Lom.get(position): null ;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Music item = Lom.get(position);
        View view;
        ViewHolder holder;
        if (convertView == null) {
//            布局文件转换为对应的视图对象
            view = mInflater.inflate(mInt, parent, false);
            holder = new ViewHolder();
            holder.title = view.findViewById(R.id.music_title);
            holder.artist = view.findViewById(R.id.music_artist);
            holder.more = view.findViewById(R.id.more);
            view.setTag(holder);
        }
        else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        holder.title.setText(item.title);
        holder.artist.setText(item.artist);
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monMoreButtonListener.onClick(position);
            }
        });
        return view;
    }
    class ViewHolder{
        TextView title;
        TextView artist;
        LinearLayout more;
    }
    public interface onMoreButtonListener {
        void onClick(int i);
    }
    public void setOnMoreButtonListener(onMoreButtonListener monMoreButtonListener) {
        this.monMoreButtonListener = monMoreButtonListener;
    }
}