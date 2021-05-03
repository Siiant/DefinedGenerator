package US.Siiant.DefinedGenerator.Commands.subcommands;

import US.Siiant.DefinedGenerator.Commands.SubCommand;
import US.Siiant.DefinedGenerator.DefinedGenerator;
import US.Siiant.DefinedGenerator.gUI.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

public class Menu extends SubCommand {

    public static Inventory menu;
    public static Inventory menuShop;
    public static Inventory menuBank;
    public static Inventory menuUpgrades;
    public static Inventory menuGenerators;
    public static Inventory next;
    public static Inventory previous;

    @Override
    public String getName() {
        return "menu";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getSyntax() {
        return null;
    }

    @Override
    public void perform(Player player, String[] args) {
        menu = gUIMenu.Menu(player);
        menuShop = gUIMenu.addBottomMenu(gUIShop.Shop(player), "&aShop Gui");
        menuBank = gUIMenu.addBottomMenu(gUIBank.Bank(player), "&cBank Gui");
        menuGenerators = gUIMenu.addBottomMenu(gUIPlacedGenerators.PlacedGenerators(player), "&4Generators Gui");
        menuUpgrades = gUIMenu.addBottomMenu(gUIUpgrades.Upgrades(player), "&6Upgrades Gui");
        player.openInventory(menu);
    }
}
