package com.example.mymusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymusic.bean.Music;
import com.example.mymusic.R;

import java.util.List;

public class PlayingMusicAdapter extends BaseAdapter {
    private List<Music>  Lom;
    private LayoutInflater mInflater;
    private int mInt;
    private Context mContext;
    private onDeleteButtonListener monDeleteButtonListener;

    public PlayingMusicAdapter(Context context, int resId, List<Music> data){
        mContext = context;
        Lom = data;
        mInflater = LayoutInflater.from(context);
        mInt = resId;
    }
    class ViewHolder{
        TextView title;
        TextView artist;
        ImageView delete;
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
            view = mInflater.inflate(mInt, parent, false);
            holder = new ViewHolder();
            holder.title = view.findViewById(R.id.PlayingTable_title);
            holder.artist = view.findViewById(R.id.PlayingTable_artist);
            holder.delete = view.findViewById(R.id.delete);
            view.setTag(holder);
        }
        else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
//        无论是创建新视图还是重用视图，最后都需要使用holder对象来设置视图中的数据
        holder.title.setText(item.title);
        holder.artist.setText(item.artist);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monDeleteButtonListener.onClick(position);
            }
        });
        return view;
    }
    public interface onDeleteButtonListener {
        void onClick(int i);
    }
    public void setOnDeleteButtonListener(onDeleteButtonListener monDeleteButtonListener) {
        this.monDeleteButtonListener = monDeleteButtonListener;
    }
}

