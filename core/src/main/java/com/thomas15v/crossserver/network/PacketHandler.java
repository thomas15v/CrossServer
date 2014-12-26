package com.thomas15v.crossserver.network;

import com.thomas15v.crossserver.network.packet.Packet;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by thomas15v on 25/12/14.
 */
public interface PacketHandler{

    void handle(Packet packet);

    void connected(ChannelWrapper cw);

    void disconnected(ChannelWrapper cw);
}
