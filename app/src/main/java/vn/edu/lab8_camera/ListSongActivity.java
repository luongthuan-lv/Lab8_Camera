package vn.edu.lab8_camera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListSongActivity extends AppCompatActivity {
    ListView lvListSong;
    List<Song> songList;
    SongAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);
        lvListSong = findViewById(R.id.lvListSong);

        songList = new ArrayList<>();
        songList.add(new Song("Đi Đu Đưa Đi", R.raw.diduduadi));
        songList.add(new Song("Em Đang Nghĩ Gì", R.raw.emdangnghigi));
        songList.add(new Song("Kiminonawa", R.raw.kimi_no_na_wa));
        songList.add(new Song("Lạnh Lẽo", R.raw.lanhleo));
        songList.add(new Song("Mượn Rượu Tỏ Tình", R.raw.muonruoutotinh));
        songList.add(new Song("Nắm Tay Em Chặt Anh Nhé", R.raw.namtayemchatanhnhe));
        songList.add(new Song("Phải Giữ Em Thôi", R.raw.phaigiuemthoi));
        songList.add(new Song("Từng Yêu", R.raw.tungyeu));
        songAdapter=new SongAdapter(ListSongActivity.this,songList);
        lvListSong.setAdapter(songAdapter);
       lvListSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              String songName= songList.get(position).getName();
               startActivity(new Intent(getApplicationContext(),MediaActivity.class)
                      .putExtra("pos",position).putExtra("songs", songList.get(position).getFile()).putExtra("songname",songName));

           }
       });





    }
}
