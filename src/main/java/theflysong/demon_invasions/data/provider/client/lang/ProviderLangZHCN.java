package theflysong.demon_invasions.data.provider.client.lang;

import net.minecraft.data.DataGenerator;
import theflysong.demon_invasions.block.ModBlocks;
import theflysong.demon_invasions.entity.ModEntities;
import theflysong.demon_invasions.item.ModItems;
import theflysong.demon_invasions.item.ModSpawnEggItem;

public class ProviderLangZHCN extends ProviderLangBase {
    public ProviderLangZHCN(DataGenerator gen, String modid) {
        super(gen, modid, "zh_cn");
    }

    protected void addEntities() {
        add(ModEntities.DEMON_EYE.get(), "恶魔眼");
        add(ModEntities.CTHULHUL_EYE.get(), "克苏鲁之眼");
    }

    protected void addItems() {
        add(ModSpawnEggItem.TRANSLATION_KEY, " %s刷怪蛋");

        add(ModItems.STRANGE_EYE.get(), "奇怪的眼球");
        add(ModItems.LENS.get(), "晶状体");
        add(ModItems.BLOOD_LENS.get(), "血 晶状体");
        add(ModItems.LIFE_CRYSTAL.get(), "生命水晶");
        add(ModItems.WOODEN_BOW.get(), "木 弓");
        add(ModItems.IRON_BOW.get(), "铁 弓");
        add(ModItems.GOLDEN_BOW.get(), "金 弓");
        add(ModItems.DIAMOND_BOW.get(), "钻石 弓");
        add(ModItems.SEA_BOW.get(), "海洋 弓");
    }

    protected void addBlocks() {
        addBlock(ModBlocks.LIFE_CRYSTAL, "生命水晶");
    }

    protected void addAdvancements() {
        addAdvamcement("root", "根源", "所有冒险的根源");
        addAdvamcement("break_heart", "碎心!", "获得生命水晶");
        addAdvamcement("blind", "失明", "杀死克苏鲁之眼");
    }

    @Override
    protected void addTranslations() {
        addEntities();
        addItems();
        addBlocks();
        addAdvancements();
    }
}
