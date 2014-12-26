package com.thomas15v.crossserver.network.packet.server;

import com.thomas15v.crossserver.network.packet.Packet;
import io.netty.buffer.ByteBuf;
import lombok.Getter;

/**
 * Created by thomas15v on 25/12/14.
 */
public class PacketAuthentationResult extends Packet {

    @Getter
    private boolean authentationResult;

    public PacketAuthentationResult() {
        super(0x2);
    }

    @Override
    public Packet decode(ByteBuf buf) {
        authentationResult = buf.readBoolean();
        return this;
    }

    @Override
    public Packet encode(ByteBuf buf) {
        buf.writeInt(getId());
        buf.writeBoolean(authentationResult);
        return this;
    }
}
