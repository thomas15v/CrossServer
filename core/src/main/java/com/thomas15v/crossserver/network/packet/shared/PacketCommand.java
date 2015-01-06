package com.thomas15v.crossserver.network.packet.shared;

import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.Packet;
import io.netty.buffer.ByteBuf;
import lombok.Getter;

/**
 * Created by thomas15v on 5/01/15.
 */
public class PacketCommand extends Packet {

    @Getter
    private String target;

    private String executer;

    @Getter
    private String command;

    @Getter
    private String[] arguments;


    public PacketCommand(String target, String executer, String command, String... arguments){
        this();
        this.target = target;
        this.executer = executer;
        this.command = command;
        this.arguments = arguments;
    }

    public PacketCommand() {
        super(0x10);
    }


    @Override
    public Packet decode(ByteBuf buf) {
        this.target = readString(buf);
        this.executer = readString(buf);
        this.command = readString(buf);
        this.arguments = readStringArray(buf);
        return this;
    }

    @Override
    public Packet encode(ByteBuf buf) {
        writeString(target, buf);
        writeString(executer, buf);
        writeString(command, buf);
        writeStringArray(arguments, buf);
        return this;
    }

    @Override
    public void handle(PacketHandler packetHandler) {
        packetHandler.handle(this);
    }
}
