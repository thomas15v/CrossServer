package com.thomas15v.crossserver.network.packet;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.remote.RemotePlayer;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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

    public Player readPlayer(ByteBuf buf){
        return new RemotePlayer(readString(buf));
    }

    public void writePlayer(Player player, ByteBuf buf){
        writeString(player.getName(), buf);
    }

    public abstract void handle(PacketHandler packetHandler);

}
