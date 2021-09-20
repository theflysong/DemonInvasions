package theflysong.demon_invasions.data.provider.client.lang;

import net.minecraft.data.DataGenerator;
import theflysong.demon_invasions.block.ModBlocks;
import theflysong.demon_invasions.entity.ModEntities;
import theflysong.demon_invasions.item.ModItems;
import theflysong.demon_invasions.item.ModSpawnEggItem;

public class ProviderLangENUS extends ProviderLangBase {
    public ProviderLangENUS(DataGenerator gen, String modid) {
        super(gen, modid, "en_us");
    }

    protected void addEntities() {
        add(ModEntities.DEMON_EYE.get(), "Demon Eye");
        add(ModEntities.CTHULHUL_EYE.get(), "Cthulhul Eye");
    }

    protected void addItems() {
        add(ModSpawnEggItem.TRANSLATION_KEY, "%s Spawn Egg");

        add(ModItems.STRANGE_EYE.get(), "Strange Eye");
        add(ModItems.LENS.get(), "Lens");
        add(ModItems.BLOOD_LENS.get(), "Blood   Lens");
        add(ModItems.LIFE_CRYSTAL.get(), "Life Crystal");
        add(ModItems.WOODEN_BOW.get(), "Wooden   Bow");
        add(ModItems.IRON_BOW.get(), "Iron   Bow");
        add(ModItems.GOLDEN_BOW.get(), "Golden   Bow");
        add(ModItems.DIAMOND_BOW.get(), "Diamond   Bow");
        add(ModItems.SEA_BOW.get(), "Sea   Bow");
    }

    protected void addBlocks() {
        addBlock(ModBlocks.LIFE_CRYSTAL, "Life Crystal");
    }

    protected void addAdvancements() {
        addAdvamcement("root", "Root Of All", "The root of all adventures");
        addAdvamcement("break_heart", "Breaking Heart!", "Got the life crystal");
        addAdvamcement("blind", "Blind", "Kill the Cthulhul Eye");
    }

    @Override
    protected void addTranslations() {
        addEntities();
        addItems();
        addBlocks();
        addAdvancements();
    }
}
