package com.thomas15v.crossserver.network.packet.shared;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.Packet;
import io.netty.buffer.ByteBuf;
import lombok.Getter;

import java.util.*;

/**
 * Created by thomas15v on 3/01/15.
 */
public class PacketInformationUpdate extends Packet {

    @Getter
    private Map<String, Collection<String>> servers;

    public PacketInformationUpdate() {
        super(0x7);
        servers = new HashMap<>();
    }

    public PacketInformationUpdate(Collection<Server> servers) {
        this();
        for (Server server : servers) {
            addServer(server);
        }

    }

    public void addServer(Server server){
        List<String> players = new ArrayList<>();
        for (Player player : server.getPlayers())
            players.add(player.getName());
        servers.put(server.getName(), players);
    }

    public PacketInformationUpdate(Map<String, Collection<String>> servers){
        this();
        this.servers = servers;
    }

    @Override
    public Packet decode(ByteBuf buf) {
        int servers = buf.readInt();
        System.out.println(servers);
        for (int s = 0; s < servers; s++){
            String servername = readString(buf);
            int players = buf.readInt();
            List<String> playersarray = new ArrayList<>();
            for (int p = 0; p < players; p++)
                playersarray.add(readString(buf));
            this.servers.put(servername, playersarray);
        }
        return this;
    }

    @Override
    public Packet encode(ByteBuf buf) {
        buf.writeInt(servers.size());
        for (String server : servers.keySet()){
            writeString(server, buf);
            Collection<String> players = servers.get(server);
            buf.writeInt(players.size());
            for (String player : players)
                writeString(player, buf);

        }
        return this;
    }

    @Override
    public void handle(PacketHandler packetHandler) {
        packetHandler.handle(this);
    }
}
