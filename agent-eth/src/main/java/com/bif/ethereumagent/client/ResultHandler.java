package com.bif.ethereumagent.client;

import io.netty.channel.ChannelPromise;

public interface ResultHandler {

    void setPromise(ChannelPromise promise);

    Object getResult();

}
