package ink.neokoni.lightSuicide;

import ink.neokoni.lightSuicide.utils.configs;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public class configUpdater {
    private JavaPlugin instance = LightSuicide.getInstance();
    private Configuration defconfig;
    private Configuration deflang;
    private YamlConfiguration config;
    private YamlConfiguration lang;

    public void update(YamlConfiguration inputconfig, YamlConfiguration inputlang) throws IOException {
        // init default config
        defconfig = instance.getConfig().getDefaults();
        File tmplang = new File(instance.getDataFolder(), "tmplang.yml");
        instance.getResource("lang.yml").transferTo(new FileOutputStream(tmplang));
        deflang = YamlConfiguration.loadConfiguration(tmplang);

        config = inputconfig;
        lang = inputlang;

        // config
        setConfig("use-custom-messages");
        setConfig("broadcast-suicide-message");
        setConfig("send-msg-who-suicided");
        setConfig("random-suicide-broadcast-message");
        setConfig("random-send-player-message");
        setConfig("formatter");
        setConfig("location-format");
        setConfigComments("broadcast-messages");
        setConfigComments("send-player-messages");
        new configs().saveConfig("config", config);

        // langConfig
        setLang("no-permission");
        setLang("not-player");
        setLang("reload-success");
        setLang("suicide-cmd-help");
        setLang("about-cmd-help");
        setLang("help-cmd-help");
        setLang("reload-cmd-help");
        setLang("description");
        setLang("running");
        setLang("links");
        try {
            new configs().saveConfig("lang", lang);
        } catch (Exception e) {
            Bukkit.getLogger().warning("Failed to save lang.yml");
        };
        tmplang.delete();
    }

    private void setConfig(String path){
        if (config.get(path) == null || !config.isSet(path)){
            config.set(path, defconfig.get(path));
        }
    }

    private void setConfigComments(String path){
        if (config.get(path) == null || !config.isSet(path)){
            config.set(path, defconfig.getComments(path));
        }
    }

    private void setLang(String path){
        if (lang.get(path) == null || !lang.isSet(path)){
            lang.set(path, deflang.get(path));
        }
    }
}
