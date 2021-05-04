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

import static US.Siiant.DefinedGenerator.Files.Players.*;

public class OnGeneratorBreak implements Listener {
    @EventHandler
    public void onGeneratorBreak(BlockBreakEvent e) {
        if (locationExists(e)) {
            Generator generator = new Generator(getGen(e));
            e.setDropItems(false);
            e.getPlayer().getInventory().addItem(generator.getBlock());
            removeGenerator(e);
        }

    }



}
