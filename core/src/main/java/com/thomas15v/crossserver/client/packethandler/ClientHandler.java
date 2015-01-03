package com.thomas15v.crossserver.client.packethandler;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.util.ServerStatus;
import com.thomas15v.crossserver.client.Client;
import com.thomas15v.crossserver.network.ChannelWrapper;
import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.shared.PacketMessage;
import com.thomas15v.crossserver.network.packet.shared.PacketPlayerStatusChangePacket;
import com.thomas15v.crossserver.network.packet.shared.PacketServerStatusChanged;
import com.thomas15v.crossserver.network.remote.RemotePlayer;
import com.thomas15v.crossserver.network.remote.RemoteServer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
            if (!client.getServers().contains(packet.getServerName()))
                client.addServer(new RemoteServer(packet.getServerName(), channel));
        else
            client.getServer(packet.getServerName()).setStatus(packet.getStatus());
    }

    @Override
    public void handle(PacketPlayerStatusChangePacket packet) {
        System.out.println(packet);
        if (!client.getServers().contains(packet.getServername()))
            client.addServer(new RemoteServer(packet.getServername(), channel));
        Player player = new RemotePlayer(packet.getPlayername(), channel);
        client.getServer(packet.getServername()).addPlayer(player);
        System.out.println(packet.getPlayername() + "has " + packet.getStatus() + " on " + packet.getServername());
    }

    @Override
    public void handle(PacketMessage packet) {
        if (packet.getType() == PacketMessage.MessageType.PLAYER)
            client.getPlugin().getLocalServer().getPlayer(packet.getTarget()).sendMessage(packet.getMessage());
        if (packet.getType() == PacketMessage.MessageType.BROADCAST || packet.getType() == PacketMessage.MessageType.SERVER)
            client.getPlugin().getLocalServer().broadcast(packet.getMessage());
        System.out.println(packet.getMessage() + " This is a client message");
    }
}
