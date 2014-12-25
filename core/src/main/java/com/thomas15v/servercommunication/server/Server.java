package com.thomas15v.servercommunication.server;

import com.thomas15v.servercommunication.network.PacketDecoder;
import com.thomas15v.servercommunication.network.PacketEncoder;
import com.thomas15v.servercommunication.network.PacketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
/**
 * Created by thomas15v on 25/12/14.
 */
public class Server implements Runnable {

    private static Server server;

    private int port = 5500;
    private String bindadress;

    public static void main(String[] args){
        server = new Server();
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
                            ch.pipeline().addLast(new PacketDecoder(), new PacketEncoder(), new ServerConnectionHandler());
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
        }

    }
}
