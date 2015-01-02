package com.thomas15v.crossserver.bukkit.impl;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.bukkit.CrossServerPlugin;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

/**
 * Created by thomas15v on 27/12/14.
 */
@RequiredArgsConstructor
public class BukkitPlayer implements Player{

    @NonNull
    @Getter
    private CrossServerPlugin plugin;
    @NonNull
    @Getter
    private org.bukkit.entity.Player player;

    @Override
    public UUID getUUID() {
        return player.getUniqueId();
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public void kick(final String kickMessage) {
        plugin.execute(new Runnable() {
            @Override
            public void run() {
                player.kickPlayer(kickMessage);
            }
        });
    }

    @Override
    public void ban(final String banMessage) {
        plugin.execute(new Runnable() {
            @Override
            public void run() {
                player.kickPlayer(banMessage);
            }
        });
    }

    @Override
    public void sendMessage(final String message) {
        plugin.execute(new Runnable() {
            @Override
            public void run() {
                player.sendMessage(message);
            }
        });
    }

    @Override
    public Server getServer() {
        return plugin.getLocalServer();
    }

    @Override
    public void setServer(Server server) {
        System.out.println("Not supported Yet");
    }
}
