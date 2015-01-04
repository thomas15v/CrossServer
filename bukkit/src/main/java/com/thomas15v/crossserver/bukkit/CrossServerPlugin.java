package com.thomas15v.crossserver.bukkit;

import com.thomas15v.crossserver.api.Plugin;
import com.thomas15v.crossserver.api.remote.CrossServer;
import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.bukkit.impl.BukkitServer;
import com.thomas15v.crossserver.bukkit.listener.PlayerListener;
import com.thomas15v.crossserver.client.Client;
import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Created by thomas15v on 27/12/14.
 */
public class CrossServerPlugin extends JavaPlugin implements Plugin {

    @Getter
    private CrossServer crossServer;

    @Getter
    private Server localServer;

    @Override
    public void onEnable() {
        this.localServer = new BukkitServer(this);
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        this.crossServer = new Client(this);
        new Thread(this.crossServer).start();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int count = 1;
        for (Server server : crossServer.getServers()) {
            sender.sendMessage(count + " " + server.toString());
            count++;
        }
        return true;
    }

    @Override
    public void onDisable() {
        crossServer.stop();
    }

    @Override
    public String getPassword() {
        return "123456";
    }

    @Override
    public void execute(Runnable task) {
        getServer().getScheduler().runTask(this, task);
    }
}
