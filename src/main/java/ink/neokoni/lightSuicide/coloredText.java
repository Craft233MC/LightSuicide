package ink.neokoni.lightSuicide;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;

public class coloredText {

    public static Component fromConfig(String text) {
        String formatter = LightSuicide.getInstance().getConfig().getString("formatter");
        if (formatter.equals("MINIMESSAGE")){
            return miniMessage(text);
        };
        if (formatter.equals("LEGACY")){
            return legacy(text);
        };

        Bukkit.getLogger().warning("Color formatter not found or error, using default formatter(MINIMESSAGE)");
        return miniMessage(text);
    }


    public static Component legacy(String text) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(text);
    }

    public static Component miniMessage(String text) {
        return MiniMessage.miniMessage().deserialize(text);
    }
}
