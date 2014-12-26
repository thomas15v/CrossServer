package com.thomas15v.crossserver.network;

import com.thomas15v.crossserver.network.packet.Packet;
import com.thomas15v.crossserver.network.packet.client.PacketLogin;
import com.thomas15v.crossserver.network.packet.client.PacketPlayerJoin;
import com.thomas15v.crossserver.network.packet.client.PacketPlayerLeave;
import com.thomas15v.crossserver.network.packet.server.PacketAuthentationResult;
import com.thomas15v.crossserver.network.packet.server.PacketServerStatusChanged;
import io.netty.buffer.ByteBuf;

import java.lang.reflect.Constructor;

/**
 * Created by thomas15v on 25/12/14.
 */
public class Protocol {

    private static final Class<? extends Packet>[] packets = new Class[ 256 ];

    static {
        packets[0x1] = PacketLogin.class;
        packets[0x2] = PacketAuthentationResult.class;
        packets[0x3] = PacketServerStatusChanged.class;
        packets[0x4] = PacketPlayerJoin.class;
        packets[0x5] = PacketPlayerLeave.class;
    }

    private static class NullPacket extends Packet{

        public NullPacket() {
            super(-1);
            System.err.println("Protocol Error!!!! Making NullPackets To avoid crashing.");
            System.err.println("Please reports these bugs!!!");
        }

        @Override
        public Packet decode(ByteBuf buf) {
            return this;
        }

        @Override
        public Packet encode(ByteBuf buf) {
            return this;
        }

        @Override
        public void handle(PacketHandler packetHandler) {}
    }

    public static Packet getPacket(int id){
        try {
            Constructor<? extends Packet> constructor = packets[id].getDeclaredConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            return new NullPacket();
        }
    }

}
