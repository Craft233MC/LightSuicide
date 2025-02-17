package ink.neokoni.lightSuicide.color;

import ink.neokoni.lightSuicide.LightSuicide;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

public class colorHandlerSelector {

    public static Component translateColor(String text) {
        String formatter = LightSuicide.getInstance().getConfig().getString("formatter");
        Component result;
        if (formatter.equals("MINIMESSAGE")){
            result = minimessage.translateColor(text);
            return result;
        };
        if (formatter.equals("LEGACY")){
            result = legacy.translateColor(text);
            return result;
        };

        Bukkit.getLogger().warning("Color formatter not found or error, using default formatter(MINIMESSAGE)");
        result = minimessage.translateColor(text);
        return result;
    }
}
