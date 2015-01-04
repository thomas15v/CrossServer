package com.thomas15v.crossserver.crossbans.command;

import com.thomas15v.crossserver.api.remote.CrossServer;
import com.thomas15v.crossserver.api.remote.Player;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by thomas15v on 4/01/15.
 */
@RequiredArgsConstructor
public class CommandKick implements CommandExecutor {
    @NonNull
    private CrossServer crossServer;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length > 0) {
            Player player = crossServer.getPlayer(strings[0]);
            if (strings.length > 1)
                player.kick(strings[1]);
            else
                player.kick("You have been kicked for breaking server rules!");
            return true;
        }
        return false;
    }
}
