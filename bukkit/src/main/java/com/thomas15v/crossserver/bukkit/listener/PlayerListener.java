package com.thomas15v.crossserver.bukkit.listener;

import com.thomas15v.crossserver.api.Plugin;
import com.thomas15v.crossserver.bukkit.CrossServerPlugin;
import com.thomas15v.crossserver.bukkit.impl.BukkitPlayer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by thomas15v on 27/12/14.
 */
@RequiredArgsConstructor
public class PlayerListener implements Listener {

    @NonNull
    @Getter
    private CrossServerPlugin plugin;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        plugin.getLocalServer().getPlayers().add(new BukkitPlayer(plugin, event.getPlayer()));
    }


}
