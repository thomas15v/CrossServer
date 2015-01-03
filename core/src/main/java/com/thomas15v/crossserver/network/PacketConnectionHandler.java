package com.thomas15v.crossserver.network;

import com.thomas15v.crossserver.network.packet.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Created by thomas15v on 26/12/14.
 */
@ChannelHandler.Sharable
public class PacketConnectionHandler extends SimpleChannelInboundHandler<Packet> {

    @NonNull
    @Getter
    private PacketHandler packetHandler;

    @Getter
    private ChannelWrapper channel;

    public PacketConnectionHandler(PacketHandler packetHandler){
        setPacketHandler(packetHandler);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.channel = new ChannelWrapper(ctx);
        synchronized (packetHandler) {
            getPacketHandler().connected(this.getChannel());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        synchronized (packetHandler) {
            getPacketHandler().disconnected(this.getChannel());
        }
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        System.out.println(packet);
        synchronized (packetHandler) {
            packet.handle(getPacketHandler());
        }
    }

    public void setPacketHandler(PacketHandler packetHandler) {
        synchronized (packetHandler) {
            this.packetHandler = packetHandler;
        }
    }
}
