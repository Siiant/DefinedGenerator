package US.Siiant.DefinedGenerator.gUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class gUIPlacedGenerators {

    private static Inventory pGeneratorsGUI;
    public static ItemStack generators = new ItemStack(Material.NOTE_BLOCK);

    public static Inventory PlacedGenerators(Player player){
        pGeneratorsGUI = Bukkit.createInventory(player, 9, ChatColor.translateAlternateColorCodes('&', "&bPlaced &bGenerators"));
        return pGeneratorsGUI;
    }

    public static Inventory menuPlacedGenerators(Player player){
        pGeneratorsGUI = Bukkit.createInventory(player, 9, ChatColor.translateAlternateColorCodes('&', "&bPlaced &bGenerators"));
        return pGeneratorsGUI;
    }

    public static ItemStack getItemStack(){
        ItemMeta generatorsMeta = generators.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&o&5View your placed generators."));
        generatorsMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&n&dPlaced&4 &n&dGenerators"));
        generatorsMeta.setLore(lore);
        generators.setItemMeta(generatorsMeta);
        return generators;
    }
}
