package com.thomas15v.crossserver.bukkit.impl;

import com.thomas15v.crossserver.api.Task;
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
        return plugin.execute(new Task<UUID>() {
            @Override
            public UUID execute() {
                return player.getUniqueId();
            }
        });
    }

    @Override
    public String getName() {
        return plugin.execute(new Task<String>() {
            @Override
            public String execute() {
                return player.getName();
            }
        });
    }

    @Override
    public void kick(final String kickMessage) {
        plugin.execute(new Task<Void>() {
            @Override
            public Void execute() {
                player.kickPlayer(kickMessage);
                return null;
            }
        });
    }

    @Override
    public void ban(final String banMessage) {
        plugin.execute(new Task<Void>() {
            @Override
            public Void execute() {
                player.kickPlayer(banMessage);
                return null;
            }
        });
    }

    @Override
    public void sendMessage(final String message) {
        plugin.execute(new Task<Void>() {
            @Override
            public Void execute() {
                player.sendMessage(message);
                return null;
            }
        });
    }

    @Override
    public Server getServer() {
        return plugin.getLocalServer();
    }
}