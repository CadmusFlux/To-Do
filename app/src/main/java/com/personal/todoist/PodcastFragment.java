package com.personal.todoist;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PodcastFragment extends Fragment implements View.OnClickListener {

    ImageButton playBtn;
    MediaPlayer mp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView =  inflater.inflate(R.layout.fragment_podcast, container, false);
        playBtn = mView.findViewById(R.id.playBtn);
        playBtn.setOnClickListener(this);
        mp = MediaPlayer.create(getActivity(),R.raw.furelise);
        return mView;
    }

//    public void playBtnClick(View view) {
//
//        if (!mp.isPlaying()){
//            mp.start();
//            playBtn.setBackgroundResource(R.mipmap.ic_pause);
//        }
//        else {
//            mp.pause();
//            playBtn.setBackgroundResource(R.mipmap.ic_stop);
//        }
//
//    }

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
    }

}

