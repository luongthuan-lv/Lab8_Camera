package vn.edu.lab8_camera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SongAdapter extends BaseAdapter {
    public Context context;
    public List<Song> songList;

    public SongAdapter(Context context, List<Song> songList) {
        this.context = context;
        this.songList = songList;
    }

    @Override
    public int getCount() {
        return songList.size();
    }

    @Override
    public Object getItem(int position) {
        return songList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.row,parent,false);
        final TextView tvNameS=view.findViewById(R.id.tvNameS);
        Song song=songList.get(position);
        tvNameS.setText(song.getName());
//        view.(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,tvNameS+"",Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }
}
