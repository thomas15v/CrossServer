package com.thomas15v.servercommunication.network.packet.server;

import com.thomas15v.servercommunication.network.packet.Packet;
import io.netty.buffer.ByteBuf;

/**
 * Created by thomas15v on 25/12/14.
 */
public class PacketAuthentationResult extends Packet {

    public enum AuthentationResult{
        SUCCEDED,
        FAILED_WRONG_PASSWORD;
    }

    public PacketAuthentationResult() {
        super(0x2);
    }

    @Override
    public Packet decode(ByteBuf buf) {
        return this;
    }

    @Override
    public Packet encode(ByteBuf buf) {
        return this;
    }
}
