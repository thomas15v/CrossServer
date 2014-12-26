package impl;

import com.thomas15v.crossserver.api.remote.Player;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.api.util.ServerStatus;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas15v on 26/12/14.
 */
@RequiredArgsConstructor
public class TestServer implements Server {

    @Getter
    @NonNull
    private String name;
    @Getter
    @NonNull
    private ServerStatus status;
    @Getter
    @NonNull
    private List<Player> players = new ArrayList<>();

    @Override
    public void broadcast(String string) {
        System.out.println("<" + name + " --> GLOBAL>: "  + string);
    }
}
