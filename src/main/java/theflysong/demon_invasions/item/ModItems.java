package theflysong.demon_invasions.item;

import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import theflysong.demon_invasions.DemonInvasions;
import theflysong.demon_invasions.capability.AdditiveHealthData;

public class ModItems {
    public static DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, DemonInvasions.MODID);

    public static RegistryObject<Item> STRANGE_EYE = REGISTER.register("strange_eye", ()->
            new Item(new Item.Properties().group(ItemGroup.MISC)) {
                @Override
                public ActionResultType onItemUse(ItemUseContext context) {
                    context.getPlayer().getHeldItem(context.getHand()).shrink(1);
                    /*
                        Summon the Cthulhul Eye
                     */
                    return ActionResultType.CONSUME;
                }
            });

    public static RegistryObject<Item> LENS = REGISTER.register("lens", ()->new Item(new Item.Properties().group(ItemGroup.MISC)));
    public static RegistryObject<Item> BLOOD_LENS = REGISTER.register("blood_lens", ()->new Item(new Item.Properties().group(ItemGroup.MISC)));

    public static RegistryObject<Item> LIFE_CRYSTAL = REGISTER.register("life_crystal", ()->
            new Item(new Item.Properties().group(ItemGroup.MISC)) {
                @Override
                public ActionResultType onItemUse(ItemUseContext context) {
                    double max_health = context.getPlayer().getAttribute(Attributes.MAX_HEALTH).getBaseValue() + 2.0D;
                    if (max_health > 100.0D)
                        return ActionResultType.FAIL;
                    context.getPlayer().getHeldItem(context.getHand()).shrink(1);
                    context.getPlayer().getAttribute(Attributes.MAX_HEALTH).setBaseValue(max_health);
                    context.getPlayer().getCapability(AdditiveHealthData.ADDITIVE_HEALTH_CAPABILITY).orElse(null).addData(2.0D);
                    context.getPlayer().heal(2.0f);
                    return ActionResultType.CONSUME;
                }
            });

    public static RegistryObject<Item> WOODEN_BOW = REGISTER.register("wooden_bow", ()->new BowItemBase(new Item.Properties(), 0, 30));
    public static RegistryObject<Item> IRON_BOW = REGISTER.register("iron_bow", ()->new BowItemBase(new Item.Properties(), 0, 25));
    public static RegistryObject<Item> GOLDEN_BOW = REGISTER.register("golden_bow", ()->new BowItemBase(new Item.Properties(), 1, 25));
    public static RegistryObject<Item> DIAMOND_BOW = REGISTER.register("diamond_bow", ()->new BowItemBase(new Item.Properties(), 2, 20));
    public static RegistryObject<Item> SEA_BOW = REGISTER.register("sea_bow", ()->new BowItemBase(new Item.Properties(), 0, 5));

    public ModItems(IEventBus bus) {
        REGISTER.register(bus);
    }
}
