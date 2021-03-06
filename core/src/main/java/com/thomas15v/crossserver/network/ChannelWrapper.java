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

    public synchronized void sendPacket(Packet packet){
        ctx.writeAndFlush(packet);
    }

    public synchronized void disconnect() {
        ctx.channel().close();
    }

    public PacketConnectionHandler getConnection(){
        return ctx.pipeline().get(PacketConnectionHandler.class);
    }

    public String getIPadress(){
        return ctx.channel().remoteAddress().toString();
    }
}
