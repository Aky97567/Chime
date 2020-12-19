package com.whitelotusapps.chime.playback

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri

/**
 * Created by Admin on 2017-07-16.
 */
class TagMediaPlayer internal constructor(TAG: String?, mContext: Context?, source: String?) : MediaPlayer() {
    private val mediaPlayer: MediaPlayer

    init {
        mediaPlayer = create(mContext, Uri.parse(source))
    }
}