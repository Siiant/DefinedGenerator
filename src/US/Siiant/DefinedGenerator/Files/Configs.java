package US.Siiant.DefinedGenerator.Files;

import US.Siiant.DefinedGenerator.DataManager.JSON;
import US.Siiant.DefinedGenerator.DefinedGenerator;
import US.Siiant.DefinedGenerator.DataManager.Config;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class Configs {

    private static Config dgConfig;
    private static JSON playersJSON;

    public static void loadAll(Plugin pl){
        dgConfig = DefinedGenerator.getDataManager().getNewConfig("Generators.yml");
        File players = new File(pl.getDataFolder(), "Players.json");
        playersJSON = new JSON(players);

    }

    public static Config getConfig(String file){
        return DefinedGenerator.getDataManager().getNewConfig(file +".yml");
    }

    public static File getPlayersJSON(){return DefinedGenerator.getDataManager().getConfigFile("Players.json");}

}
