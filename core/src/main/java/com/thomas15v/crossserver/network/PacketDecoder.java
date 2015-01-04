package com.thomas15v.crossserver.network;

import com.thomas15v.crossserver.network.packet.client.PacketBye;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
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
            while(byteBuf.readableBytes() != 0) {
                int id = byteBuf.readInt();
                list.add(Protocol.getPacket(id).decode(byteBuf));
            }
        }catch (Exception e){
            e.printStackTrace();
            list.add(new PacketBye(true));
            ch.close();
        }

    }
}
