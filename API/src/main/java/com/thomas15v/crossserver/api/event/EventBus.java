package com.thomas15v.crossserver.api.event;

import com.thomas15v.crossserver.api.event.events.Event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thomas15v on 27/12/14.
 */
public class EventBus {

    private Map<Class<? extends Event>,List<EventMethod>> listeners;

    public EventBus(){
        this.listeners = new HashMap<>();
    }

    public void addListener(Object object) {
        doListener(object, false);
    }

    private void doListener(Object object, boolean remove){
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(EventListener.class) && method.getParameterTypes().length == 1) {
                Class event = method.getParameterTypes()[0];
                if (Event.class.isAssignableFrom(event)) {
                    if (!listeners.containsKey(event))
                        listeners.put(event, new ArrayList<EventMethod>());
                    if (remove)
                        listeners.get(event).remove(new EventMethod(method, object));
                    else
                        listeners.get(event).add(new EventMethod(method, object));
                }
            }
        }
    }

    private void addEvent(Class event){
        Class clazz = event;
        while (clazz != null || !Object.class.equals(clazz)) {
            listeners.put(event, new ArrayList<EventMethod>());
            clazz = clazz.getSuperclass();
        }
    }

    public void removeListener(Object object){
        doListener(object, true);
    }

    public void call(Event event){
        Class clazz = event.getClass();
        while (clazz != null && !Object.class.equals(clazz)) {
            if (listeners.containsKey(clazz))
                for (EventMethod method : listeners.get(clazz))
                    method.call(event);
            clazz = clazz.getSuperclass();
        }
    }
}
