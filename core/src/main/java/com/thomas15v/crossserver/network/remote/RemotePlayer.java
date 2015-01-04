package com.thomas15v.crossserver.network.remote;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.network.ChannelWrapper;
import com.thomas15v.crossserver.network.packet.shared.PacketMessage;
import com.thomas15v.crossserver.network.packet.shared.PacketPlayerDisconnect;
import lombok.*;

import java.util.UUID;

/**
 * Created by thomas15v on 26/12/14.
 */
@RequiredArgsConstructor
public class RemotePlayer implements Player {

    @NonNull
    @Getter
    private String name;

    @Getter
    @Setter
    @NonNull
    private Server server;
    @NonNull
    @Setter
    private ChannelWrapper channel;

    @Override
    public UUID getUUID() {
        return null;
    }

    @Override
    public void kick(String kickMessage) {
        channel.sendPacket(new PacketPlayerDisconnect(PacketPlayerDisconnect.Action.KICK, name, kickMessage));
    }

    @Override
    public void ban(String banMessage) {
        channel.sendPacket(new PacketPlayerDisconnect(PacketPlayerDisconnect.Action.BAN, name, banMessage));
    }

    @Override
    public void sendMessage(String message) {
        channel.sendPacket(new PacketMessage(name, PacketMessage.MessageType.PLAYER, message));
    }

    @Override
    public String toString() {
        return "RemotePlayer(name:" + name + " server:" + server.getName() + ")";
    }
}
