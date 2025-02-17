package ink.neokoni.lightSuicide.color;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class minimessage {
    public static Component translateColor(String text) {
        return MiniMessage.miniMessage().deserialize(text);
    }
}
