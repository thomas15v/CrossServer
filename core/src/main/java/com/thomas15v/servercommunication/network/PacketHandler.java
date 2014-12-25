package com.thomas15v.servercommunication.network;

import com.thomas15v.servercommunication.network.packet.Packet;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by thomas15v on 25/12/14.
 */
public abstract class PacketHandler extends SimpleChannelInboundHandler<Packet> {

}
