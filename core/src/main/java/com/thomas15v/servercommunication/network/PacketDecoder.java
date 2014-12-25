package com.thomas15v.servercommunication.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by thomas15v on 25/12/14.
 */
public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("decode");
        int id = byteBuf.readByte();
        System.out.println(id);
        list.add(Protocol.getPacket(id).decode(byteBuf));
    }
}
