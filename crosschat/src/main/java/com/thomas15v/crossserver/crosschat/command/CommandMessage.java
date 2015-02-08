package com.thomas15v.crossserver.crosschat.command;

import com.thomas15v.crossserver.api.remote.CrossServer;
import com.thomas15v.crossserver.api.remote.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by thomas15v on 17/01/15.
 */
public class CommandMessage implements CommandExecutor {

    private CrossServer crossServer;

    public CommandMessage(CrossServer crossServer){
        this.crossServer = crossServer;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length > 1)
            return false;
        String senderName = commandSender.getName();
        String recievername = strings[0];
        StringBuilder messageBuilder = new StringBuilder();
        for (int i = 1; i < strings.length; i++)
            messageBuilder.append(strings[i]).append(" ");
        String message = messageBuilder.toString();

        Player player = crossServer.getPlayer(recievername);
        player.sendMessage(new StringBuilder().
                        append("[").
                        append(senderName).
                        append("->me] ").
                        append(message).toString()
        );
        commandSender.sendMessage(new StringBuilder().
                        append("[me->").
                        append(player.getName()).
                        append("] ").
                        append(message).toString()
        );



        return false;
    }
}
