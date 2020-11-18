package com.personal.todoist;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
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

import java.util.Timer;
import java.util.TimerTask;

public class PodcastFragment extends Fragment implements View.OnClickListener {

    ImageButton playBtn;
    TextView songName,artistName;
    MediaPlayer mp;
    SeekBar seekBar;
    private FloatingActionButton logout;
    FirebaseAuth fAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView =  inflater.inflate(R.layout.fragment_podcast, container, false);
        playBtn = mView.findViewById(R.id.playBtn);
        seekBar =  mView.findViewById(R.id.seekBar);
        logout = mView.findViewById(R.id.Logout);
        songName = mView.findViewById(R.id.song_name);
        artistName = mView.findViewById(R.id.artist_name);
        ImageButton play1 = mView.findViewById(R.id.playlist1);
        ImageButton play2 = mView.findViewById(R.id.playlist2);
        ImageButton play3 = mView.findViewById(R.id.playlist3);
        ImageButton play4 = mView.findViewById(R.id.playlist4);
        ImageButton play5 = mView.findViewById(R.id.playlist5);
        ImageButton play6 = mView.findViewById(R.id.playlist6);
        ImageButton play7 = mView.findViewById(R.id.playlist7);
        ImageButton play8 = mView.findViewById(R.id.playlist8);

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

        mp = MediaPlayer.create(getActivity(),R.raw.furelise);
        return mView;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.playBtn:
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
                songName.setText("Playlist 1");
                artistName.setText("artist 1");
                break;
            case R.id.playlist2:
                songName.setText("Playlist 2");
                artistName.setText("artist 2");
                break;
            case R.id.playlist3:
                songName.setText("Playlist 3");
                artistName.setText("artist 3");
                break;
            case R.id.playlist4:
                songName.setText("Playlist 4");
                artistName.setText("artist 4");
                break;
            case R.id.playlist5:
                songName.setText("Playlist 5");
                artistName.setText("artist 5");
                break;
            case R.id.playlist6:
                songName.setText("Playlist 6");
                artistName.setText("artist 6");
                break;
            case R.id.playlist7:
                songName.setText("Playlist 7");
                artistName.setText("artist 7");
                break;
            case R.id.playlist8:
                songName.setText("Playlist 8");
                artistName.setText("artist 8");
                break;

            case R.id.Logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity().getApplicationContext(),SplashActivity.class));
                break;
        }
    }

    public void enableSeekBar(){

        seekBar.setMax(mp.getDuration());

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

