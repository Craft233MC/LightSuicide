package ink.neokoni.lightSuicide.utils;

import ink.neokoni.lightSuicide.LightSuicide;
import ink.neokoni.lightSuicide.coloredText;
import net.kyori.adventure.text.Component;

public class text {
    public static String replace (String str) {
        return str.replace("%version%", LightSuicide.version);
    }

    public static Component getLangLegacy(String str) {
        return coloredText.legacy(replace(configs.getConfig("lang").getString(str)));
    }
}
