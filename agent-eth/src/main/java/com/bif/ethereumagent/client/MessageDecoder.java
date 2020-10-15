package com.bif.ethereumagent.client;

public interface MessageDecoder<T> {

    T decode(Object data);

}
