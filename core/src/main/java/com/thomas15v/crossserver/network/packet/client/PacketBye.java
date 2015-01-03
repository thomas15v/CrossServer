package com.thomas15v.crossserver.network.packet.client;

import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.Packet;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by thomas15v on 2/01/15.
 */
@ToString
public class PacketBye extends Packet {

    @Getter
    private boolean crashed;

    public PacketBye() {
        super(0x5);
    }

    public PacketBye(boolean crashed){
        this();
        this.crashed = crashed;
    }

    @Override
    public Packet decode(ByteBuf buf) {
        crashed = buf.readBoolean();
        return this;
    }

    @Override
    public Packet encode(ByteBuf buf) {
        buf.writeBoolean(crashed);
        return this;
    }

    @Override
    public void handle(PacketHandler packetHandler) {
        packetHandler.handle(this);
    }
}
