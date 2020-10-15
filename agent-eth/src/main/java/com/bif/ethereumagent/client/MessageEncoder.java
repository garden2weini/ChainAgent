package com.bif.ethereumagent.client;

public interface MessageEncoder<R> {

    R encode(Object data);

}
