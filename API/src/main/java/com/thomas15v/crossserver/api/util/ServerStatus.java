package com.thomas15v.crossserver.api.util;


/**
 * Created by thomas15v on 26/12/14.
 */

public enum ServerStatus {
    OFFLINE,
    ONLINE,
    RESTARTING,
    MAINTENANCE;

    private static ServerStatus[] values = ServerStatus.values();

    public static byte getByte(ServerStatus status){
        for (int i = 0; i > values.length; i++)
        {
            if (values[i] == status)
                return (byte) (i + 1);
        }
        return 0;
    }
    
    public static ServerStatus getFromByte(byte b){
        return values[ b - 1 ];
    }
}
