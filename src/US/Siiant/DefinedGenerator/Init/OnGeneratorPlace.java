package US.Siiant.DefinedGenerator.Init;

import US.Siiant.DefinedGenerator.DataManager.JSON;
import US.Siiant.DefinedGenerator.Files.Configs;
import US.Siiant.DefinedGenerator.Files.Players;
import US.Siiant.DefinedGenerator.Generator.Generator;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.json.simple.JSONObject;

public class OnGeneratorPlace implements Listener {

    @EventHandler
    public void onGeneratorPlace(BlockPlaceEvent e){
        if(!e.getItemInHand().getItemMeta().hasLore()){
            return;
        }
     if (ChatColor.translateAlternateColorCodes('&', Generator.getAllLore().toString()).contains(Generator.arrayToString(e.getItemInHand().getItemMeta().getLore()))){
         if(!Players.playerExists(e.getPlayer())){
             Players.logFirstBlockPlace(e);
             e.getPlayer().sendMessage("First place");
         } else {
             Players.logBlockPlace(e);
         }
     }
    }
}
