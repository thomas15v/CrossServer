package com.thomas15v.crossserver.bukkit;

import com.thomas15v.crossserver.api.Plugin;
import com.thomas15v.crossserver.api.Task;
import com.thomas15v.crossserver.api.remote.Server;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by thomas15v on 27/12/14.
 */
public class CrossServerPlugin extends JavaPlugin implements Plugin {

    @Override
    public void onEnable() {

    }

    @Override
    public Server getLocalServer() {
        return null;
    }

    @Override
    public String getPassword() {
        return "123456";
    }

    @Override
    public <I> I execute(final Task<I> task) {
        try {
            return getServer().getScheduler().callSyncMethod(this, new Callable<I>() {
                @Override
                public I call() throws Exception {
                    return task.execute();
                }
            }).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
