package com.thomas15v.crossserver.network.packet.shared;

import com.thomas15v.crossserver.api.PayLoad;
import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.Packet;
import io.netty.buffer.ByteBuf;
import lombok.Getter;

import java.io.*;
/**
 * Created by thomas15v on 5/01/15.
 */
public class PacketPayload<I extends PayLoad> extends Packet {

    public static final String BROADCAST = "NA";

    @Getter
    private String service;
    @Getter
    private I payload;
    @Getter
    private String target;

    public PacketPayload(){
        super(0x9);
    }

    public PacketPayload(String target, String service, I payload){
        this();
        this.target = target;
        this.service = service;
        this.payload = payload;
    }

    public PacketPayload(String service, I payload){
        this(BROADCAST, service, payload);
    }

    public Packet encode(ByteBuf buf) {
        writeString(target, buf);
        writeString(service, buf);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(payload);
            byte[] bytes = bos.toByteArray();
            buf.writeInt(bytes.length);
            for (byte b : bytes)
                buf.writeByte(b);
            bos.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public Packet decode(ByteBuf buf){
        target = readString(buf);
        service = readString(buf);

        int length = buf.readInt();
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++)
            bytes[i] = buf.readByte();
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            payload = (I) in.readObject();
            in.close();
            bis.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public void handle(PacketHandler packetHandler) {
        packetHandler.handle(this);
    }

}
