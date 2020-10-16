package com.bif.nettyclient;

import io.netty.channel.ChannelPromise;

public interface ResultHandler {

    void setPromise(ChannelPromise promise);

    Object getResult();

}
