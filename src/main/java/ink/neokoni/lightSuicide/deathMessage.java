package ink.neokoni.lightSuicide;

import net.kyori.adventure.text.Component;
import org.bukkit.configuration.Configuration;
import ink.neokoni.lightSuicide.color.colorHandlerSelector;
import org.bukkit.entity.Player;

import java.util.Random;

public class deathMessage {
    Configuration config = LightSuicide.getInstance().getConfig();
    private final String[] broadcastDeathMessages = config.getStringList("broadcast-messages").toArray(new String[0]);
    private final String[] sendPlayerDeathMessages = config.getStringList("send-player-messages").toArray(new String[0]);

    public Component getRandomDeathMessage(Player player) {
        int randomNum = new Random().nextInt(broadcastDeathMessages.length);
        String replacedText = internalPlaceholderReplace(broadcastDeathMessages[randomNum], player);
        return colorHandlerSelector.translateColor(replacedText);
    }

    public Component getFirstDeathMessage(Player player) {
        String replacedText = internalPlaceholderReplace(broadcastDeathMessages[0], player);
        return colorHandlerSelector.translateColor(replacedText);
    }

    public Component getRandomSendPlayerDeathMessage(Player player) {
        int randomNum = new Random().nextInt(sendPlayerDeathMessages.length);
        String replacedText = internalPlaceholderReplace(sendPlayerDeathMessages[randomNum], player);
        return colorHandlerSelector.translateColor(replacedText);
    }

    public Component getFirstSendPlayerDeathMessage(Player player) {
        String replacedText = internalPlaceholderReplace(sendPlayerDeathMessages[0], player);
        return colorHandlerSelector.translateColor(replacedText);
    }

    private String internalPlaceholderReplace(String input, Player player){
        // placeholder %player%
        String named = input.replaceAll("%player%", player.getName());

        // placeholder %location%
        String location = "[" +
                player.getLocation().getBlockX() + ", "
                + player.getLocation().getBlockY() + ", "
                + player.getLocation().getBlockZ() + ", "
                + player.getLocation().getWorld().getName()
                + "]";
        String endText = named.replaceAll("%location%", location);

        // return
        return endText;
    }
}
