package ink.neokoni.lightSuicide.commands;

import ink.neokoni.lightSuicide.LightSuicide;
import ink.neokoni.lightSuicide.deathMessage;
import ink.neokoni.lightSuicide.utils.configs;
import ink.neokoni.lightSuicide.utils.text;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class suicide implements CommandExecutor {
    private static Player lastSuicidePlayer;
    private static JavaPlugin instance;

    public void register(JavaPlugin plugin) {
        plugin.getCommand("suicide").setExecutor(this);
        instance =  plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage(text.getLangLegacy("not-player"));
            return true;
        }

        Player player = (Player) commandSender;
        configs.getConfig("config").getStringList("broadcast-messages").toArray(new String[0]);

        String[] broadcastDeathMsg = configs.getConfig("config").getStringList("broadcast-messages").toArray(new String[0]);
        String[] sendPlayerDeathMsg = configs.getConfig("config").getStringList("send-player-messages").toArray(new String[0]);

        if (!commandSender.hasPermission("lightsuicide.suicide")){
            LightSuicide.noPermsMsg(player);
            return true;
        }

        lastSuicidePlayer = player;

        player.setHealth(0.0);

        // broadcast msg
        if (configs.getConfig("config").getBoolean("use-custom-messages")){
            if(configs.getConfig("config").getBoolean("random-suicide-broadcast-message")){
                Bukkit.broadcast(deathMessage.getMsg(player, broadcastDeathMsg, true));
            } else {
                Bukkit.broadcast(deathMessage.getMsg(player, broadcastDeathMsg, false));
            }
        }

        // send player msg
        if (configs.getConfig("config").getBoolean("send-msg-who-suicided")){
            if(configs.getConfig("config").getBoolean("random-send-player-message")){
                player.sendMessage(deathMessage.getMsg(player, sendPlayerDeathMsg, false));
            } else {
                player.sendMessage(deathMessage.getMsg(player, sendPlayerDeathMsg, false));
            }
        }

        return true;
    }

    public Player getLastSuicidePlayer() {
        return lastSuicidePlayer;
    }

    public void clearLastSuicidePlayer() {
        lastSuicidePlayer = null;
    }
}
