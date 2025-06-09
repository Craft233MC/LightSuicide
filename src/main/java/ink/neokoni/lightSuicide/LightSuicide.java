package ink.neokoni.lightSuicide;

import ink.neokoni.lightSuicide.commands.lightsuicide;
import ink.neokoni.lightSuicide.commands.suicide;
import ink.neokoni.lightSuicide.utils.configs;
import ink.neokoni.lightSuicide.utils.text;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class LightSuicide extends JavaPlugin {

    private static LightSuicide instance;
    public static String version = "0.2";

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

    public void initConfig() throws IOException {
        new configs().reloadConfig();

        // check config and fix to default
        new configUpdater().update(configs.getConfig("config"), configs.getConfig("lang"));
    }

    private void regCommand() {
        new suicide().register(this);
        new lightsuicide().register(this);
        new deathHandler().register(this);
    }

    public static LightSuicide getInstance() {
        return instance;
    }

    public static void noPermsMsg(CommandSender c){
        c.sendMessage(text.getLangLegacy("no-permission"));
    }
}
