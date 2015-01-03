package com.thomas15v.crossserver.server;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.client.Client;
import com.thomas15v.crossserver.network.packet.Packet;
import com.thomas15v.crossserver.network.remote.NullPlayer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
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
        System.out.println("starting....");
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
                        System.out.println("Server succesfully started on /127.0.0.1:" + port);
                }
            });
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("Server has stopped");
            stop();
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
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    public ConnectedServer getServer(String name){
        return clients.get(name);
    }

    public Player getPlayer(String name){
        for (ConnectedServer client : clients.values()) {
            System.out.println(client.getPlayers());
            Player player = client.getPlayer(name);
            if (player != null)
                return player;
        }
        return new NullPlayer(name);
    }



}
