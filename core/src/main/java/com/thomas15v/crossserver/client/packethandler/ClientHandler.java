package com.thomas15v.crossserver.client.packethandler;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.api.util.PlayerStatus;
import com.thomas15v.crossserver.api.util.ServerStatus;
import com.thomas15v.crossserver.client.Client;
import com.thomas15v.crossserver.network.ChannelWrapper;
import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.shared.*;
import com.thomas15v.crossserver.network.remote.RemotePlayer;
import com.thomas15v.crossserver.network.remote.RemoteServer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

/**
 * Created by thomas15v on 26/12/14.
 */
@RequiredArgsConstructor
public class ClientHandler extends PacketHandler{

    @NonNull
    private ChannelWrapper channel;
    @NonNull
    private Client client;

    @Override
    public void handle(PacketServerStatusChanged packet) {
        if (packet.getStatus() == ServerStatus.ONLINE)
            addServer(packet.getServerName());
        else
            client.getServer(packet.getServerName()).setStatus(packet.getStatus());
    }

    @Override
    public void handle(PacketPlayerStatusChangePacket packet) {
        Server server = client.getServer(packet.getServername());
        if (packet.getStatus() == PlayerStatus.JOINED || packet.getStatus() == PlayerStatus.ONLINE)
            server.addPlayer(new RemotePlayer(packet.getPlayername(), server, channel));
        else
            server.removePlayer(packet.getPlayername());
        System.out.println(packet.getPlayername() + " has " + packet.getStatus() + " on " + packet.getServername());
    }


    private Server addServer(String name){
        Server server;
        if (!client.getServers().contains(name)) {
            server = new RemoteServer(name, channel);
            client.addServer(server);
        }
        else
            server = client.getServer(name);
        return server;
    }

    private void addServer(String name, Collection<String> players){
        Server server = addServer(name);

        for (String player : players){
            Player remotePlayer = new RemotePlayer(player, server, channel);
            server.addPlayer(remotePlayer);
        }


    }

    @Override
    public void handle(PacketMessage packet) {
        if (packet.getType() == PacketMessage.MessageType.PLAYER)
            client.getPlugin().getLocalServer().getPlayer(packet.getTarget()).sendMessage(packet.getMessage());
        if (packet.getType() == PacketMessage.MessageType.BROADCAST || packet.getType() == PacketMessage.MessageType.SERVER)
            client.getPlugin().getLocalServer().broadcast(packet.getMessage());
    }

    @Override
    public void handle(PacketInformationUpdate packet) {
        for (String server : packet.getServers().keySet()) {
            addServer(server, packet.getServers().get(server));
        }
    }

    @Override
    public void handle(PacketPlayerDisconnect packet) {
        if (packet.getAction() == PacketPlayerDisconnect.Action.BAN){
            client.getPlugin().getLocalServer().ban(packet.getUsername(), packet.getMessage());
        }else  if (packet.getAction() == PacketPlayerDisconnect.Action.KICK)
            client.getPlayer(packet.getUsername()).kick(packet.getMessage());
    }

    @Override
    public void handle(PacketPayload packet) {
        client.getServer(packet.getTarget()).sendPayLoad(packet.getPayload(), packet.getService());
    }
}
