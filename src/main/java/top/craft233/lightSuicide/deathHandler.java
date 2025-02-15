package top.craft233.lightSuicide;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class deathHandler implements Listener {
    public void register(JavaPlugin plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player Listenedplayer = event.getPlayer();
        Player lastSuicidePlayer = new suicide().getLastSuicidePlayer();
        if(lastSuicidePlayer != null && lastSuicidePlayer.equals(Listenedplayer)){
            event.deathMessage(null);
        }
    }
}
