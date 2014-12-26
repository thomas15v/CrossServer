package com.thomas15v.crossserver.client;

import com.thomas15v.crossserver.client.packethandler.ConnectionInitializer;
import com.thomas15v.crossserver.network.PacketConnection;
import com.thomas15v.crossserver.network.PacketDecoder;
import com.thomas15v.crossserver.network.PacketEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by thomas15v on 25/12/14.
 */
public class Client implements Runnable {

    private int port = 5500;
    private String host = "localhost";

    private static Client client;

    public static void main(String[] args){
        client = new Client();
        new Thread(client).start();
    }

    @Override
    public void run() {
        System.out.println("started!");
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new PacketDecoder(), new PacketEncoder(), new PacketConnection(new ConnectionInitializer()));
                        }
                    });
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
            System.out.println("closed!");
        }catch (Exception e){
            group.shutdownGracefully();
            e.printStackTrace();
        }
    }
}
