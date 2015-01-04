package com.thomas15v.crossserver.network.packet.shared;

import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.Packet;
import io.netty.buffer.ByteBuf;
import lombok.Getter;

/**
 * Created by thomas15v on 4/01/15.
 */
public class PacketPlayerDisconnect extends Packet {

    public enum Action{
        BAN,
        KICK
    }

    @Getter
    private Action action;

    @Getter
    private String username;

    @Getter
    private String message;

    public PacketPlayerDisconnect(Action action, String username, String message){
        this();
        this.action = action;
        this.username = username;
        this.message = message;
    }

    public PacketPlayerDisconnect() {
        super(0x8);
    }

    @Override
    public Packet decode(ByteBuf buf) {
        this.action = Action.valueOf(readString(buf));
        this.username = readString(buf);
        this.message = readString(buf);
        return this;
    }

    @Override
    public Packet encode(ByteBuf buf) {
        writeString(action.name(), buf);
        writeString(username, buf);
        writeString(message, buf);
        return this;
    }

    @Override
    public void handle(PacketHandler packetHandler) {
        packetHandler.handle(this);
    }
}
