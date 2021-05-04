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
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Players {
    private static File players;

    public static void createPlayersJson(Plugin pl) {
        players = new File(pl.getDataFolder(), "Players.json");
        JSON jsonPlayers = new JSON(players);
        JSONObject jso = new JSONObject();
        JSONObject plist = new JSONObject();
        jso.put("Players", plist);
        jsonPlayers.save(jso);
    }

    public static void logFirstBlockPlace(BlockPlaceEvent e) {
        try {
            File pJson = Configs.getPlayersJSON();
            JSON json = new JSON(pJson);
            Player player = e.getPlayer();
            Location loc = e.getBlockPlaced().getLocation();
            JSONParser jsonParser = new JSONParser();
            Object object = jsonParser.parse(new FileReader(pJson));
            JSONObject jsonObject = (JSONObject) object;
            JSONObject players = (JSONObject) jsonObject.get("Players");
            JSONObject generators = new JSONObject();
            JSONObject gData = new JSONObject();
            gData.put("World", loc.getWorld().getName()); // Log the world the block was placed
            gData.put("Generator", Generator.itemStackToGenerator(e.getItemInHand())); // Log the generator that was placed
            gData.put("Level", Generator.getLevel(e.getItemInHand())); // Get the level of the generator
            gData.put("Location", (int) loc.getX() + "," + loc.getBlockY() + "," + loc.getBlockZ()); // Get the location of the generator
            generators.put("0", gData);
            players.put(player.getUniqueId().toString(), generators);
            jsonObject.put("Players", players);
            json.save(jsonObject);
        } catch (Exception exc) {
            return;
        }
    }

    public static void logBlockPlace(BlockPlaceEvent e) {
        try {
            File pJson = Configs.getPlayersJSON();
            JSON json = new JSON(pJson);
            Player player = e.getPlayer();
            Location loc = e.getBlockPlaced().getLocation();
            JSONParser jsonParser = new JSONParser();
            Object object = jsonParser.parse(new FileReader(pJson));
            JSONObject jsonObject = (JSONObject) object;
            JSONObject players = (JSONObject) jsonObject.get("Players");
            JSONObject generators = (JSONObject) players.get(player.getUniqueId().toString());
            JSONObject gData = new JSONObject();
            gData.put("World", loc.getWorld().getName()); // Log the world the block was placed
            gData.put("Generator", Generator.itemStackToGenerator(e.getItemInHand())); // Log the generator that was placed
            gData.put("Level", Generator.getLevel(e.getItemInHand())); // Get the level of the generator
            gData.put("Location", (int) loc.getX() + "," + loc.getBlockY() + "," + loc.getBlockZ()); // Get the location of the generator
            generators.put(generators.size(), gData);
            players.put(player.getUniqueId().toString(), generators);
            jsonObject.put("Players", players);
            json.save(jsonObject);
        } catch (Exception exc) {
            return;
        }
    }


    public static boolean playerExists(Player player) {
        File pJson = Configs.getPlayersJSON(); // Load Players JSON file
        JSON json = new JSON(pJson);// Turn JSON file into readable JSON
        JSONObject obj = json.getObject("Players");
        if (obj.toString().contains(player.getUniqueId().toString())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean locationExists(BlockBreakEvent e) {
        try {
            File pJson = Configs.getPlayersJSON(); // Load Players JSON file
            JSONParser jsonParser = new JSONParser();
            Object object = jsonParser.parse(new FileReader(pJson));
            JSONObject jsonObject = (JSONObject) object;
            JSONObject players = (JSONObject) jsonObject.get("Players");// Get a list of the players UUID
            String location = e.getBlock().getLocation().getBlockX() + "," + e.getBlock().getLocation().getBlockY() + "," + e.getBlock().getLocation().getBlockZ();// Get block placed location
            Iterator keys = players.keySet().iterator();//Create iterator to loop through each player
            while (keys.hasNext()) {// For each player
                JSONObject pConfig = (JSONObject) players.get(keys.next().toString());// Get a players generator section
                for (int c = 0; pConfig.size() > c; c++) {// For each generator the player has in their section
                    JSONObject gen = (JSONObject) pConfig.get(c + "");// Get the generator
                    if (gen.get("Location").toString().contains(location)) {// Check to see if the location matches up
                        return true;// return true
                    }
                }
            }
            return false;
        } catch (Exception exc) {
            return false;
        }
    }

    public static String getGen(BlockBreakEvent e) {
        try {
            File pJson = Configs.getPlayersJSON(); // Load Players JSON file
            JSONParser jsonParser = new JSONParser();
            Object object = jsonParser.parse(new FileReader(pJson));
            JSONObject jsonObject = (JSONObject) object;
            JSONObject players = (JSONObject) jsonObject.get("Players");// Get a list of the players UUID
            String location = e.getBlock().getLocation().getBlockX() + "," + e.getBlock().getLocation().getBlockY() + "," + e.getBlock().getLocation().getBlockZ();// Get block placed location
            Iterator keys = players.keySet().iterator();//Create iterator to loop through each player
            while (keys.hasNext()) {// For each player
                JSONObject pConfig = (JSONObject) players.get(keys.next().toString());// Get a players generator section
                for (int c = 0; pConfig.size() > c; c++) {// For each generator the player has in their section
                    JSONObject gen = (JSONObject) pConfig.get(c + "");// Get the generator
                    if (gen.get("Location").toString().contains(location)) {// Check to see if the location matches up
                        return gen.get("Generator").toString();// return Generator String
                    }
                }
            }
        } catch (Exception exc) {
            return null;
        }
        return null;
    }

    public static void removeGenerator(BlockBreakEvent e) {
        try {
            File pJson = Configs.getPlayersJSON(); // Load Players JSON file
            JSON json = new JSON(pJson);
            JSONParser jsonParser = new JSONParser();
            Object object = jsonParser.parse(new FileReader(pJson));
            JSONObject jsonObject = (JSONObject) object;
            JSONObject players = (JSONObject) jsonObject.get("Players");// Get a list of the players UUID
            String location = e.getBlock().getLocation().getBlockX() + "," + e.getBlock().getLocation().getBlockY() + "," + e.getBlock().getLocation().getBlockZ();// Get block placed location
            Iterator keys = players.keySet().iterator();//Create iterator to loop through each player
            while (keys.hasNext()) {// For each player
                JSONObject pConfig = (JSONObject) players.get(keys.next().toString());// Get a players generator section
                for (int c = 0; pConfig.size() > c; c++) {// For each generator the player has in their section
                    JSONObject gen = (JSONObject) pConfig.get(c + "");// Get the generator
                    if (gen.get("Location").toString().contains(location)) {// Check to see if the location matches up
                            pConfig.remove(c + "");
                            json.save(jsonObject);
                    }
                }
            }
        } catch (Exception exc) {
            return;
        }
    }
}


