package ink.neokoni.lightSuicide;

import net.kyori.adventure.text.Component;
import org.bukkit.configuration.Configuration;

import java.util.Random;

public class deathMessage {
    Configuration config = LightSuicide.getInstance().getConfig();
    private final String[] deathMessages = config.getStringList("messages").toArray(new String[0]);

    public Component getRandomDeathMessage() {
        int randomNum = new Random().nextInt(deathMessages.length);
        return Component.text(deathMessages[randomNum]);
    }

    public Component getFirstDeathMessage() {
        return Component.text(config.getStringList("messages").get(0));
    }

}
