package ink.neokoni.lightSuicide.color;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class legacy {
    public static Component translateColor(String text) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(text);
    }
}
