package com.thomas15v.crossserver.bukkit.impl;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.api.util.PlayerStatus;
import com.thomas15v.crossserver.api.util.ServerStatus;
import com.thomas15v.crossserver.bukkit.CrossServerPlugin;
import lombok.*;

import java.util.*;

/**
 * Created by thomas15v on 27/12/14.
 */
@ToString
public class BukkitServer implements Server {

    @NonNull
    private CrossServerPlugin plugin;

    private Map<String, Player> players = new HashMap<>();
    @Getter
    @Setter
    private ServerStatus status = ServerStatus.ONLINE;

    public BukkitServer(CrossServerPlugin plugin){
        this.plugin = plugin;
        for (org.bukkit.entity.Player player : plugin.getServer().getOnlinePlayers()) {
            BukkitPlayer bukkitPlayer = new BukkitPlayer(plugin, player);
            players.put(bukkitPlayer.getName(), bukkitPlayer);
        }
    }

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
        plugin.execute(new Runnable() {
            @Override
            public void run() {
                plugin.getServer().broadcastMessage(string);
            }
        });
    }

    @Override
    public void ban(String player, String message) {
        org.bukkit.entity.Player onlineplayer =plugin.getServer().getPlayer(player);
        if (onlineplayer == null)
            plugin.getServer().getOfflinePlayer(player).setBanned(true);
        else {
            onlineplayer.kickPlayer(message);
            onlineplayer.setBanned(true);
        }
    }
}
