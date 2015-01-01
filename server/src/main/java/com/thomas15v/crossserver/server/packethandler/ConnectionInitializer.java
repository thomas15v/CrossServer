package com.thomas15v.crossserver.server.packethandler;

import com.thomas15v.crossserver.network.ChannelWrapper;
import com.thomas15v.crossserver.network.PacketHandler;
import com.thomas15v.crossserver.network.packet.client.PacketLogin;
import com.thomas15v.crossserver.network.packet.server.PacketAuthentationResult;
import com.thomas15v.crossserver.network.packet.server.PacketServerStatusChanged;
import com.thomas15v.crossserver.server.ConnectedServer;
import com.thomas15v.crossserver.server.CrossServer;

/**
 * Created by thomas15v on 26/12/14.
 */
public class ConnectionInitializer extends PacketHandler {

    private String password = "123456";
    private CrossServer crossServer;
    private ChannelWrapper cw;

    public ConnectionInitializer(CrossServer crossServer){
        this.crossServer = crossServer;
    }

    @Override
    public void connected(ChannelWrapper cw) {
        this.cw = cw;
        System.out.println("Incomming Connection from " + cw.getIPadress());
    }

    @Override
    public void disconnected(ChannelWrapper cw) {
        System.out.println(cw.getIPadress() + " lost connection!");
    }

    @Override
    public void handle(PacketLogin packet){
        if (packet.getPassword().equals(password)) {
            if (crossServer.getClients().containsKey(packet.getServerName())) {
                disconnect(cw, "Their is already a server with this name connected!");
                return;
            }
            ConnectedServer connectedServer = new ConnectedServer(packet.getServerName(), cw);
            crossServer.getClients().put(packet.getServerName(), connectedServer);
            System.out.println(packet.getServerName() + " logged in");
            cw.sendPacket(new PacketAuthentationResult(true));
            cw.getConnection().setPacketHandler(new ServerHandler(connectedServer, crossServer));
            crossServer.broadCast(new PacketServerStatusChanged(connectedServer), connectedServer);
        }else
        {
            disconnect(cw, packet.getServerName() + " didn't used the correct password!");
        }
    }

    private void disconnect(ChannelWrapper cw, String reason){
        cw.sendPacket(new PacketAuthentationResult());
        System.out.println(reason);
        cw.disconnect();
    }


}
