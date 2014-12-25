package com.thomas15v.servercommunication.server;

import com.thomas15v.servercommunication.network.packet.Packet;
import com.thomas15v.servercommunication.network.packet.client.PacketLogin;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by thomas15v on 25/12/14.
 */
public class ServerConnectionHandler extends SimpleChannelInboundHandler<Packet> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("connected");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("disconected");
        super.channelInactive(ctx);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        System.out.println(packet);
        if (packet instanceof PacketLogin)
            System.out.println(((PacketLogin) packet).getPassword() + " " + ((PacketLogin) packet).getServerName());
    }
}
