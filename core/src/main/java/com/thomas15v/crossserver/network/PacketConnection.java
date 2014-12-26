package com.thomas15v.crossserver.network;

import com.thomas15v.crossserver.network.packet.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Created by thomas15v on 26/12/14.
 */
public class PacketConnection extends SimpleChannelInboundHandler<Packet> {

    @NonNull
    @Getter
    @Setter
    private PacketHandler packetHandler;

    @Getter
    private ChannelWrapper channelWrapper;

    public PacketConnection(PacketHandler packetHandler){
        setPacketHandler(packetHandler);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.channelWrapper = new ChannelWrapper(ctx);
        synchronized (packetHandler) {
            getPacketHandler().connected(getChannelWrapper());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        synchronized (packetHandler) {
            getPacketHandler().disconnected(getChannelWrapper());
        }
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        synchronized (packetHandler) {
            getPacketHandler().handle(packet);
        }
    }
}
