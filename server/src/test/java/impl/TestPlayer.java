package impl;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Created by thomas15v on 26/12/14.
 */
@RequiredArgsConstructor
public class TestPlayer implements Player {

    @Getter
    @NonNull
    private String name;
    @Getter
    @Setter
    private Server server;

    @Override
    public UUID getUUID() {
        return null;
    }

    @Override
    public void kick(String kickMessage) {
        server.removePlayer(name);
        System.out.println(name + " has been kicked");
    }

    @Override
    public void ban(String banMessage) {
        System.out.println(name + " has been banned");
    }


    @Override
    public void sendMessage(String message) {
        System.out.println(name + "has recived message: " + message);
    }
}
