package com.thomas15v.crossserver.api.event.events.payload;

import com.thomas15v.crossserver.api.PayLoad;
import com.thomas15v.crossserver.api.event.events.Event;
import lombok.Data;
import lombok.NonNull;

/**
 * Created by thomas15v on 16/01/15.
 */
@Data
public class PayloadRecievedEvent implements Event {

    @NonNull
    private PayLoad payLoad;

    @NonNull
    private String service;

}
