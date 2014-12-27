package com.thomas15v.crossserver.network.packet.client;

import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.Packet;
import io.netty.buffer.ByteBuf;
import lombok.Getter;

/**
 * Created by thomas15v on 25/12/14.
 */
public class PacketLogin extends Packet {

    @Getter
    private String serverName;
    @Getter
    private String password;

    public PacketLogin() {
        super(0x1);
    }

    public PacketLogin(String serverName, String password) {
        this();
        this.serverName = serverName;
        this.password = password;
    }

    @Override
    public Packet decode(ByteBuf buf) {
        serverName = readString( buf );
        password = readString( buf );
        return this;
    }

    @Override
    public Packet encode(ByteBuf buf) {
        writeString(serverName, buf);
        writeString(password, buf);
        return this;
    }

    @Override
    public void handle(PacketHandler packetHandler) {
        packetHandler.handle(this);
    }
}
