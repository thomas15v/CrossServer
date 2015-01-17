package com.thomas15v.crossserver.config;

import com.typesafe.config.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by thomas15v on 15/01/15.
 */
public class ConfigFile {

    private Config config;
    private File file;

    public ConfigFile(File file, ClassLoader loader){
        this.file = file;
        try {
            if (!file.exists()) {
                file.createNewFile();
                this.config = ConfigFactory.parseResources(loader, file.getName());
                System.out.println(config.entrySet());
                save();
            }
            else
                this.config = ConfigFactory.parseFile(file).withFallback(ConfigFactory.parseResources(this.getClass().getClassLoader(), file.getName()));
            } catch (Exception e){
                this.config = ConfigFactory.empty();
            }


    }

    public void save() {
        try {
            PrintWriter out = new PrintWriter(file);
            out.print(config.root().render(ConfigRenderOptions.defaults().setComments(false).setOriginComments(false)));
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void set(String key, Object value){
        config = config.withValue(key, ConfigValueFactory.fromAnyRef(value));
        save();
    }

    public Config getConfig(){
        return config;
    }
}
