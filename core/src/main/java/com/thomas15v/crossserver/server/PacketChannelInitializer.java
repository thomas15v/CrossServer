package com.thomas15v.crossserver.server;

import com.thomas15v.crossserver.network.PacketConnectionHandler;
import com.thomas15v.crossserver.network.PacketDecoder;
import com.thomas15v.crossserver.network.PacketEncoder;
import com.thomas15v.crossserver.server.packethandler.ConnectionInitializer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * Created by thomas15v on 27/12/14.
 */
public class PacketChannelInitializer extends ChannelInitializer<SocketChannel>{

    private CrossServer crossServer;

    public PacketChannelInitializer(CrossServer crossServer) {
        this.crossServer = crossServer;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ReadTimeoutHandler(3000), new PacketDecoder(), new PacketEncoder(), new PacketConnectionHandler(new ConnectionInitializer(crossServer)));
    }
}
