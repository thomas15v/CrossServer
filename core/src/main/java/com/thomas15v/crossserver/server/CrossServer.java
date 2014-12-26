package com.thomas15v.crossserver.server;

import com.thomas15v.crossserver.network.PacketConnection;
import com.thomas15v.crossserver.network.PacketDecoder;
import com.thomas15v.crossserver.network.PacketEncoder;
import com.thomas15v.crossserver.server.packethandler.ConnectionInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thomas15v on 25/12/14.
 */
public class CrossServer implements Runnable {

    private static CrossServer server;

    private int port = 5500;
    private String bindadress;
    @Getter
    private Map<String, ConnectedServer> clients = new HashMap<>();

    public static void main(String[] args){
        server = new CrossServer();
        new Thread(server).start();
    }

    @Override
    public void run() {
        System.out.println("started!");
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new PacketDecoder(), new PacketEncoder(), new PacketConnection(new ConnectionInitializer(server)));
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
            System.out.println("closed!");
        }catch (Exception e){
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            e.printStackTrace();
        }

    }
}
