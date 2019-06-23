package com.kristurek.polskatv.ui.event;

import com.kristurek.polskatv.ui.arch.Event;

public class RecreateAppEvent implements Event {

    private int channelId;
    private long epgCurrentTime;

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public long getEpgCurrentTime() {
        return epgCurrentTime;
    }

    public void setEpgCurrentTime(long epgCurrentTime) {
        this.epgCurrentTime = epgCurrentTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RecreateAppEvent{");
        sb.append("channelId=").append(channelId);
        sb.append(", epgCurrentTime=").append(epgCurrentTime);
        sb.append('}');
        return sb.toString();
    }
}
