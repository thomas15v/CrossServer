package com.thomas15v.crossserver.network.packet.shared;

import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.Packet;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by thomas15v on 2/01/15.
 */
@ToString
public class PacketMessage extends Packet {

    public enum MessageType {
        SERVER,
        PLAYER,
        CHANNEL,
        BROADCAST
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

    public PacketMessage(String message){
        this("NA",MessageType.BROADCAST, message);
    }

    public PacketMessage(String target, MessageType type, String message){
        this();
        this.type = type;
        this.target = target;
        this.message = message;
    }

    @Override
    public Packet decode(ByteBuf buf) {
        type = MessageType.valueOf(readString(buf));
        target = readString(buf);
        message = readString(buf);
        return this;
    }

    @Override
    public Packet encode(ByteBuf buf) {
        writeString(type.name(), buf);
        writeString(target, buf);
        writeString(message, buf);
        return this;
    }

    @Override
    public void handle(PacketHandler packetHandler) {
        packetHandler.handle(this);
    }
}
