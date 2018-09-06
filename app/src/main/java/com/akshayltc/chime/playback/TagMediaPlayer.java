package com.akshayltc.chime.playback;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

/**
 * Created by Admin on 2017-07-16.
 */

public class TagMediaPlayer extends MediaPlayer {

    private MediaPlayer mediaPlayer;

    TagMediaPlayer(String TAG, Context mContext, String source) {
        super();
        mediaPlayer = MediaPlayer.create(mContext, Uri.parse(source));
    }



}
