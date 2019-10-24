package vn.edu.lab8_camera;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MediaActivity extends AppCompatActivity {
    TextView tvName, tvTime, tvTotalTime;
    SeekBar sbMusic;
    ImageView imgPrees, imgPlay, imgStop, imgNext,imgDisc;
    ArrayList<Song> songArrayList;
    int position = 0;
    MediaPlayer mediaPlayer;
    Animation animator;
    String name;
    int positions;
    int file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        tvName = findViewById(R.id.tvName);
        tvTime = findViewById(R.id.tvTime);
        tvTotalTime = findViewById(R.id.tvTotalTime);
        sbMusic = findViewById(R.id.sbMusic);
        imgPrees = findViewById(R.id.imgPrees);
        imgNext = findViewById(R.id.imgNext);
        imgPlay = findViewById(R.id.imgPlay);
        imgStop = findViewById(R.id.imgStop);
        imgDisc=findViewById(R.id.imgDisc);
        animator= AnimationUtils.loadAnimation(this,R.anim.disc);
        final Animation animation=AnimationUtils.loadAnimation(this,R.anim.stopdisc);

        songArrayList = new ArrayList<>();
        songArrayList.add(new Song("Đi Đu Đưa Đi", R.raw.diduduadi));
        songArrayList.add(new Song("Em Đang Nghĩ Gì", R.raw.emdangnghigi));
        songArrayList.add(new Song("Kiminonawa", R.raw.kimi_no_na_wa));
        songArrayList.add(new Song("Lạnh Lẽo", R.raw.lanhleo));
        songArrayList.add(new Song("Mượn Rượu Tỏ Tình", R.raw.muonruoutotinh));
        songArrayList.add(new Song("Nắm Tay Em Chặt Anh Nhé", R.raw.namtayemchatanhnhe));
        songArrayList.add(new Song("Phải Giữ Em Thôi", R.raw.phaigiuemthoi));
        songArrayList.add(new Song("Từng Yêu", R.raw.tungyeu));

        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        Intent intent=getIntent();
         name= intent.getStringExtra("songname");
        positions= intent.getIntExtra("pos",0);
        file= intent.getIntExtra("songs",0);

        initialization();

        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if (position>songArrayList.size()-1){
                    position=0;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                initialization1();
                mediaPlayer.start();
                imgPlay.setImageResource(R.drawable.pause);
                settimetotal();
                Updatetime();
                imgDisc.startAnimation(animator);
            }
        });

        imgPrees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position<0){
                    position=songArrayList.size()-1;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
                initialization1();
                mediaPlayer.start();
                imgPlay.setImageResource(R.drawable.pause);
                settimetotal();
                Updatetime();
                imgDisc.startAnimation(animator);
            }
        });

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (mediaPlayer.isPlaying()){
                   mediaPlayer.pause();
                   imgPlay.setImageResource(R.drawable.play);
               }else{
                   mediaPlayer.start();
                   imgPlay.setImageResource(R.drawable.pause);
               }
               settimetotal();
               Updatetime();
               imgDisc.startAnimation(animator);

            }
        });

        imgStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                imgPlay.setImageResource(R.drawable.play);
                initialization1();
                imgDisc.startAnimation(animation);
            }
        });

        sbMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(sbMusic.getProgress());

            }
        });
    }

    public void Updatetime(){

        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dingdang=new SimpleDateFormat("mm:ss");
                tvTime.setText(dingdang.format(mediaPlayer.getCurrentPosition()));
                //update progress seekbar
                sbMusic.setProgress(mediaPlayer.getCurrentPosition());

                //kiểm tra thười gian kết thúc thì next bài
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position>songArrayList.size()-1){
                            position=0;
                        }
                        if (mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                            mediaPlayer.release();
                        }
                        initialization();
                        mediaPlayer.start();
                        imgPlay.setImageResource(R.drawable.pause);
                        settimetotal();
                        Updatetime();
                    }
                });
                handler.postDelayed(this,500);
            }
        },100);
    }

    public void initialization() {


//        mediaPlayer = MediaPlayer.create(MediaActivity.this, songArrayList.get(position).getFile());
//        tvName.setText(songArrayList.get(position).getName());

        mediaPlayer = MediaPlayer.create(MediaActivity.this, file);
        tvName.setText(name);


    }
    public void initialization1() {


        mediaPlayer = MediaPlayer.create(MediaActivity.this, songArrayList.get(position).getFile());
        tvName.setText(songArrayList.get(position).getName());



    }


    public void settimetotal(){
        SimpleDateFormat dinhdang=new SimpleDateFormat("mm:ss");
        tvTotalTime.setText(dinhdang.format(mediaPlayer.getDuration()));

        sbMusic.setMax(mediaPlayer.getDuration());

    }
}
