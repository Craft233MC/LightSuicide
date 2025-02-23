package ink.neokoni.lightSuicide;

import ink.neokoni.lightSuicide.commands.lightsuicide;
import ink.neokoni.lightSuicide.commands.suicide;
import ink.neokoni.lightSuicide.color.legacy;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class LightSuicide extends JavaPlugin {

    private static LightSuicide instance;
    private static YamlConfiguration lang;
    public static String version = "0.1";

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        try {
            initConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        regCommand();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void initConfig() throws IOException {
        saveDefaultConfig();
        if (!new File(getDataFolder(), "lang.yml").exists()){
            saveResource("lang.yml", false);
        }

        YamlConfiguration config = (YamlConfiguration) getConfig();
        lang = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "lang.yml"));

        // check config and fix to default
        new configUpdater().update(config, lang);
    }

    private void regCommand() {
        new suicide().register(this);
        new lightsuicide().register(this);
        new deathHandler().register(this);
    }

    public static LightSuicide getInstance() {
        return instance;
    }

    public void reload() throws IOException {
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
