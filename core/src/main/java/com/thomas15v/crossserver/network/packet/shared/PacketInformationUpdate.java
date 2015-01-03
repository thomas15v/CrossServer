package com.thomas15v.crossserver.network.packet.shared;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.Packet;
import io.netty.buffer.ByteBuf;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by thomas15v on 3/01/15.
 */
public class PacketInformationUpdate extends Packet {

    private List<Server> servers;

    private Map<String, List<String>> serverlist;

    public PacketInformationUpdate() {
        super(0x7);
    }

    public PacketInformationUpdate(List<Server> servers) {
        this();
        this.servers = servers;
    }

    @Override
    public Packet decode(ByteBuf buf) {
        //read
        buf.writeInt(servers.size());
        for (Server server : servers){
            writeString(server.getName(), buf);
            Collection<Player> players = server.getPlayers();
            buf.writeInt(players.size());
            for (Player player : players)
                writeString(player.getName(), buf);

        }
        return this;
    }

    @Override
    public Packet encode(ByteBuf buf) {
        //write
        return this;
    }

    @Override
    public void handle(PacketHandler packetHandler) {
        packetHandler.handle(this);
    }
}
