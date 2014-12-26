package com.thomas15v.crossserver.network;

import com.thomas15v.crossserver.network.packet.Packet;
import com.thomas15v.crossserver.network.packet.client.PacketLogin;
import com.thomas15v.crossserver.network.packet.server.PacketAuthentationResult;
import com.thomas15v.crossserver.network.packet.server.PacketServerStatusChanged;

/**
 * Created by thomas15v on 25/12/14.
 */
public class PacketHandler{

    public void connected(ChannelWrapper cw){};

    public void disconnected(ChannelWrapper cw){};

    @Deprecated
    public void handle(Packet packet){
        throw new UnsupportedOperationException();
    }

    public void handle(PacketLogin packet){}

    public void handle(PacketAuthentationResult packet){}

    public void handle(PacketServerStatusChanged packetServerJoined){}
}
