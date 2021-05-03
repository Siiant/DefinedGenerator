package US.Siiant.DefinedGenerator.Files;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import US.Siiant.DefinedGenerator.DataManager.JSON;
import US.Siiant.DefinedGenerator.DefinedGenerator;
import US.Siiant.DefinedGenerator.Generator.Generator;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Players {
    private static File players;

    public static void createPlayersJson(Plugin pl){
        players = new File(pl.getDataFolder(), "Players.json");
        JSON jsonPlayers = new JSON(players);
        JSONObject jso = new JSONObject();
        JSONObject plist = new JSONObject();
        jso.put("Players",plist);
        jsonPlayers.save(jso);
    }

    public static void logFirstBlockPlace(BlockPlaceEvent e){
        File pJson = Configs.getPlayersJSON(); // Load the players json
        JSON json = new JSON(pJson); // Load the json into a readable JSON
        Player player = e.getPlayer();//Get the player who placed the block
        Location loc = e.getBlockPlaced().getLocation(); // Get the location of the block placed
        JSONObject gData = new JSONObject();  // Create an empty json object
        JSONObject generator = new JSONObject(); // Create an empty json object
        JSONObject players = json.getObject("Players");
        JSONObject test = new JSONObject();
        gData.put("World", loc.getWorld().getName()); // Log the world the block was placed
        gData.put("Generator", Generator.itemStackToGenerator(e.getItemInHand())); // Log the generator that was placed
        gData.put("Level", Generator.getLevel(e.getItemInHand())); // Get the level of the generator
        gData.put("Location", loc.getX() + "," + loc.getBlockY() + "," + loc.getBlockZ()); // Get the location of the generator
        generator.put("0",gData); // This is the first generator, so we will set the count to 0 to count as a computer
        players.put(player.getUniqueId().toString(), generator); // Log the player and the generator array
        test.put("Players", players);
        json.save(test);//Save the user to the json file
    }

    public static void logBlockPlace(BlockPlaceEvent e){
        File pJson = Configs.getPlayersJSON(); // Load the players json
        JSON json = new JSON(pJson); // Load the json into a readable JSON
        Player player = e.getPlayer();//Get the player who placed the block
        Location loc = e.getBlockPlaced().getLocation(); // Get the location of the block placed
        JSONObject gData = new JSONObject();  // Create an empty json object
        JSONObject players = json.getObject("Players");
        JSONObject generators = (JSONObject) players.get(player.getUniqueId().toString());
        gData.put("World", loc.getWorld().getName()); // Log the world the block was placed
        gData.put("Generator", Generator.itemStackToGenerator(e.getItemInHand())); // Log the generator that was placed
        gData.put("Level", Generator.getLevel(e.getItemInHand())); // Get the level of the generator
        gData.put("Location", loc.getX() + "," + loc.getBlockY() + "," + loc.getBlockZ()); // Get the location of the generator
        generators.put(generators.size() - 1,gData); // This is the first generator, so we will set the count to 0 to count as a computer
        players.put(player.getUniqueId().toString(), generators); // Log the player and the generator array
        json.save(players);//Save the user to the json file
    }




    public static boolean playerExists(Player player) {
       /* try {
            File pJson = Configs.getPlayersJSON(); // Load Players JSON file
            JSON json = new JSON(pJson);// Turn JSON file into readable JSON
            //Create iterator to loop through each player
            for (Object o : json.getObject("Players").keySet()) {// For each player
                if (o.toString().contains(player.getUniqueId().toString())) {// Check to see if the location matches up
                    return true;
                }
            }
            return false;
        } catch (Exception e){
            return false;
        }*/
            File pJson = Configs.getPlayersJSON(); // Load Players JSON file
            JSON json = new JSON(pJson);// Turn JSON file into readable JSON
            JSONObject obj = json.getObject("Players");
            if(obj.toString().contains(player.getUniqueId().toString())){
                return true;
            } else {
                return false;
            }
    }
}


