package US.Siiant.DefinedGenerator.Generator;

import US.Siiant.DefinedGenerator.Files.Configs;
import US.Siiant.DefinedGenerator.DataManager.Config;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Generator {

    private String generator;
    private ItemStack block;
    private ItemMeta generatorMeta;
    private List<String> lore = new ArrayList<>();
    private Boolean enchanted;
    private String command;
    private int increment;


    public Generator(String generator, String block, String nameTag, String lore, Boolean enchanted, String command, int increment){
        this.generator = generator;
        this.block = new ItemStack(Material.valueOf(block.toUpperCase()));
        this.generatorMeta = this.block.getItemMeta();
        this.generatorMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', nameTag));
        this.lore.add(ChatColor.translateAlternateColorCodes('&', lore));
        this.generatorMeta.setLore(this.lore);
        this.enchanted = enchanted;
        if (enchanted = true){
            this.generatorMeta.addEnchant(Enchantment.DURABILITY, 1, false);
            this.generatorMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }
        this.command = command;
        this.increment = increment;
        this.block.setItemMeta(generatorMeta);
    }


    public Generator(String key){
        Config  dgConfig = Configs.getConfig("Generators");
            this.generator = key;
            this.block = new ItemStack(Material.valueOf(dgConfig.getString(key +".Block").toUpperCase()));
            this.generatorMeta = this.block.getItemMeta();
            this.generatorMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', dgConfig.getString(key +".NameTag")));
            String lore = dgConfig.getString(key +".Lore");
            this.lore.add(ChatColor.translateAlternateColorCodes('&', lore));
            this.generatorMeta.setLore(this.lore);
            this.enchanted = dgConfig.getBoolean(key +".Enchanted");
            if (dgConfig.getBoolean(key + ".Enchanted")){
                this.generatorMeta.addEnchant(Enchantment.DURABILITY, 1, false);
                this.generatorMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            }
            this.command = dgConfig.getString(key +".Command");
            this.increment = dgConfig.getInt(key +".Increment");
            this.block.setItemMeta(generatorMeta);

    }

    public static ArrayList getAllLore(){
        Set<String> gens = Configs.getConfig("Generators").getKeys();
        ArrayList<String> lore = new ArrayList<>();
        for(int i = 0; gens.size() > i; i++){
            lore.add(Configs.getConfig("Generators").getString(objectToString(gens).get(i) +".Lore"));
        }
       return lore;
    }


    public static ArrayList<String> objectToString(Set<String> generators){
        String gens = generators.toString();
        String s = "";
        ArrayList<String> newGeneratorList = new ArrayList<>(generators.size());
        for(int i = 0; gens.length() > i; i++) {
            if(gens.charAt(i) == ',' || gens.charAt(i) == ']') {
                newGeneratorList.add(s);
                s = "";
            } else if(gens.charAt(i) !='[' && gens.charAt(i) != ' '){
                s = s + gens.charAt(i);
            }
        }
        return newGeneratorList;
    }

    public static String arrayToString(List generators){
        String gens = generators.toString();
        String s = "";
        for(int i = 0; gens.length() > i; i++) {
            if(gens.charAt(i) !='[' && gens.charAt(i) != ']'){
                s = s + gens.charAt(i);
            }
        }
        return s;
    }

    public static String itemStackToGenerator(ItemStack item){
        List<String> lore = item.getItemMeta().getLore();
        Set<String> gens = Configs.getConfig("Generators").getKeys();
        for(int j = 0; gens.size() > j; j++) {
            Generator generator = new Generator(Generator.objectToString(gens).get(j));
            if(generator.getLore().equals(lore)){
                return generator.getGenerator();
            }
        }
        return null; // This shouldn't be returned. If so I fucked up.
    }

    public String getGenerator() {
        return generator;
    }

    public ItemStack getBlock(){
        return this.block;
    }

    public ItemMeta getGeneratorMeta(){
        return this.generatorMeta;
    }

    public List<String> getLore(){
        return this.lore;
    }

    public boolean getEnchanted(){
        return this.enchanted;
    }

    public String getCommand(){
        return this.command;
    }

    public int getIncrement(){
        return this.increment;
    }

    public static ItemMeta addLevel(ItemStack generator){
        ItemMeta newMeta = generator.getItemMeta();
        int count = newMeta.getDisplayName().charAt(newMeta.getDisplayName().length() -1);
        newMeta.setDisplayName(newMeta.getDisplayName() + " LV: " + count + 1);
        return newMeta;
    }

    public static int getLevel(ItemStack generator){
        try {
            String s = generator.getItemMeta().getDisplayName().toString();
            String count = "";
            count += s.charAt(s.length() - 1);
            return Integer.parseInt(count);
        } catch (NumberFormatException e){
            return 1;
        }
    }

}
