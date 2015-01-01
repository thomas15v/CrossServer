package com.thomas15v.crossserver.bukkit.impl;

import com.thomas15v.crossserver.api.Task;
import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.api.util.PlayerStatus;
import com.thomas15v.crossserver.api.util.ServerStatus;
import com.thomas15v.crossserver.bukkit.CrossServerPlugin;
import lombok.*;
import org.bukkit.event.Listener;

import java.util.*;

/**
 * Created by thomas15v on 27/12/14.
 */
@RequiredArgsConstructor
public class BukkitServer implements Server, Listener {

    @NonNull
    private CrossServerPlugin plugin;

    private Map<String, Player> players = new HashMap<>();
    @Getter
    private ServerStatus status = ServerStatus.ONLINE;

    @Override
    public String getName() {
        return plugin.getServer().getServerName();
    }

    @Override
    public Collection<Player> getPlayers() {
        return players.values();
    }

    @Override
    public Player getPlayer(String string) {
        return players.get(string);
    }

    @Override
    public void removePlayer(String player) {
        plugin.getCrossServer().reportPlayerStatus(getPlayer(player), PlayerStatus.LEFT);
        players.remove(player);
    }

    @Override
    public void addPlayer(Player player) {
        players.put(player.getName(), player);
        plugin.getCrossServer().reportPlayerStatus(player, PlayerStatus.JOINED);
    }

    @Override
    public void broadcast(final String string) {
        plugin.execute(new Task<Integer>() {
            @Override
            public Integer execute() {
                return plugin.getServer().broadcastMessage(string);
            }
        });
    }
}
