package com.thomas15v.crossserver.server.packethandler;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.api.util.PlayerStatus;
import com.thomas15v.crossserver.network.ChannelWrapper;
import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.client.PacketPlayerStatusChangePacket;
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
    private Server server;

    @Getter
    @NonNull
    private CrossServer crossServer;

    @Override
    public void connected(ChannelWrapper cw) {

    }

    @Override
    public void disconnected(ChannelWrapper cw) {
        crossServer.getClients().remove(server.getName());
    }

    @Override
    public void handle(PacketPlayerStatusChangePacket packet) {
        Player player = packet.getPlayer();
        if (packet.getStatus() == PlayerStatus.JOINED) {
            player.setServer(server);
            server.addPlayer(player);
            System.out.print(player.getName() + " Joined on " + server.getName());
        }else {
            server.removePlayer(player.getName());
            System.out.print(player.getName() + " has " + packet.getStatus().name() + " on " + server.getName());
        }
    }
}
