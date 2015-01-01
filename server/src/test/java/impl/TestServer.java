package impl;

import com.thomas15v.crossserver.api.Plugin;
import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.api.util.PlayerStatus;
import com.thomas15v.crossserver.api.util.ServerStatus;
import lombok.Getter;

import java.util.*;

/**
 * Created by thomas15v on 26/12/14.
 */
public class TestServer implements Server {

    @Getter
    private String name;
    @Getter
    private ServerStatus status;

    private Plugin plugin;

    private Map<String, Player> players = new HashMap<>();

    public TestServer(String name, Plugin plugin){
        this.name = name;
        this.plugin = plugin;
        status = ServerStatus.OFFLINE;
    }

    @Override
    public Collection<Player> getPlayers() {
        return players.values();
    }

    @Override
    public Player getPlayer(String string) {
        return players.get(string);
    }

    @Override
    public void removePlayer(String player) {
        plugin.getCrossServer().reportPlayerStatus(getPlayer(player), PlayerStatus.LEFT);
        players.remove(player);
    }

    @Override
    public void addPlayer(Player player) {
        players.put(player.getName(), player);
        plugin.getCrossServer().reportPlayerStatus(player, PlayerStatus.JOINED);
    }

    @Override
    public void broadcast(String string) {
        System.out.println("<" + name + " --> GLOBAL>: "  + string);
    }
}
