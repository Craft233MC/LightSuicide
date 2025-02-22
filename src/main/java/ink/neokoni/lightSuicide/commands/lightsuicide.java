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
            Bukkit.getLogger().info(String.valueOf(!commandSender.hasPermission("lightsuicide.reload")));
            if (!commandSender.hasPermission("lightsuicide.reload")){
                LightSuicide.noPermsMsg(commandSender);
                return true;
            }
            reload(commandSender);
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

    private void reload(CommandSender commandSender) {
        Player p = (Player) commandSender;

        LightSuicide.getInstance().reload();

        p.sendMessage(Component.text("成功重载配置文件！").color(TextColor.fromCSSHexString("#C8F1EF")));
    }

    private void help(CommandSender c){
        c.sendMessage(Component.text("正在运行LightSuicide v0.1!").color(TextColor.fromCSSHexString("#C8F1EF")));
        c.sendMessage(Component.text("/suicide 执行自杀").color(TextColor.fromCSSHexString("#C8F1EF")));
        c.sendMessage(Component.text(""));
        c.sendMessage(Component.text("--------------------").color(TextColor.fromCSSHexString("#C8F1EF")));
        c.sendMessage(Component.text("/lightsuicide about 显示关于信息").color(TextColor.fromCSSHexString("#C8F1EF")));
        c.sendMessage(Component.text("/lightsuicide reload 重载配置文件").color(TextColor.fromCSSHexString("#C8F1EF")));
        c.sendMessage(Component.text("/lightsuicide help 显示指令帮助").color(TextColor.fromCSSHexString("#C8F1EF")));
        c.sendMessage(Component.text(""));
        c.sendMessage(Component.text("/suicide 执行自杀").color(TextColor.fromCSSHexString("#C8F1EF")));
    }

    private void about(CommandSender c){
        c.sendMessage(Component.text("正在运行LightSuicide v0.1!").color(TextColor.fromCSSHexString("#C8F1EF")));
        c.sendMessage(Component.text(""));
        c.sendMessage(Component.text("一个简单的用于执行Suicide的插件，尝试支持高度自定义的配置。").color(TextColor.fromCSSHexString("#C8F1EF")));
        c.sendMessage(Component.text(""));
        c.sendMessage(
                Component.text("相关链接:  ").color(TextColor.fromCSSHexString("#C8F1EF"))
                        .append(Component.text("[GitHub]").clickEvent(ClickEvent.openUrl("https://github.com/Craft233MC/LightSuicide"))
                        .color(TextColor.fromCSSHexString("#C8F1EF")))
        );

    }
}
