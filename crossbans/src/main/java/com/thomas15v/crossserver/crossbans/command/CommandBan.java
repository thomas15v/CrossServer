package com.thomas15v.crossserver.crossbans.command;

import com.thomas15v.crossserver.api.Plugin;
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
public class CommandBan implements CommandExecutor {

    @NonNull
    private Plugin crossServer;

    private final String BAN_MESSAGE = "You have been banned for breaking rules!";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length > 0) {
            Player player = crossServer.getCrossServer().getPlayer(strings[0]);
            if (strings.length > 1) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 1; i < strings.length; i++)
                    stringBuilder.append(strings[i]);
                player.ban(stringBuilder.toString());
            }
            else {
                player.ban(BAN_MESSAGE);
                crossServer.getLocalServer().ban(player.getName(), BAN_MESSAGE);
            }

            return true;
        }
        return false;
    }
}
