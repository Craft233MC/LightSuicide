package ink.neokoni.lightSuicide;

import ink.neokoni.lightSuicide.commands.lightsuicide;
import ink.neokoni.lightSuicide.commands.suicide;
import ink.neokoni.lightSuicide.color.legacy;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class LightSuicide extends JavaPlugin {

    private static LightSuicide instance;
    private static FileConfiguration lang;
    public static String version = "0.1";

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
        if (!new File(getDataFolder(), "lang.yml").exists()){
            saveResource("lang.yml", false);
        }

        FileConfiguration config = getConfig();
        lang = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "lang.yml"));

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

    public static void noPermsMsg(CommandSender c){
        c.sendMessage(getMsgFromLang("no-permission"));
    }

    public static Component getMsgFromLang(String path){
        return legacy.translateColor(lang.getString(path));
    }
    public static Component getMsgFromLangV(String path){
        String text = lang.getString(path).replace("%version%", version);
        return legacy.translateColor(text);
    }
}
