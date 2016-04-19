package com.bobslab;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by bast on 2016-04-19.
 */
public class ApiRequestParser extends SimpleChannelInboundHandler<FullHttpMessage> {
    private static final Logger logger = LogManager.getLogger(ApiRequestParser.class);
    private HttpRequest request;
    private JsonObject apiResult;

    private static final HttpDataFactory factory =
            new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE);

    private HttpPostRequestDecoder decoder;

    private Map<String, String> reqData = new HashMap<String, String>();

    private static final Set<String> usingHeader = new HashSet<String>();

    static {
        usingHeader.add("token");
        usingHeader.add("emal");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpMessage msg) throws Exception {
        if (msg instanceof HttpRequest) {
            this.request = (HttpRequest) msg;

            if (HttpHeaders.is100ContinueExpected(request)) {
                send100Continues(ctx);
            }

            HttpHeaders headers = request.headers();
            if (!headers.isEmpty()) {
                for (Map.Entry<String, String> h : headers) {
                    String key = h.getKey();
                    if (usingHeader.contains(key)) {
                        reqData.put(key, h.getValue());
                    }
                }
            }

            reqData.put("REQUEST_URI", request.getUri());
            reqData.put("REQUEST_METHOD", request.getMethod());
        }

        if (msg instanceof HttpContent) {
            HttpContent httpContent = (HttpContent) msg;

            ByteBuf content = httpContent.content();

            if (msg instanceof LastHttpContent) {
                logger.debug()
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.info("요청 처리 완료");
        ctx.flush();
    }
}
