package com.thomas15v.crossserver.bukkit;

import com.thomas15v.crossserver.api.PayLoad;
import com.thomas15v.crossserver.api.Plugin;
import com.thomas15v.crossserver.api.event.EventListener;
import com.thomas15v.crossserver.api.event.events.payload.PayloadRecievedEvent;
import com.thomas15v.crossserver.api.remote.CrossServer;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.bukkit.impl.BukkitServer;
import com.thomas15v.crossserver.bukkit.listener.PlayerListener;
import com.thomas15v.crossserver.client.Client;
import com.thomas15v.crossserver.config.ConfigFile;
import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;


/**
 * Created by thomas15v on 27/12/14.
 */
public class CrossServerPlugin extends JavaPlugin implements Plugin {

    @Getter
    private CrossServer crossServer;

    @Getter
    private Server localServer;

    private ConfigFile configFile;
    @Getter
    private String serverAdress;
    @Getter
    private int serverPort;
    @Getter
    private String password;

    @Override
    public void onEnable() {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();
        this.configFile = new ConfigFile(new File(getDataFolder(), "config.conf"), this.getClassLoader());
        LoadConfig();
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
    public InputStream getResource(String filename) {
        return super.getResource(filename);
    }

    @Override
    public void onDisable() {
        crossServer.stop();
    }

    @Override
    public void execute(Runnable task) {
        getServer().getScheduler().runTask(this, task);
    }

    private void LoadConfig() {
        serverAdress = configFile.getConfig().getString("server-ip");
        serverAdress = serverAdress.equals("") ? "127.0.0.1" : serverAdress;
        serverPort = configFile.getConfig().getInt("port");
        password = configFile.getConfig().getString("password");
    }
}
