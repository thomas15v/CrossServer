package com.thomas15v.crossserver.network.packet.server;

import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.Packet;
import io.netty.buffer.ByteBuf;
import lombok.ToString;

/**
 * Created by thomas15v on 25/12/14.
 */
@ToString
public class PacketAuthentationResult extends Packet {

    private boolean result;

    public PacketAuthentationResult() {
        super(0x2);
    }

    public PacketAuthentationResult(boolean result){
        this();
        this.result = result;
    }

    @Override
    public Packet decode(ByteBuf buf) {
        result = buf.readBoolean();
        return this;
    }

    @Override
    public Packet encode(ByteBuf buf) {
        buf.writeBoolean(result);
        return this;
    }

    @Override
    public void handle(PacketHandler packetHandler) {
        packetHandler.handle(this);
    }

    public boolean getResult(){
        return result;
    }
}
