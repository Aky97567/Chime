package com.akshayltc.chime.callback;

import com.akshayltc.chime.utilities.BaseEvents;

public interface BaseListener {

    void onEvent(BaseEvents event, int position, Object... params);

}
