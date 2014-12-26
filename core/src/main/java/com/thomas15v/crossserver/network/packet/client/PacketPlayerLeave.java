package com.thomas15v.crossserver.network.packet.client;

import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.Packet;
import io.netty.buffer.ByteBuf;

/**
 * Created by thomas15v on 26/12/14.
 */
public class PacketPlayerLeave extends Packet {

    public PacketPlayerLeave() {
        super(0x5);
    }

    @Override
    public Packet decode(ByteBuf buf) {
        return null;
    }

    @Override
    public Packet encode(ByteBuf buf) {
        return null;
    }

    @Override
    public void handle(PacketHandler packetHandler) {

    }
}
