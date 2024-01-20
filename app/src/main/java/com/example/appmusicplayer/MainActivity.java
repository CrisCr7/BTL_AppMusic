package com.example.appmusicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;





public class MainActivity extends AppCompatActivity {

    TextView txtTitle, txtTimeSong, txtTimeTotal;
    SeekBar skSong;
    ImageButton btnPrev, btnPlay, btnStop, btnNext;
    ImageView imgHinhXoay;

    ImageButton btnlogout;


    ArrayList<Song> arraySong;
    int position=0;

    MediaPlayer mediaPlayer;

    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        AddSong();

        animation = AnimationUtils.loadAnimation(this, R.anim.disc_totate);


        KhoiTaoMediaPlayer();


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position++;
                if (position > arraySong.size() - 1) {
                    position = 0;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(android.R.drawable.ic_media_pause);

            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position--;
                if (position < 0) {
                    position = arraySong.size() - 1;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(android.R.drawable.ic_media_pause);
            }
        });

//        btnPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(mediaPlayer.isPlaying()){
//                    mediaPlayer.pause();
//                    btnPlay.setImageResource(android.R.drawable.ic_media_play);
//                }else{
//                    mediaPlayer.start();
//                    btnPlay.setImageResource(android.R.drawable.ic_media_pause);
//                }
//            }
//
//
//
//        });
        btnPlay.setOnClickListener((view) -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                btnPlay.setImageResource(android.R.drawable.ic_media_play);
            } else {
                mediaPlayer.start();
                btnPlay.setImageResource(android.R.drawable.ic_media_pause);
            }
            imgHinhXoay.startAnimation(animation);


        });

        skSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(skSong.getProgress());
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finishAffinity();//kết thúc các activity
                System.exit(0); // Tắt hết tất cả các tác vụ

            }
        });

    }
    private void UpdateTimeSong(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()));
                // update progress sksong
                skSong.setProgress(mediaPlayer.getCurrentPosition());
                // kiem tra tg cua bai hat - neu ket thuc -> next
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if(position > arraySong.size() - 1){
                            position = 0;
                        }
                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        KhoiTaoMediaPlayer();
                        mediaPlayer.start();
                        btnPlay.setImageResource(android.R.drawable.ic_media_pause);
                    }
                });
                handler.postDelayed(this,500);
            }
        },100);
    }
    private void SetTimeTotal(){

        SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(dinhDangGio.format(mediaPlayer.getDuration()));
        //gan max cua sksong = mediaplay.getdu

        skSong.setMax(mediaPlayer.getDuration());

    }
    private void KhoiTaoMediaPlayer(){
        mediaPlayer= MediaPlayer.create(MainActivity.this, arraySong.get(position).getFile());
        txtTitle.setText(arraySong.get(position).getTitle());
        SetTimeTotal();
        UpdateTimeSong();
//        imgHinhXoay.startAnimation(animation);


    }
    private void AddSong(){
        arraySong=new ArrayList<>();
        arraySong.add(new Song("Biển Tình-Lan Anh",R.raw.bien_tinh));
        arraySong.add(new Song("Con Tim Đang Cố Quên-Phương Phương Thảo",R.raw.con_tim_dang_co_quen));
        arraySong.add(new Song("Dĩ Vãng Cuộc Tình", R.raw.di_vang_cuoc_tinh));
        arraySong.add(new Song("Chỉ Muốn Bên Anh Suốt Đời-Phương Phương Thảo", R.raw.chi_muon_ben_anh_suot_doi));
    }

    private void AnhXa(){
        txtTimeSong     =(TextView) findViewById(R.id.textViewTimeSong);
        txtTimeTotal    =(TextView) findViewById(R.id.textViewTimeTotal);
        txtTitle        =(TextView) findViewById(R.id.textViewTitle);
        skSong          =(SeekBar) findViewById(R.id.seekBarSong);
        btnNext         =(ImageButton) findViewById(R.id.imageButtonNext);
        btnPlay         =(ImageButton) findViewById(R.id.imageButtonPlay);
        btnPrev         =(ImageButton) findViewById(R.id.imageButtonPrev);
        imgHinhXoay     =(ImageView) findViewById(R.id.imageViewDisc);
        btnlogout       =(ImageButton)findViewById(R.id.btnlogout);
    }
}