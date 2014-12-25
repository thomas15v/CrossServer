package com.thomas15v.servercommunication.network;

import com.thomas15v.servercommunication.network.packet.Packet;
import com.thomas15v.servercommunication.network.packet.client.PacketLogin;
import io.netty.buffer.ByteBuf;

import java.lang.reflect.Constructor;
import java.security.InvalidKeyException;

/**
 * Created by thomas15v on 25/12/14.
 */
public class Protocol {

    private static final Class<? extends Packet>[] packets = new Class[ 256 ];

    static {
        packets[0x1] = PacketLogin.class;
    }

    private static class NullPacket extends Packet{

        public NullPacket() {
            super(-1);
            System.out.println("nullPacket created");
        }

        @Override
        public Packet decode(ByteBuf buf) {
            return this;
        }

        @Override
        public Packet encode(ByteBuf buf) {
            return this;
        }
    }

    public static Packet getPacket(int id){
        try {
            Constructor<? extends Packet> constructor = packets[id].getDeclaredConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new NullPacket();
    }

}
