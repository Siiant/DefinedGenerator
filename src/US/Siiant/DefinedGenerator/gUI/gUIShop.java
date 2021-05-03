package US.Siiant.DefinedGenerator.gUI;

import US.Siiant.DefinedGenerator.Files.Configs;
import US.Siiant.DefinedGenerator.Generator.Generator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class gUIShop {
    private static Inventory shopGUI;
    private static ItemStack shop = new ItemStack(Material.SUNFLOWER);

    public static Inventory Shop(Player player){
        Set<String> gens = Configs.getConfig("Generators").getKeys();
        shopGUI = Bukkit.createInventory(player, gUINumber(gens), ChatColor.translateAlternateColorCodes('&', "&4Shop &4Menu"));
        for(int i = 0; gens.size() > i; i++) {
            Generator generator = new Generator(Generator.objectToString(gens).get(i));
            shopGUI.addItem(generator.getBlock());
        }
        return shopGUI;
    }

    public static Inventory menuShop(Player player){
        Set<String> gens = Configs.getConfig("Generators").getKeys();
        shopGUI = Bukkit.createInventory(player, gUINumber(gens), ChatColor.translateAlternateColorCodes('&', "&cGenerator &4Menu"));
        for(int i = 0; gens.size() > i; i++) {
            Generator generator = new Generator(Generator.objectToString(gens).get(i));
            shopGUI.addItem(generator.getBlock());
        }
        return shopGUI;
    }


    private static int gUINumber(Set<String> gens){
        if(gens.size() <= 9){
            return 9;
        } else if (gens.size() <= 18){
            return 18;
        } else if (gens.size() <= 27) {
            return 27;
        } else if (gens.size() <= 36){
            return 36;
        } else if (gens.size() <= 45){
            return 45;
        }
        return 9;
    }

    public static ItemStack getItemStack(){
        ItemMeta shopMeta = shop.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&o&eOpen the shop menu."));
        shopMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&n&6Purchase&4 &n&6Generators"));
        shopMeta.setLore(lore);
        shop.setItemMeta(shopMeta);
        return shop;
    }
}
