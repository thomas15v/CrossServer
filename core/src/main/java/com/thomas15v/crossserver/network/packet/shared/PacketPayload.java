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
public class PacketPayload extends Packet {

    public static final String BROADCAST = "NA";

    @Getter
    private String service;

    private PayLoad payload;
    @Getter
    private byte[] payloadbytes;

    @Getter
    private String target;

    public PacketPayload(){
        super(0x9);
    }

    public PacketPayload(String target, String service, PayLoad payload){
        this();
        this.target = target;
        this.service = service;
        this.payload = payload;
    }

    public PacketPayload(String service, PayLoad payload){
        this(BROADCAST, service, payload);
    }

    public Packet encode(ByteBuf buf) {
        writeString(target, buf);
        writeString(service, buf);

        byte[] bytes;
        if (payload!= null)
            bytes = getBytes(payload);
        else
            bytes = payloadbytes;

        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
        return this;
    }

    @Override
    public Packet decode(ByteBuf buf){
        target = readString(buf);
        service = readString(buf);
        int length = buf.readInt();
        byte[] bytes = new byte[length];
        buf.readBytes(bytes);
        this.payloadbytes = bytes;
        return this;
    }

    private byte[] getBytes(Object object){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] bytes = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(payload);
            bytes = bos.toByteArray();
            bos.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    private PayLoad getObject(byte[] bytes){
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;
        PayLoad object = null;
        try {
            in = new ObjectInputStream(bis);
            object = (PayLoad) in.readObject();
            in.close();
            bis.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    public PayLoad getPayload() {
        if (payload == null)
            payload = getObject(payloadbytes);
        return payload;
    }

    @Override
    public void handle(PacketHandler packetHandler) {
        packetHandler.handle(this);
    }

}
