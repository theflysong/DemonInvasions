package theflysong.demon_invasions.data.provider.client.model;

import net.minecraft.data.DataGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import theflysong.demon_invasions.entity.ModEntities;
import theflysong.demon_invasions.item.ModItems;

public class ProviderItemModel extends ItemModelProvider {
    public static final ModelFile.UncheckedModelFile GENERATED =
        new ModelFile.UncheckedModelFile("item/generated");

    public static final ModelFile.UncheckedModelFile TEMPLATE_SPAWN_EGG = new ModelFile.UncheckedModelFile("item/template_spawn_egg");

    public ProviderItemModel(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    private ModelFile getModel(String loc) {
        return new ModelFile.UncheckedModelFile(new ResourceLocation(modid, loc));
    }

    protected void simpleItem(Item item, ResourceLocation texture) {
        singleTexture(item.getRegistryName().getPath(), mcLoc("generated"), "layer0", texture);
    }

    protected void simpleItem(Item item) {
        simpleItem(item, modLoc("item/" + item.getRegistryName().getPath()));
    }

    protected void simpleBlockItem(BlockItem blockItem) {
        getBuilder(blockItem.getRegistryName().getPath())
                .parent(getModel("block/" + blockItem.getRegistryName().getPath()));
    }

    protected void namedBlockItem(BlockItem blockItem, String name) {
        getBuilder(blockItem.getRegistryName().getPath())
                .parent(getModel("block/" + name));
    }

    protected void axisBlockItem(BlockItem blockItem) {
        getBuilder(blockItem.getRegistryName().getPath())
                .parent(getModel("block/" + blockItem.getRegistryName().getPath() + "_horizontal"));
    }

    protected void egg(EntityType<?> type) {
        this.getBuilder("spawn_egg_" + type.getRegistryName().getPath()).parent(TEMPLATE_SPAWN_EGG);
    }

    protected void registerEggs() {
        egg(ModEntities.DEMON_EYE.get());
        egg(ModEntities.CTHULHUL_EYE.get());
    }

    protected void registerSimpleModels() {
        simpleItem(ModItems.LENS.get());
        simpleItem(ModItems.BLOOD_LENS.get());
        simpleItem(ModItems.STRANGE_EYE.get());
        simpleItem(ModItems.LIFE_CRYSTAL.get());
        simpleItem(ModItems.WOODEN_BOW.get());
        simpleItem(ModItems.IRON_BOW.get());
        simpleItem(ModItems.GOLDEN_BOW.get());
        simpleItem(ModItems.DIAMOND_BOW.get());
        simpleItem(ModItems.SEA_BOW.get());
    }

    @Override
    protected void registerModels() {
        registerEggs();
        registerSimpleModels();
    }
}
