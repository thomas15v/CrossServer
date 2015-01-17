package com.thomas15v.crossserver.network;

import com.thomas15v.crossserver.network.packet.Packet;
import com.thomas15v.crossserver.network.packet.client.PacketBye;
import com.thomas15v.crossserver.network.packet.client.PacketLogin;
import com.thomas15v.crossserver.network.packet.shared.*;
import com.thomas15v.crossserver.network.packet.server.PacketAuthentationResult;

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

    public void handle(PacketInformationUpdate packet){}

    public void handle(PacketPlayerDisconnect packet){}

    public void handle(PacketPayload packet){}

    public void handle(PacketCommand packet){}
}
