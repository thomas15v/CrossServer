package com.thomas15v.crossserver.server;

import com.thomas15v.crossserver.network.PacketConnectionHandler;
import com.thomas15v.crossserver.network.PacketDecoder;
import com.thomas15v.crossserver.network.PacketEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * Created by thomas15v on 27/12/14.
 */
public class PacketChannelInitializer extends ChannelInitializer<SocketChannel>{

    private PacketConnectionHandler connectionHandler;

    public PacketChannelInitializer(PacketConnectionHandler connectionHandler){
        this.connectionHandler = connectionHandler;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ReadTimeoutHandler(3000), new PacketDecoder(), new PacketEncoder(), connectionHandler);
    }
}
