package com.bif.service;

import com.bif.nettyclient.NettyClientConnector;
import com.bif.util.FastJsonUtil;
import com.bif.vm.CommonRequest;
import com.bif.vm.CommonResponse;
import com.bif.vm.Request;
import com.bif.vm.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public abstract class BaseDataHandler {
    private static final Logger logger = LoggerFactory.getLogger(BaseDataHandler.class);

    @Autowired
    private NettyClientConnector nettyClientConnector;

    protected Response sendRequest(Request request) {
        Response response = nettyClientConnector.requestSync(request,
                FastJsonUtil::toString,
                (json) -> FastJsonUtil.toObject((String) json, Response.class));
        logger.info("request:{},response{}", request, response);
        return response;
    }

    protected CommonResponse sendRequest(CommonRequest request) {
        CommonResponse response = nettyClientConnector.requestSync(request,
                FastJsonUtil::toString,
                (json) -> FastJsonUtil.toObject((String) json, CommonResponse.class));
        logger.info("request:{},response{}", request, response);
        return response;
    }
}
