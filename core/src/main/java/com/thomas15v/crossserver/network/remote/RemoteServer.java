package com.thomas15v.crossserver.network.remote;

import com.thomas15v.crossserver.api.PayLoad;
import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.api.util.ServerStatus;
import com.thomas15v.crossserver.network.ChannelWrapper;
import com.thomas15v.crossserver.network.packet.shared.PacketMessage;
import com.thomas15v.crossserver.network.packet.shared.PacketPayload;
import com.thomas15v.crossserver.network.packet.shared.PacketPlayerDisconnect;
import lombok.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thomas15v on 26/12/14.
 */
@RequiredArgsConstructor
@ToString
public class RemoteServer implements Server {

    @Getter
    @NonNull
    private String Name;

    private Map<String,Player> players = new HashMap<>();

    @Getter
    @Setter
    private ServerStatus status = ServerStatus.ONLINE;

    @NonNull
    @Getter
    @Setter
    private ChannelWrapper channel;

    @Override
    public Collection<Player> getPlayers() {
        return players.values();
    }

    @Override
    public Player getPlayer(String player) {
        return players.get(player);
    }

    @Override
    public void removePlayer(String player) {
        players.remove(player);
    }

    @Override
    public void addPlayer(Player player) {
        players.put(player.getName(), player);
    }

    @Override
    public void broadcast(String message) {
        channel.sendPacket(new PacketMessage(message));
    }

    @Override
    public void ban(String player, String message) {
        channel.sendPacket(new PacketPlayerDisconnect(PacketPlayerDisconnect.Action.BAN, player, message));
    }

    @Override
    public void sendPayLoad(PayLoad payLoad, String service) {
        channel.sendPacket(new PacketPayload(getName(), service, payLoad));
    }
}
