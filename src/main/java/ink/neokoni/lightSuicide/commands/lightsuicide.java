package ink.neokoni.lightSuicide.commands;

import ink.neokoni.lightSuicide.LightSuicide;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.List;

public class lightsuicide implements CommandExecutor {
    public void register(JavaPlugin plugin) {
        plugin.getCommand("lightsuicide").setExecutor(this);
        TabCompleter tab = new TabCompleter() {
            @Override
            public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
                if (strings.length == 1) {
                    return List.of(new String[]{"reload", "help", "about"});
                }
                return List.of(new String[]{""});
            }
        };
        plugin.getCommand("lightsuicide").setTabCompleter(tab);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length == 0){
            if (!commandSender.hasPermission("lightsuicide.help")){
                LightSuicide.noPermsMsg(commandSender);
                return true;
            }
            help(commandSender);
            return true;
        }

        if (strings[0].equals("reload")){
            if (!commandSender.hasPermission("lightsuicide.reload")){
                LightSuicide.noPermsMsg(commandSender);
                return true;
            }
            try {
                reload(commandSender);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }

        if (strings[0].equals("help")){
            if (!commandSender.hasPermission("lightsuicide.help")){
                LightSuicide.noPermsMsg(commandSender);
                return true;
            }
            help(commandSender);
            return true;
        }

        if (strings[0].equals("about")){
            if (!commandSender.hasPermission("lightsuicide.about")){
                LightSuicide.noPermsMsg(commandSender);
                return true;
            }
            about(commandSender);
            return true;
        }
        return true;
    }

    private void reload(CommandSender commandSender) throws IOException {
        LightSuicide.getInstance().reload();

        commandSender.sendMessage(LightSuicide.getMsgFromLang("reload-success"));
    }

    private void help(CommandSender c){
        c.sendMessage(LightSuicide.getMsgFromLangV("running"));
        c.sendMessage(LightSuicide.getMsgFromLang("suicide-cmd-help"));
        c.sendMessage(Component.text(""));
        c.sendMessage(Component.text("--------------------").color(TextColor.fromCSSHexString("#C8F1EF")));
        c.sendMessage(LightSuicide.getMsgFromLang("about-cmd-help"));
        c.sendMessage(LightSuicide.getMsgFromLang("reload-cmd-help"));
        c.sendMessage(LightSuicide.getMsgFromLang("help-cmd-help"));
    }

    private void about(CommandSender c){
        c.sendMessage(LightSuicide.getMsgFromLangV("running"));
        c.sendMessage(Component.text(""));
        c.sendMessage(LightSuicide.getMsgFromLang("description"));
        c.sendMessage(Component.text(""));
        c.sendMessage(
                LightSuicide.getMsgFromLang("links")
                        .append(Component.text("[GitHub]").clickEvent(ClickEvent.openUrl("https://github.com/Craft233MC/LightSuicide"))
                        .color(TextColor.fromCSSHexString("#C8F1EF")))
        );

    }
}
