package com.thomas15v.crossserver.network;

import com.thomas15v.crossserver.network.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by thomas15v on 25/12/14.
 */
@ChannelHandler.Sharable
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        try {
            byteBuf.writeInt(packet.getId());
            packet.encode(byteBuf);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
