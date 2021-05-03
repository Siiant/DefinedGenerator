package US.Siiant.DefinedGenerator.Files;

import US.Siiant.DefinedGenerator.DataManager.Config;
import US.Siiant.DefinedGenerator.DefinedGenerator;


public class DefaultGenerators extends DefinedGenerator {


    private static Config dgConfig;

    public DefaultGenerators(){

    }

    public static void createDefaultConfig(){
        dgConfig = getDataManager().getNewConfig("Generators.yml");
        dgConfig.createSection("Default");
        dgConfig.setListValue("Default","Block", "note_block");
        dgConfig.setListValue("Default", "NameTag", "&b&lDefault&r &c&lGenerator");
        dgConfig.setListValue("Default", "Lore", "&c&lThis generator gives you &a$&e60 &c an hour!");
        dgConfig.setListValue("Default", "Enchanted", false);
        dgConfig.setListValue("Default", "Command", "eco give {player} ");
        dgConfig.setListValue("Default", "Amount", 60);
        dgConfig.setListValue("Default", "Increment", 60);
        dgConfig.saveConfig();
    }

    public static Config getDGConfig(){
        return dgConfig;
    }
}
