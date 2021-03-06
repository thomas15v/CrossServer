package com.thomas15v.crossserver.server.packethandler;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.util.PlayerStatus;
import com.thomas15v.crossserver.api.util.ServerStatus;
import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.client.PacketBye;
import com.thomas15v.crossserver.network.packet.shared.*;
import com.thomas15v.crossserver.network.remote.RemotePlayer;
import com.thomas15v.crossserver.network.remote.RemoteServer;
import com.thomas15v.crossserver.server.CrossServer;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by thomas15v on 26/12/14.
 */
@RequiredArgsConstructor
public class ServerHandler extends PacketHandler {

    @Getter
    @NonNull
    private RemoteServer server;

    @Getter
    @NonNull
    private CrossServer crossServer;

    @Override
    public void handle(PacketPlayerStatusChangePacket packet) {
        crossServer.broadCast(packet, server);
        Player player = new RemotePlayer(packet.getPlayername(), server, server.getChannel());
        if (packet.getStatus() == PlayerStatus.JOINED) {
            server.addPlayer(player);
            System.out.println(player.getName() + " Joined on " + server.getName());
        }else {
            server.removePlayer(packet.getPlayername());
            System.out.println(packet.getPlayername() + " has " + packet.getStatus().name() + " on " + server.getName());
        }
    }

    @Override
    public void handle(PacketBye packet) {
        if (server.getStatus() == ServerStatus.OFFLINE)
            return;
        crossServer.getClients().remove(server.getName());
        server.setStatus(ServerStatus.OFFLINE);
        crossServer.broadCast(new PacketServerStatusChanged(server), server);
        server.getChannel().disconnect();
        System.out.println(server.getName() + " has disconnected; crashed: " + packet.isCrashed());
    }

    @Override
    public void handle(PacketMessage packet) {
        if (packet.getType() == PacketMessage.MessageType.PLAYER)
            crossServer.getPlayer(packet.getTarget()).sendMessage(packet.getMessage());
        else if (packet.getType() == PacketMessage.MessageType.BROADCAST)
            getCrossServer().broadCast(packet, server);
    }

    @Override
    public void handle(PacketPlayerDisconnect packet) {
        if (packet.getAction() == PacketPlayerDisconnect.Action.BAN)
            crossServer.broadCast(packet, server);
        else if (packet.getAction() == PacketPlayerDisconnect.Action.KICK)
            crossServer.getPlayer(packet.getUsername()).kick(packet.getMessage());
    }

    @Override
    public void handle(PacketPayload packet) {
        crossServer.getServer(packet.getTarget()).getChannel().sendPacket(packet);
    }
}
