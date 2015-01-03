package com.thomas15v.crossserver.server.packethandler;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.util.PlayerStatus;
import com.thomas15v.crossserver.api.util.ServerStatus;
import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.client.PacketBye;
import com.thomas15v.crossserver.network.packet.shared.PacketMessage;
import com.thomas15v.crossserver.network.packet.shared.PacketPlayerStatusChangePacket;
import com.thomas15v.crossserver.network.packet.shared.PacketServerStatusChanged;
import com.thomas15v.crossserver.network.remote.RemotePlayer;
import com.thomas15v.crossserver.server.ConnectedServer;
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
    private ConnectedServer server;

    @Getter
    @NonNull
    private CrossServer crossServer;

    @Override
    public void handle(PacketPlayerStatusChangePacket packet) {
        crossServer.broadCast(packet, server);
        Player player = new RemotePlayer(packet.getPlayername(), server.getChannel());
        if (packet.getStatus() == PlayerStatus.JOINED) {
            player.setServer(server);
            server.addPlayer(player);
            System.out.println(player.getName() + " Joined on " + server.getName());
        }else {
            server.removePlayer(player.getName());
            System.out.println(player.getName() + " has " + packet.getStatus().name() + " on " + server.getName());
        }
    }

    @Override
    public void handle(PacketBye packet) {
        if (server.getStatus() == ServerStatus.OFFLINE)
            return;
        crossServer.getClients().remove(server.getName());
        server.setStatus(ServerStatus.OFFLINE);
        crossServer.broadCast(new PacketServerStatusChanged(server), server);
        server.disconnect();
        System.out.println(server.getName() + " has disconnected; crashed: " + packet.isCrashed());
    }

    @Override
    public void handle(PacketMessage packet) {
        if (packet.getType() == PacketMessage.MessageType.PLAYER)
            crossServer.getPlayer(packet.getTarget()).sendMessage(packet.getMessage());
        else if (packet.getType() == PacketMessage.MessageType.BROADCAST)
            getCrossServer().broadCast(packet, server);
    }
}
