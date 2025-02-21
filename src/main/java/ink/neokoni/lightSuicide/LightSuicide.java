package ink.neokoni.lightSuicide;

import ink.neokoni.lightSuicide.commands.lightsuicide;
import ink.neokoni.lightSuicide.commands.suicide;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class LightSuicide extends JavaPlugin {

    private static LightSuicide instance;

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        initConfig();
        regCommand();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void initConfig() {
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        // 基础配置初始化
        if (config.get("custom-suicide-message") == null || !config.isSet("custom-suicide-message")){
            config.set("custom-suicide-message", true);
        }
        if (config.get("formatter") == null || !config.isSet("formatter")){
            config.set("formatter", "MINIMESSAGE");
        }
    }

    private void regCommand() {
        new suicide().register(this);
        new lightsuicide().register(this);
        new deathHandler().register(this);
    }

    public static LightSuicide getInstance() {
        return instance;
    }

    public void reload() {
        initConfig();
        reloadConfig();
    }
}
