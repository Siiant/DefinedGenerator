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

public class gUIUpgrades {
    private static Inventory upgradesGUI;
    private static ItemStack upgrade = new ItemStack(Material.NETHER_STAR);

    public static Inventory Upgrades(Player player){
        upgradesGUI = Bukkit.createInventory(player, 9, ChatColor.translateAlternateColorCodes('&', "&6Upgrade &8Menu"));

        return upgradesGUI;
    }

    public static Inventory menuUpgrades(Player player){
        upgradesGUI = Bukkit.createInventory(player, 9, ChatColor.translateAlternateColorCodes('&', "&6Upgrade &8Menu"));

        return upgradesGUI;
    }

    public static ItemStack getItemStack(){
        ItemMeta upgradeMeta = upgrade.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&o&6Open the upgrade menu."));
        upgradeMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&n&eUpgrade&4 &n&eGenerators"));
        upgradeMeta.setLore(lore);
        upgrade.setItemMeta(upgradeMeta);
        return upgrade;
    }
}
