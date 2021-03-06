package com.thomas15v.crossserver.network.packet.shared;

import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.api.util.ServerStatus;
import com.thomas15v.crossserver.network.packet.Packet;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.ToString;

@ToString
public class PacketServerStatusChanged extends Packet {

    @Getter
    private String serverName;
    @Getter
    private ServerStatus status;

    public PacketServerStatusChanged(Server server){
        this();
        this.serverName = server.getName();
        this.status = server.getStatus();
    }

    public PacketServerStatusChanged() {
        super(0x3);
    }

    @Override
    public Packet decode(ByteBuf buf) {
        this.serverName = readString(buf);
        this.status = ServerStatus.valueOf(readString(buf));
        return this;
    }

    @Override
    public Packet encode(ByteBuf buf) {
        writeString(serverName, buf);
        writeString(status.name(), buf);
        return this;
    }

    @Override
    public void handle(PacketHandler packetHandler) {
        packetHandler.handle(this);
    }
}
