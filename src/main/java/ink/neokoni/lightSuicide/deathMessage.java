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
        // format location text from config
        String locText = config.getString("location-format");
        String locFormated = locText.replace("%x%", String.valueOf(player.getLocation().getBlockX()))
                        .replace("%y%", String.valueOf(player.getLocation().getBlockY()))
                        .replace("%z%", String.valueOf(player.getLocation().getBlockZ()))
                        .replace("%world%", player.getLocation().getWorld().getName());

        // replace placeholders
        String result = input.replace("%player%", player.getName()) // player name
                .replace("%location", locFormated); // location

        // return
        return result;
    }
}
