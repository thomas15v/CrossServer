package com.thomas15v.crossserver.config;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ConfigFileTest {

    @Test
    public void testConfig() throws IOException {
        File file = new File("test.conf");
        System.out.println(file.getAbsolutePath());

        ConfigFile configFile = new ConfigFile(file, this.getClass().getClassLoader());
        configFile.set("Hey", "lol");
    }

}