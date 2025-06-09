package ink.neokoni.lightSuicide.utils;

import ink.neokoni.lightSuicide.LightSuicide;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class configs {
    private static YamlConfiguration config;
    private static YamlConfiguration lang;

    public boolean isFileExist(String fileName){
        return new File(LightSuicide.getInstance().getDataFolder(),  fileName+".yml").exists();
    }

    public void createFile(String fileName) {
        LightSuicide.getInstance().saveResource(fileName+".yml", false);
    }


    public static YamlConfiguration getConfig(String str) {
        return switch (str) {
            case "config" -> config;
            case "lang" -> lang;
            default -> null;
        };
    }

    public void reloadConfig() {
        config = loadConfig("config");
        lang = loadConfig("lang");
    }

    public YamlConfiguration loadConfig(String fileName) {
        if(!isFileExist(fileName)){
            createFile(fileName);
        }
        return YamlConfiguration.loadConfiguration(new File(LightSuicide.getInstance().getDataFolder(), fileName+".yml"));
    }

    public boolean saveConfig(String fileName, YamlConfiguration config){
        try {
            config.save(new File(LightSuicide.getInstance().getDataFolder(), fileName+".yml"));

            if (fileName.equals("config")) {
                configs.config = config;
            } else if (fileName.equals("lang")) {
                lang = config;
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
