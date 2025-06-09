package ink.neokoni.lightSuicide;

import ink.neokoni.lightSuicide.utils.configs;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.Random;

public class deathMessage {
    public static Component getMsg(Player player, String[] strings, Boolean random){
        int msgLength = strings.length;
        int randomNum = new Random().nextInt(msgLength);

        if (random) {
            return coloredText.fromConfig(
                    internalPlaceholderReplace(strings[randomNum], player));
        } else {
            return coloredText.fromConfig(
                    internalPlaceholderReplace(strings[0], player));
        }
    }

    private static String internalPlaceholderReplace(String input, Player player){
        // format location text from config
        String locText = configs.getConfig("config").getString("location-format");
        String locFormated = locText
                        .replace("%x%", String.valueOf(player.getLocation().getBlockX()))
                        .replace("%y%", String.valueOf(player.getLocation().getBlockY()))
                        .replace("%z%", String.valueOf(player.getLocation().getBlockZ()))
                        .replace("%world%", player.getLocation().getWorld().getName());

        // replace placeholders
        String result = input
                .replace("%player%", player.getName()) // player name
                .replace("%location%", locFormated); // location

        // return
        return result;
    }
}
