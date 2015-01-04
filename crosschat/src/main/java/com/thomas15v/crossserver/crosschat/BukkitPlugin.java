package com.thomas15v.crossserver.crosschat;

import com.thomas15v.crossserver.api.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by thomas15v on 3/01/15.
 */
public class BukkitPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Plugin plugin = (Plugin) getServer().getPluginManager().getPlugin("CrossServer");
        getServer().getPluginManager().registerEvents(new ChatListener(plugin.getCrossServer(), getServer()), this);
    }
}
