package com.thomas15v.crossserver.api.event;

import com.thomas15v.crossserver.api.event.events.Event;
import com.thomas15v.crossserver.api.event.events.server.ServerCrashEvent;
import com.thomas15v.crossserver.api.event.events.server.ServerEvent;
import org.junit.Test;

public class EventManagerTest {

    @Test
    public void testEvent(){
        EventBus eventManager = new EventBus();

        Object listener = new Object() {
            @EventListener
            public void hey(Event event) {
                System.out.println("We recieved an events");
            }

            @EventListener
            public void onServerShutdown(ServerCrashEvent event){
                System.out.println("Server Cras");
            }

            @EventListener
            public void onServerShutdown(ServerEvent event){
                System.out.println("Server Event");
            }
        };

        eventManager.addListener(listener);
        //eventManager.call(new ServerCrashEvent());
        eventManager.removeListener(listener);
        //eventManager.call(new ServerShutDownEvent());

    }

}