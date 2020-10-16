package com.bif.nettyclient;

public interface MessageDecoder<T> {

    T decode(Object data);

}
