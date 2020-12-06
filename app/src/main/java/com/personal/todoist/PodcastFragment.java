package com.personal.todoist;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.Timer;
import java.util.TimerTask;

public class PodcastFragment extends Fragment implements View.OnClickListener {

    ImageButton playBtn;
    TextView songName,artistName,SongDuration;
    MediaPlayer mp;
    SeekBar seekBar;
    FloatingActionButton logout;
    RoundedImageView podcastImage;

    private double songDuration = 0;

    FirebaseAuth fAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView =  inflater.inflate(R.layout.fragment_podcast, container, false);

        // Attaching Views to Objects

        playBtn = mView.findViewById(R.id.playBtn);
        seekBar =  mView.findViewById(R.id.seekBar);
        logout = mView.findViewById(R.id.Logout);
        songName = mView.findViewById(R.id.song_name);
        artistName = mView.findViewById(R.id.artist_name);
        SongDuration = mView.findViewById(R.id.song_duration);
        podcastImage = mView.findViewById(R.id.imageView1);
        ImageButton play1 = mView.findViewById(R.id.playlist1);
        ImageButton play2 = mView.findViewById(R.id.playlist2);
        ImageButton play3 = mView.findViewById(R.id.playlist3);
        ImageButton play4 = mView.findViewById(R.id.playlist4);
        ImageButton play5 = mView.findViewById(R.id.playlist5);
        ImageButton play6 = mView.findViewById(R.id.playlist6);
        ImageButton play7 = mView.findViewById(R.id.playlist7);
        ImageButton play8 = mView.findViewById(R.id.playlist8);


        // Setting onClickListener with context as this fragment

        playBtn.setOnClickListener(this);
        logout.setOnClickListener(this);
        play1.setOnClickListener(this);
        play2.setOnClickListener(this);
        play3.setOnClickListener(this);
        play4.setOnClickListener(this);
        play5.setOnClickListener(this);
        play6.setOnClickListener(this);
        play7.setOnClickListener(this);
        play8.setOnClickListener(this);

        return mView;
    }

    // Onclick Implementation for each Genre and Music Controller

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.playBtn:
                if (mp == null){
                    Toast.makeText(getActivity(), "Select a Genre First!",
                            Toast.LENGTH_LONG).show();
                    break;
                }
                if (!mp.isPlaying()) {
                    mp.start();
                    playBtn.setBackgroundResource(R.mipmap.ic_pause);
                } else {
                    mp.pause();
                    playBtn.setBackgroundResource(R.mipmap.ic_stop);
                }
                enableSeekBar();
                break;
            case R.id.playlist1:
                if (mp != null) {
                    mp.stop();
                }
                mp = MediaPlayer.create(getActivity(),R.raw.abcdef);
                songName.setText("Study");
                artistName.setText("Artist 1");
                podcastImage.setBackground(getResources().getDrawable(R.drawable.play2));
                break;
            case R.id.playlist2:
                if (mp != null) {
                    mp.stop();
                }
                mp = MediaPlayer.create(getActivity(),R.raw.abc);
                songName.setText("Meditate");
                artistName.setText("Artist 2");
                podcastImage.setBackground(getResources().getDrawable(R.drawable.play1));
                break;
            case R.id.playlist3:
                if (mp != null) {
                    mp.stop();
                }
                mp = MediaPlayer.create(getActivity(),R.raw.abcd);
                songName.setText("Sleep");
                artistName.setText("artist 3");
                podcastImage.setBackground(getResources().getDrawable(R.drawable.play3));
                break;
            case R.id.playlist4:
                if (mp != null) {
                    mp.stop();
                }
                mp = MediaPlayer.create(getActivity(),R.raw.abcde);
                songName.setText("Workout");
                artistName.setText("artist 4");
                podcastImage.setBackground(getResources().getDrawable(R.drawable.play5));
                break;
            case R.id.playlist5:
                if (mp != null) {
                    mp.stop();
                }
                mp = MediaPlayer.create(getActivity(),R.raw.abcdef);
                songName.setText("Gaming");
                artistName.setText("artist 5");
                podcastImage.setBackground(getResources().getDrawable(R.drawable.play4));
                break;
            case R.id.playlist6:
                if (mp != null) {
                    mp.stop();
                }
                mp = MediaPlayer.create(getActivity(),R.raw.abc);
                songName.setText("Groovy");
                artistName.setText("artist 6");
                podcastImage.setBackground(getResources().getDrawable(R.drawable.play1));
                break;
            case R.id.playlist7:
                if (mp != null) {
                    mp.stop();
                }
                mp = MediaPlayer.create(getActivity(),R.raw.abcd);
                songName.setText("Coding");
                artistName.setText("artist 7");
                podcastImage.setBackground(getResources().getDrawable(R.drawable.play2));
                break;
            case R.id.playlist8:
                if (mp != null) {
                    mp.stop();
                }
                mp = MediaPlayer.create(getActivity(),R.raw.abcde);
                songName.setText("Motivation");
                artistName.setText("artist 8");
                podcastImage.setBackground(getResources().getDrawable(R.drawable.play3));
                break;

            case R.id.Logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity().getApplicationContext(),SplashActivity.class));
                break;
        }
    }

    // Seekbar Contoller
    public void enableSeekBar(){

        seekBar.setMax(mp.getDuration());
        int secs = (int) (mp.getDuration() / 1000) % 60;
        int min =  (int) (mp.getDuration() / 1000) / 60;
        SongDuration.setText(min +":"+secs);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if(mp!=null && mp.isPlaying()){
                    seekBar.setProgress(mp.getCurrentPosition());
                }
            }
        }, 0, 10);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                // Update the progress depending on seek bar
                if(fromUser){
                    mp.seekTo(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}

