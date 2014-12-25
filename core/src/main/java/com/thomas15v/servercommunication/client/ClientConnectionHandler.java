package com.thomas15v.servercommunication.client;

import com.thomas15v.servercommunication.network.packet.Packet;
import com.thomas15v.servercommunication.network.packet.client.PacketLogin;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by thomas15v on 25/12/14.
 */
public class ClientConnectionHandler extends SimpleChannelInboundHandler<Packet> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("connected");
        ctx.write(new PacketLogin("Test", "123"));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("disconected");
        super.channelInactive(ctx);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {

    }
}
