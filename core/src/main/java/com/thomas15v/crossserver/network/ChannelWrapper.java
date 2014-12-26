package com.thomas15v.crossserver.network;

import com.thomas15v.crossserver.network.packet.Packet;
import io.netty.channel.ChannelHandlerContext;
import lombok.RequiredArgsConstructor;

/**
 * Created by thomas15v on 26/12/14.
 */
@RequiredArgsConstructor
public class ChannelWrapper {

    private ChannelHandlerContext ctx;

    public ChannelWrapper(ChannelHandlerContext ctx){
        this.ctx = ctx;
    }

    public void sendPacket(Packet packet){
        ctx.write(packet);
    }
}
