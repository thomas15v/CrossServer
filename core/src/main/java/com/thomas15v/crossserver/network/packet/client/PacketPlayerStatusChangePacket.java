package com.thomas15v.crossserver.network.packet.client;

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
    private Player player;
    @Getter
    private PlayerStatus status;

    public PacketPlayerStatusChangePacket(Player player, PlayerStatus status){
        this();
        this.player = player;
        this.status = status;
    }

    public PacketPlayerStatusChangePacket() {
        super(0x4);
    }

    @Override
    public Packet decode(ByteBuf buf) {
        System.out.println("decode: " + this);
        this.player = readPlayer(buf);
        this.status = PlayerStatus.valueOf(readString(buf));
        return this;
    }

    @Override
    public Packet encode(ByteBuf buf) {
        writePlayer(player, buf);
        writeString(status.name(), buf);
        return this;
    }

    @Override
    public void handle(PacketHandler packetHandler) {
        packetHandler.handle(this);
    }
}
