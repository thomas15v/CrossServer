package com.thomas15v.crossserver.network.packet;

import com.thomas15v.crossserver.network.PacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Created by thomas15v on 25/12/14.
 */
@RequiredArgsConstructor
public abstract class Packet {

    @Getter
    private final int id;

    public abstract Packet decode(ByteBuf buf);

    public abstract Packet encode(ByteBuf buf);

    public void writeString(String s, ByteBuf buf)
    {
        buf.writeShort( s.length() );
        for ( char c : s.toCharArray() )
        {
            buf.writeChar( c );
        }
    }

    public String readString(ByteBuf buf)
    {
        short length = buf.readShort();
        char[] chars = new char[ length ];
        for ( int i = 0; i < length; i++ )
        {
            chars[i] = buf.readChar();
        }
        return new String( chars );
    }

    public String[] readStringArray(ByteBuf buf){
        int length = buf.readInt();
        String[] array = new String[length];
        for (int i = 0; i < length; i++)
            array[i] = readString(buf);
        return array;
    }

    public void writeStringArray(String[] array, ByteBuf buf){
        buf.writeInt(array.length);
        for (String str : array)
            writeString(str, buf);
    }

    public abstract void handle(PacketHandler packetHandler);

}
