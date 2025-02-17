package ink.neokoni.lightSuicide;

import net.kyori.adventure.text.Component;
import org.bukkit.configuration.Configuration;
import ink.neokoni.lightSuicide.color.colorHandlerSelector;

import java.util.Random;

public class deathMessage {
    Configuration config = LightSuicide.getInstance().getConfig();
    private final String[] deathMessages = config.getStringList("messages").toArray(new String[0]);

    public Component getRandomDeathMessage() {
        int randomNum = new Random().nextInt(deathMessages.length);
        return colorHandlerSelector.translateColor(deathMessages[randomNum]);
    }

    public Component getFirstDeathMessage() {
        return colorHandlerSelector.translateColor(deathMessages[0]);
    }

}
