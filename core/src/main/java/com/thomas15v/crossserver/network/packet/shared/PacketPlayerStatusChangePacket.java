package com.thomas15v.crossserver.network.packet.shared;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.util.PlayerStatus;
import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.Packet;
import io.netty.buffer.ByteBuf;
import lombok.Getter;

/**
 * This packets gives a list of new players,
 */
public class PacketPlayerStatusChangePacket extends Packet {

    @Getter
    private String playername;
    @Getter
    private String servername;

    @Getter
    private PlayerStatus status;

    public PacketPlayerStatusChangePacket(Player player, PlayerStatus status){
        this(player.getName(), player.getServer().getName(), status);
    }

    public PacketPlayerStatusChangePacket(String playername, String servername, PlayerStatus playerStatus){
        this();
        this.playername = playername;
        this.servername = servername;
        this.status = playerStatus;
    }

    public PacketPlayerStatusChangePacket() {
        super(0x4);
    }

    @Override
    public Packet decode(ByteBuf buf) {
        this.playername = readString(buf);
        this.servername = readString(buf);
        this.status = PlayerStatus.valueOf(readString(buf));
        return this;
    }

    @Override
    public Packet encode(ByteBuf buf) {
        writeString(playername, buf);
        writeString(servername, buf);
        writeString(status.name(), buf);
        return this;
    }

    @Override
    public void handle(PacketHandler packetHandler) {
        packetHandler.handle(this);
    }
}
