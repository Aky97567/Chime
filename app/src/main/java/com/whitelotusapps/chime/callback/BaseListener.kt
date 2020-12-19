package com.whitelotusapps.chime.callback;

import com.whitelotusapps.chime.utilities.BaseEvents;

public interface BaseListener {

    void onEvent(BaseEvents event, int position, Object... params);

}
