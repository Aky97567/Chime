package com.whitelotusapps.chime.playback

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener

/**
 * Created by Admin on 2017-07-16.
 */
class MusicPlaybackController : OnPreparedListener {
    //endregion
    //region globals
    //variables
    private val TAG = MusicPlaybackController::class.java.simpleName
    override fun onPrepared(mp: MediaPlayer) {}

    companion object {
        //region states
        const val PLAYBACK_STATE_IDLE = 0
        const val PLAYBACK_STATE_PREPARING = 1
        const val PLAYBACK_STATE_READY = 2
        const val PLAYBACK_STATE_PLAYING = 3
        const val PLAYBACK_STATE_PAUSED = 4
        const val PLAYBACK_STATE_SEEKING = 5

        //endregion
        //region actions
        const val SET_MEDIA = 0
        const val PLAY = 1
        const val PAUSE = 2
        const val STOP = 3
        const val RELEASE = 4
        private var currentState: Int

        //flags
        /*  private boolean;*/ //misc
        private var mMediaPlayer: TagMediaPlayer? = null
        fun setmMediaPlayer(mContext: Context?, source: String?, action: Int) {
            if (currentState == PLAYBACK_STATE_IDLE) {
                mMediaPlayer = TagMediaPlayer("", mContext, source)
            }
            when (currentState) {
                PLAYBACK_STATE_IDLE, PLAYBACK_STATE_PREPARING, PLAYBACK_STATE_READY, PLAYBACK_STATE_PLAYING, PLAYBACK_STATE_PAUSED, PLAYBACK_STATE_SEEKING -> {
                }
            }
        }
    }

    //endregion
    init {
        currentState = PLAYBACK_STATE_IDLE
    }
}