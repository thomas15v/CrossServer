package com.thomas15v.crossserver.api.event;

import com.thomas15v.crossserver.api.event.events.Event;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by thomas15v on 16/01/15.
 */
@EqualsAndHashCode
public class EventMethod {

    @NonNull
    private Method method;

    @NonNull
    private Object object;

    public EventMethod(Method method, Object object){
        this.method = method;
        method.setAccessible(true);
        this.object = object;
    }

    public void call(Event event) {
        try {
            method.invoke(object, event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
