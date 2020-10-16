package com.bif.nettyclient;

public interface MessageEncoder<R> {

    R encode(Object data);

}
