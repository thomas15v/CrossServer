package com.thomas15v.crossserver.crosschat.channel;

import com.thomas15v.crossserver.api.remote.CrossServer;
import com.thomas15v.crossserver.api.remote.Player;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Created by thomas15v on 15/01/15.
 */
@RequiredArgsConstructor
public class Channel {

    @NonNull
    private String channelprefix;

    @NonNull
    private CrossServer crossServer;

    private List<Player> playerList;

    public void send(Player player, String message){

    }

}
