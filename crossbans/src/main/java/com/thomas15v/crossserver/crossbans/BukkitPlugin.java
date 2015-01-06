package com.thomas15v.crossserver.crossbans;

import com.thomas15v.crossserver.api.Plugin;
import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.crossbans.command.CommandBan;
import com.thomas15v.crossserver.crossbans.command.CommandKick;
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
        this.plugin = ((Plugin) getServer().getPluginManager().getPlugin("CrossServer"));
        getCommand("cban").setExecutor(new CommandBan(plugin));
        getCommand("ckick").setExecutor(new CommandKick(plugin.getCrossServer()));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        String commandstring = command.getName();
        List<String> suggestions = new ArrayList<>();
        if (commandstring.equalsIgnoreCase("kick") || commandstring.equalsIgnoreCase("ban"))
            for (Player player : plugin.getCrossServer().getPlayers())
                suggestions.add(player.getName());
        return suggestions;

    }
}
