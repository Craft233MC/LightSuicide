package ink.neokoni.lightSuicide;

import ink.neokoni.lightSuicide.commands.lightsuicide;
import ink.neokoni.lightSuicide.commands.suicide;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
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

    public static void noPermsMsg(CommandSender c){
        c.sendMessage(Component.text("你没有执行此命令的权限！").color(TextColor.fromCSSHexString("#C8F1EF")));
    }
}
