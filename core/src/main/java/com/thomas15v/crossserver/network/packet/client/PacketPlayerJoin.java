package com.thomas15v.crossserver.network.packet.client;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.Packet;
import io.netty.buffer.ByteBuf;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This packets gives a list of new players,
 */
public class PacketPlayerJoin extends Packet {

    @Getter
    public Player player;

    public PacketPlayerJoin(Player player){
        this();
        this.player = player;
    }

    public PacketPlayerJoin() {
        super(0x4);
    }

    @Override
    public Packet decode(ByteBuf buf) {
        return this;
    }

    @Override
    public Packet encode(ByteBuf buf) {
        return this;
    }

    @Override
    public void handle(PacketHandler packetHandler) {

    }
}
