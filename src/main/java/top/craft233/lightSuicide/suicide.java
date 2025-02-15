package top.craft233.lightSuicide;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class suicide implements CommandExecutor {
    private static Player lastSuicidePlayer;

    public void register() {
        LightSuicide.getInstance().getCommand("suicide").setExecutor(this);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("Not a player!");
            return false;
        }

        Player player = (Player) commandSender;
        deathMessage deathMessage = new deathMessage();
        lastSuicidePlayer = player;

        player.setHealth(0.0);

        if(LightSuicide.getInstance().getConfig().getBoolean("custom-suicide-message")){
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
