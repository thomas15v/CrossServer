package com.thomas15v.crossserver.network.remote;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.network.ChannelWrapper;
import com.thomas15v.crossserver.network.packet.shared.PacketMessage;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Created by thomas15v on 26/12/14.
 */
@RequiredArgsConstructor
public class RemotePlayer implements Player {

    @NonNull
    @Getter
    private String name;

    @NonNull
    @Setter
    private ChannelWrapper channel;

    @Getter
    @Setter
    private Server server;

    @Override
    public UUID getUUID() {
        return null;
    }

    @Override
    public void kick(String kickMessage) {

    }

    @Override
    public void ban(String banMessage) {

    }

    @Override
    public void sendMessage(String message) {
        channel.sendPacket(new PacketMessage(name, PacketMessage.MessageType.PLAYER, message));
    }
}
