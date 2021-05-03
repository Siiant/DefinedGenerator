package US.Siiant.DefinedGenerator;

import US.Siiant.DefinedGenerator.Commands.CommandManager;
import US.Siiant.DefinedGenerator.DataManager.JSON;
import US.Siiant.DefinedGenerator.Files.Configs;
import US.Siiant.DefinedGenerator.Files.DefaultGenerators;
import US.Siiant.DefinedGenerator.DataManager.dataManager;
import US.Siiant.DefinedGenerator.Files.Players;
import US.Siiant.DefinedGenerator.Init.OnGeneratorBreak;
import US.Siiant.DefinedGenerator.Init.OnGeneratorPlace;
import US.Siiant.DefinedGenerator.Init.OnInventoryClick;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class DefinedGenerator extends JavaPlugin {
    public static dataManager manager;
    private static JSON playersJSON;


    @Override
    public void onEnable(){
        manager = new dataManager(this);
        if(!getDataFolder().exists()){
            DefaultGenerators.createDefaultConfig();
            Players.createPlayersJson(this);
            //Create config stuff
            //Create messages stuff
        }
        getCommand("DefinedGenerator").setExecutor(new CommandManager());
        getServer().getPluginManager().registerEvents(new OnGeneratorPlace(), this);
        getServer().getPluginManager().registerEvents(new OnInventoryClick(), this);
        getServer().getPluginManager().registerEvents(new OnGeneratorBreak(), this);
    }
    @Override
    public void onDisable(){
        //Save config stuff
        //Save messages stuff
        //Save placed generators
        //Save configured generators
    }


    public static dataManager getDataManager(){return manager;}

}
