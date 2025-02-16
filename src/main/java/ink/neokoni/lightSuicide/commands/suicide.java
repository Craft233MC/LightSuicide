package ink.neokoni.lightSuicide.commands;

import ink.neokoni.lightSuicide.LightSuicide;
import ink.neokoni.lightSuicide.deathMessage;
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
            commandSender.sendMessage("Not a player!");
            return true;
        }

        Player player = (Player) commandSender;
        deathMessage deathMessage = new deathMessage();
        lastSuicidePlayer = player;

        player.setHealth(0.0);

        if(instance.getConfig().getBoolean("custom-suicide-message")){
            Bukkit.broadcast(deathMessage.getRandomDeathMessage());
        } else {
            player.sendMessage(deathMessage.getFirstDeathMessage());
        }
        return true;
    }

    public Player getLastSuicidePlayer() {
        return lastSuicidePlayer;
    }
}
