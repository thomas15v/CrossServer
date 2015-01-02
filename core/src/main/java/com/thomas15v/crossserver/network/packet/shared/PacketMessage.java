package com.thomas15v.crossserver.network.packet.shared;

import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.Packet;
import io.netty.buffer.ByteBuf;
import lombok.Getter;

/**
 * Created by thomas15v on 2/01/15.
 */
public class PacketMessage extends Packet {

    public enum MessageType {
        SERVER,
        PLAYER,
        CHANNEL
    }

    @Getter
    private String target;
    @Getter
    private MessageType type;
    @Getter
    private String message;

    public PacketMessage() {
        super(0x6);
    }

    public PacketMessage(String target, MessageType type, String message){
        this();
        this.target = target;
        this.type = type;
        this.message = message;
    }

    @Override
    public Packet decode(ByteBuf buf) {
        target = readString(buf);
        type = MessageType.valueOf(readString(buf));
        message = readString(buf);
        return this;
    }

    @Override
    public Packet encode(ByteBuf buf) {
        writeString(target, buf);
        writeString(type.name(), buf);
        writeString(message, buf);
        return this;
    }

    @Override
    public void handle(PacketHandler packetHandler) {
        packetHandler.handle(this);
    }
}
