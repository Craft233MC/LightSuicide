package ink.neokoni.lightSuicide;

import net.kyori.adventure.text.Component;
import org.bukkit.configuration.Configuration;
import ink.neokoni.lightSuicide.color.colorHandlerSelector;
import org.bukkit.entity.Player;

import java.util.Random;

public class deathMessage {
    Configuration config = LightSuicide.getInstance().getConfig();
    private final String[] deathMessages = config.getStringList("messages").toArray(new String[0]);

    public Component getRandomDeathMessage(Player player) {
        int randomNum = new Random().nextInt(deathMessages.length);
        String replacedText = internalPlaceholderReplace(deathMessages[randomNum], player);
        return colorHandlerSelector.translateColor(replacedText);
    }

    public Component getFirstDeathMessage(Player player) {
        String replacedText = internalPlaceholderReplace(deathMessages[0], player);
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
