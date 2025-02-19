package ink.neokoni.lightSuicide.commands;

import ink.neokoni.lightSuicide.LightSuicide;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class lightsuicide implements CommandExecutor {
    public void register(JavaPlugin plugin) {
        plugin.getCommand("lightsuicide").setExecutor(this);
        TabCompleter tab = new TabCompleter() {
            @Override
            public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
                if (strings.length <= 1) {
                    return List.of(new String[]{"reload", "help", "about"});
                }
                return List.of(new String[]{});
            }
        };
        plugin.getCommand("lightsuicide").setTabCompleter(tab);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings[0].equals("reload")){
            reload();
            return true;
        }

        if (strings[0].equals("help")){
            help();
            return true;
        }

        if (strings[0].equals("about")){
            about();
            return true;
        }

        help();
        return false;
    }

    private void reload() {
        LightSuicide.getInstance().reloadConfig();
    }

    private void help(){

    }

    private void about(){

    }
}
