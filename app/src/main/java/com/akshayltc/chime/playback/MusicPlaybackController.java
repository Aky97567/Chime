package com.akshayltc.chime.playback;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Admin on 2017-07-16.
 */

public class MusicPlaybackController implements MediaPlayer.OnPreparedListener {

    //region states
    public static final int PLAYBACK_STATE_IDLE = 0;
    public static final int PLAYBACK_STATE_PREPARING = 1;
    public static final int PLAYBACK_STATE_READY = 2;
    public static final int PLAYBACK_STATE_PLAYING = 3;
    public static final int PLAYBACK_STATE_PAUSED = 4;
    public static final int PLAYBACK_STATE_SEEKING = 5;
    //endregion

    //region actions
    public static final int SET_MEDIA = 0;
    public static final int PLAY = 1;
    public static final int PAUSE = 2;
    public static final int STOP = 3;
    public static final int RELEASE = 4;
    //endregion

    //region globals
    //variables
    private String TAG = com.akshayltc.chime.playback.MusicPlaybackController.class.getSimpleName();
    private static int currentState;

    //flags
/*  private boolean;*/

    //misc
    private static TagMediaPlayer mMediaPlayer;
    //endregion


    public MusicPlaybackController() {
        currentState = PLAYBACK_STATE_IDLE;
    }

    public static void setmMediaPlayer(Context mContext, String source, int action) {
        if(currentState == PLAYBACK_STATE_IDLE) {
            mMediaPlayer = new TagMediaPlayer("",mContext,source);
        }
        switch ( currentState ) {
            case PLAYBACK_STATE_IDLE:
            case PLAYBACK_STATE_PREPARING:
            case PLAYBACK_STATE_READY:
            case PLAYBACK_STATE_PLAYING:
            case PLAYBACK_STATE_PAUSED:
            case PLAYBACK_STATE_SEEKING:
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }
}
