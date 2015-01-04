package com.thomas15v.crossserver.client;

import com.thomas15v.crossserver.api.Plugin;
import com.thomas15v.crossserver.api.remote.CrossServer;
import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.api.util.ConnectionStatus;
import com.thomas15v.crossserver.api.util.PlayerStatus;
import com.thomas15v.crossserver.client.packethandler.ConnectionInitializer;
import com.thomas15v.crossserver.network.PacketConnectionHandler;
import com.thomas15v.crossserver.network.PacketDecoder;
import com.thomas15v.crossserver.network.PacketEncoder;
import com.thomas15v.crossserver.network.packet.Packet;
import com.thomas15v.crossserver.network.packet.client.PacketBye;
import com.thomas15v.crossserver.network.packet.shared.PacketMessage;
import com.thomas15v.crossserver.network.packet.shared.PacketPlayerStatusChangePacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

/**
 * Created by thomas15v on 25/12/14.
 */
@ToString
public class Client implements CrossServer {

    private int port = 5500;
    private String host = "localhost";
    @Getter
    private final Plugin plugin;

    private Map<String, Server> servers = new HashMap<>();
    @Getter
    @Setter
    private ConnectionStatus status = ConnectionStatus.DISCONNECTED;
    @Getter
    private final PacketConnectionHandler Connection;
    private boolean running = true;

    public Client(Plugin plugin){
        this.plugin = plugin;
        servers.put(plugin.getLocalServer().getName(), plugin.getLocalServer());
        Connection = new PacketConnectionHandler(new ConnectionInitializer(this));
    }

    @Override
    public void run() {
        System.out.println("started!");
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            while (running) {
                Bootstrap b = new Bootstrap();
                b.group(group)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new PacketDecoder(), new PacketEncoder(), Connection);
                            }
                        });
                ChannelFuture f = b.connect(host, port).sync();
                f.channel().closeFuture().sync();
                Thread.sleep(10000);
                System.out.println("Disconnected, reconnecting in 10 sec");
            }
            System.out.println("closed!");
        }catch (Exception e){
            group.shutdownGracefully();
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Server> getServers() {
        return servers.values();
    }

    @Override
    public Server getServer(String server) {
        return servers.get(server);
    }

    public void addServer(Server server){
        servers.put(server.getName(), server);
    }

    @Override
    public void reportPlayerStatus(Player player, PlayerStatus playerStatus) {
        sendPacket(new PacketPlayerStatusChangePacket(player, playerStatus));
    }

    public void sendPacket(Packet packet){
       getConnection().getChannel().sendPacket(packet);
    }

    public void stop(){
        getConnection().getChannel().sendPacket(new PacketBye());
        getConnection().getChannel().disconnect();
        running = false;
    }

    @Override
    public void broadcast(String message) {
        sendPacket(new PacketMessage(message));
    }
}
