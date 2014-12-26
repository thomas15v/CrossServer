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
            ConnectedServer connectedServer =  new ConnectedServer(packet.getServerName(), cw);
            crossServer.getClients().put(packet.getServerName(), connectedServer);
            System.out.println(packet.getServerName() + " logged in");
            cw.sendPacket(new PacketAuthentationResult(true));
            cw.getConnection().setPacketHandler(new ServerHandler());
            crossServer.broadCast(new PacketServerStatusChanged(connectedServer), connectedServer);

        }else
        {
            cw.sendPacket(new PacketAuthentationResult());
            System.out.println(packet.getServerName() + " didn't used the correct password!");
            cw.disconnect();
        }
    }


}
