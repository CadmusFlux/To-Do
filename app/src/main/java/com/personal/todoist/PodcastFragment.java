package com.personal.todoist;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Timer;
import java.util.TimerTask;

public class PodcastFragment extends Fragment implements View.OnClickListener {

    ImageButton playBtn;
    MediaPlayer mp;
    SeekBar seekBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView =  inflater.inflate(R.layout.fragment_podcast, container, false);
        playBtn = mView.findViewById(R.id.playBtn);
        seekBar =  mView.findViewById(R.id.seekBar);
        playBtn.setOnClickListener(this);
        mp = MediaPlayer.create(getActivity(),R.raw.furelise);
        return mView;
    }


    @Override
    public void onClick(View view) {
        if (!mp.isPlaying()){
            mp.start();
            playBtn.setBackgroundResource(R.mipmap.ic_pause);
        }
        else {
            mp.pause();
            playBtn.setBackgroundResource(R.mipmap.ic_stop);
        }
        enableSeekBar();
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

