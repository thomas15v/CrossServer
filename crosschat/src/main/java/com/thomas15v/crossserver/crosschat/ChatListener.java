package com.thomas15v.crossserver.crosschat;

import com.thomas15v.crossserver.api.remote.CrossServer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by thomas15v on 3/01/15.
 */
@RequiredArgsConstructor
public class ChatListener implements Listener {

    @NonNull
    private CrossServer crossServer;

    @NonNull
    private Server server;

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerChat(AsyncPlayerChatEvent event){
        for (com.thomas15v.crossserver.api.remote.Server server : crossServer.getServers()) {
            if (server != this.server)
                server.broadcast("[" + this.server.getServerName() + "]" + String.format(event.getFormat(), event.getPlayer().getDisplayName(), event.getMessage()));
        }
    }

}
