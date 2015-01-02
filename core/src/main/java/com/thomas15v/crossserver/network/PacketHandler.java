package com.thomas15v.crossserver.network;

import com.thomas15v.crossserver.network.packet.Packet;
import com.thomas15v.crossserver.network.packet.client.PacketBye;
import com.thomas15v.crossserver.network.packet.client.PacketLogin;
import com.thomas15v.crossserver.network.packet.shared.PacketMessage;
import com.thomas15v.crossserver.network.packet.shared.PacketPlayerStatusChangePacket;
import com.thomas15v.crossserver.network.packet.server.PacketAuthentationResult;
import com.thomas15v.crossserver.network.packet.shared.PacketServerStatusChanged;

/**
 * Created by thomas15v on 25/12/14.
 */
public class PacketHandler{

    public void connected(ChannelWrapper cw){}

    public void disconnected(ChannelWrapper cw){}

    @Deprecated
    public void handle(Packet packet){
        throw new UnsupportedOperationException();
    }

    public void handle(PacketLogin packet){}

    public void handle(PacketAuthentationResult packet){}

    public void handle(PacketServerStatusChanged packet){}

    public void handle(PacketPlayerStatusChangePacket packet){}

    public void handle(PacketBye packet){}

    public void handle(PacketMessage packet){}

}
