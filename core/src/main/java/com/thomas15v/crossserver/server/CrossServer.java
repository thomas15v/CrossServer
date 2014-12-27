package com.thomas15v.crossserver.server;

import com.thomas15v.crossserver.network.PacketConnectionHandler;
import com.thomas15v.crossserver.network.PacketDecoder;
import com.thomas15v.crossserver.network.PacketEncoder;
import com.thomas15v.crossserver.network.packet.Packet;
import com.thomas15v.crossserver.server.packethandler.ConnectionInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thomas15v on 25/12/14.
 */
public class CrossServer implements Runnable {

    private int port = 5500;
    private String bindadress;
    @Getter
    private Map<String, ConnectedServer> clients = new HashMap<>();

    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    public static void main(String[] args){
        new Thread(new CrossServer()).start();
    }

    @Override
    public void run() {
        System.out.println("started!");
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new PacketChannelInitializer(this))
                    .option(ChannelOption.SO_BACKLOG, 128);
            ChannelFuture f = b.bind(port).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess())
                        System.out.println("Succesfully Bounded");
                }
            });
            System.out.println("waiting");
            f.channel().closeFuture().sync();
            System.out.println("closed!");
        }catch (Exception e){
            stop();
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            System.out.println("stopped");
            e.printStackTrace();
        }
    }

    public void broadCast(Packet packet, ConnectedServer sender){
        for (ConnectedServer connectedServer : clients.values())
            if (connectedServer != sender)
                connectedServer.getChannel().sendPacket(packet);
    }

    public void broadCast(Packet packet){
        broadCast(packet, null);
    }

    public void stop(){

    }


}
