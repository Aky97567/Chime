package com.whitelotusapps.musicplayer.chime.preferences

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Admin on 2017-07-09.
 */
class PreferenceManager internal constructor(mContext: Context) {
    private val mSharedPreferences: SharedPreferences
    fun getString(key: String?, defaultValue: String?): String? {
        return mSharedPreferences.getString(key, defaultValue)
    }

    fun getBoolean(key: String?, defaultValue: Boolean): Boolean {
        return mSharedPreferences.getBoolean(key, defaultValue)
    }

    fun getInt(key: String?, defaultValue: Int): Int {
        return mSharedPreferences.getInt(key, defaultValue)
    }

    fun getLong(key: String?, defaultValue: Long): Long {
        return mSharedPreferences.getLong(key, defaultValue)
    }

    fun getFloat(key: String?, defaultValue: Float): Double {
        return mSharedPreferences.getFloat(key, defaultValue).toDouble()
    }

    fun putString(key: String?, value: String?) {
        mSharedPreferences.edit().putString(key, value).commit()
    }

    fun putBoolean(key: String?, value: Boolean) {
        mSharedPreferences.edit().putBoolean(key, value).commit()
    }

    fun putInt(key: String?, value: Int) {
        mSharedPreferences.edit().putInt(key, value).commit()
    }

    fun putLong(key: String?, value: Long) {
        mSharedPreferences.edit().putLong(key, value).commit()
    }

    fun putFloat(key: String?, value: Float) {
        mSharedPreferences.edit().putFloat(key, value).commit()
    }

    fun remove(key: String?) {
        mSharedPreferences.edit().remove(key).commit()
    }

    fun clear() {
        mSharedPreferences.edit().clear().commit()
    }

    companion object {
        //region Global Variables
        private var mPreferenceManager: PreferenceManager? = null
        private const val PREFS_NAME = "com.akshayltc.musicplayer.chime"

        /*
     * Function
     * @desc - Returns an instance of this class to allow method calls
     * @param Context - required to create PreferenceManager object
     * @return PreferenceManager
     */
        fun getInstance(mContext: Context): PreferenceManager? {
            mPreferenceManager = null
            mPreferenceManager = PreferenceManager(mContext)
            return mPreferenceManager
        }
    }

    //endregion
    /*
     * Constructor
     * Create an object of PreferenceManager (this class) to manage AppPreferences
     */
    init {
        mSharedPreferences = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
}