package US.Siiant.DefinedGenerator.Init;

import US.Siiant.DefinedGenerator.DataManager.JSON;
import US.Siiant.DefinedGenerator.Files.Configs;
import US.Siiant.DefinedGenerator.Files.Players;
import US.Siiant.DefinedGenerator.Generator.Generator;
import jdk.nashorn.internal.ir.Block;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;

public class OnGeneratorBreak implements Listener {
    @EventHandler
    public void onGeneratorBreak(BlockBreakEvent e) {
        File pJson = Configs.getPlayersJSON();
        JSON json = new JSON(pJson);
        String location = e.getBlock().getLocation().getBlockX() + "," + e.getBlock().getLocation().getBlockY() + "," + e.getBlock().getLocation().getBlockZ();
        if (locationExists(e)) {
            e.getPlayer().sendMessage("test");
            JSONObject player = json.getObject(getUUID(e));
            Generator generator = new Generator(getGen(e));
            e.getBlock().getDrops().clear();
            e.getPlayer().getInventory().addItem(generator.getBlock());
        }

    }

    public static boolean locationExists(BlockBreakEvent e) {
        File pJson = Configs.getPlayersJSON(); // Load Players JSON file
        JSON json = new JSON(pJson);// Turn JSON file into readable JSON
        JSONObject players = json.getObject("Players");// Get a list of the players UUID
        String location = e.getBlock().getLocation().getBlockX() + "," + e.getBlock().getLocation().getBlockY() + "," + e.getBlock().getLocation().getBlockZ();// Get block placed location
        Iterator keys = json.getObject("Players").keySet().iterator();//Create iterator to loop through each player
        while (keys.hasNext()) {// For each player
            JSONObject pConfig = (JSONObject) players.get(keys.next().toString());// Get a players generator section
            for (int c = 0; pConfig.size() > c; c++) {// For each generator the player has in their section
                JSONObject gen = (JSONObject) pConfig.get(c + "");// Get the generator
                if (gen.get("Location").toString() == location) {// Check to see if the location matches up
                    return true;// return true
                }
            }
        }
        return false;
    }


    public static String getGen(BlockBreakEvent e) {
        File pJson = Configs.getPlayersJSON(); // Load Players JSON file
        JSON json = new JSON(pJson);// Turn JSON file into readable JSON
        JSONObject players = json.getObject("Players");// Get a list of the players UUID
        String location = e.getBlock().getLocation().getBlockX() + "," + e.getBlock().getLocation().getBlockY() + "," + e.getBlock().getLocation().getBlockZ();// Get block placed location
        Iterator keys = json.getObject("Players").keySet().iterator();//Create iterator to loop through each player
        while (keys.hasNext()) {// For each player
            JSONObject pConfig = (JSONObject) players.get(keys.next().toString());// Get a players generator section
            for (int c = 0; pConfig.size() > c; c++) {// For each generator the player has in their section
                JSONObject gen = (JSONObject) pConfig.get(c + "");// Get the generator
                if (gen.get("Location").toString() == location) {// Check to see if the location matches up
                    return gen.get("Generator").toString();// return Generator String
                }
            }
        }
        return null;
    }




    public static String getUUID(BlockBreakEvent e) {
        File pJson = Configs.getPlayersJSON(); // Load Players JSON file
        JSON json = new JSON(pJson);// Turn JSON file into readable JSON
        JSONObject players = json.getObject("Players");// Get a list of the players UUID
        String location = e.getBlock().getLocation().getBlockX() + "," + e.getBlock().getLocation().getBlockY() + "," + e.getBlock().getLocation().getBlockZ();// Get block placed location
        Iterator keys = json.getObject("Players").keySet().iterator();//Create iterator to loop through each player
        while (keys.hasNext()) {// For each player
            JSONObject pConfig = (JSONObject) players.get(keys.next().toString());// Get a players generator section
            for (int c = 0; pConfig.size() > c; c++) {// For each generator the player has in their section
                JSONObject gen = (JSONObject) pConfig.get(c + "");// Get the generator
                if (gen.get("Location").toString() == location) {// Check to see if the location matches up
                    return keys.next().toString();// return UUID
                }
            }
        }
        return null;
    }

}
