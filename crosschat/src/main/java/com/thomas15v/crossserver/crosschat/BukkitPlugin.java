package com.thomas15v.crossserver.crosschat;

import com.thomas15v.crossserver.api.Plugin;
import com.thomas15v.crossserver.api.remote.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas15v on 3/01/15.
 */
public class BukkitPlugin extends JavaPlugin {

    private Plugin plugin;

    @Override
    public void onEnable() {
        this.plugin = (Plugin) getServer().getPluginManager().getPlugin("CrossServer");
        getServer().getPluginManager().registerEvents(new ChatListener(plugin.getCrossServer(), getServer()), this);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        String commandstring = command.getName();
        List<String> suggestions = new ArrayList<>();
        if (commandstring.equalsIgnoreCase("cmsg"))
            for (Player player : plugin.getCrossServer().getPlayers())
                suggestions.add(player.getName());
        return suggestions;
    }
}
