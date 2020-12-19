package com.whitelotusapps.chime.playback

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.media.MediaPlayer.OnPreparedListener
import android.os.Binder
import android.os.IBinder
import com.whitelotusapps.chime.models.Track
import com.whitelotusapps.chime.playback.MusicPlayerService

class MusicPlayerService : Service(), OnPreparedListener, MediaPlayer.OnErrorListener, OnCompletionListener, ObserverSubject {
    private val mObservers: List<MusicPlayerObserver>? = null
    private val mMediaPlayer: MediaPlayer? = null
    private val playerBind: IBinder = MusicBinder()
    private val mPlaylist: List<Track>? = null
    private val mPosition: Int? = null
    private val isRepeating: Boolean? = null
    private val isShuffling: Boolean? = null
    private val isPrepared: Boolean? = null
    private val isPaused: Boolean? = null

    // Callback Methods______________________________________________
    override fun onCreate() {
        //...
    }

    override fun onPrepared(mp: MediaPlayer) {
        //...
    }

    override fun onBind(intent: Intent): IBinder? {
        return playerBind
    }

    override fun onUnbind(intent: Intent): Boolean {
        mMediaPlayer!!.stop()
        mMediaPlayer.release()
        return false
    }

    override fun onDestroy() {
        stopForeground(true)
    }

    override fun onError(mp: MediaPlayer, what: Int, extra: Int): Boolean {
        mp.reset()
        return false
    }

    // UTIL METHODS__________________________________________________
    private val currentTrackId: String?
        private get() = mPlaylist!![mPosition!!].trackId
    private val currentAlbumId: String?
        private get() = mPlaylist!![mPosition!!].albumId

    // MEDIA PLAYER INTERFACE________________________________________
    fun play() {
        //...
    }

    fun pause() {
        //...
    }

    fun resume() {
        //...
    }

    operator fun next() {
        //...
    }

    fun previous() {
        //...
    }

    fun seekTo(pos: Int) {
        //...
    }

    override fun onCompletion(mediaPlayer: MediaPlayer) {
        //..
    }
    // SERVICE INTERFACE PROVIDER_____________________________________
    /**
     * Interface through the component bound to this service can interact with it
     */
    inner class MusicBinder : Binder() {
        val service: MusicPlayerService
            get() = this@MusicPlayerService
    }

    companion object {
        private const val NOTIFY_ID = 1
    }
}