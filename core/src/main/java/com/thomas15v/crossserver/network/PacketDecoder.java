package com.thomas15v.crossserver.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by thomas15v on 25/12/14.
 */
public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ch, ByteBuf byteBuf, List<Object> list) throws Exception {
        try {
            int id = byteBuf.readInt();
            list.add(Protocol.getPacket(id).decode(byteBuf));
        }catch (Exception e){
            System.out.println("decoder stopped");
        }

    }
}
