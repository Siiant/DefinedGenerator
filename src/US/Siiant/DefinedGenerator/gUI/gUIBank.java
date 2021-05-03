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

public class gUIBank {
    private static Inventory bankGUI;
    private static ItemStack bank = new ItemStack(Material.PAPER);

    public static Inventory Bank(Player player){
        bankGUI = Bukkit.createInventory(player, 9, ChatColor.translateAlternateColorCodes('&', "&cBank &8Menu"));
        ItemMeta bankMeta = bank.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&o&2Claim your goods."));
        bankMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&o&aBank"));
        bankMeta.setLore(lore);
        bank.setItemMeta(bankMeta);
        return bankGUI;
    }

    public static Inventory menuBank(Player player){
        bankGUI = Bukkit.createInventory(player, 9, ChatColor.translateAlternateColorCodes('&', "&cBank &8Menu"));
        ItemMeta bankMeta = bank.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&o&2Claim your goods."));
        bankMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&o&aBank"));
        bankMeta.setLore(lore);
        bank.setItemMeta(bankMeta);
        return bankGUI;
    }

    public static ItemStack getItemStack(){
        ItemMeta bankMeta = bank.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&o&2Claim your goods."));
        bankMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&o&aBank"));
        bankMeta.setLore(lore);
        bank.setItemMeta(bankMeta);
        return bank;
    }
}
