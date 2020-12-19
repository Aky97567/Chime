package com.whitelotusapps.chime.callback

import com.whitelotusapps.chime.utilities.BaseEvents

interface BaseListener {
    fun onEvent(event: BaseEvents?, position: Int, vararg params: Any?)
}