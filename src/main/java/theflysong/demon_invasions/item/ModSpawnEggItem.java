package theflysong.demon_invasions.item;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import theflysong.demon_invasions.DemonInvasions;
import theflysong.demon_invasions.entity.EntityRegister;

import javax.annotation.Nullable;

public class ModSpawnEggItem extends SpawnEggItem {
    public static final String TRANSLATION_KEY = "item." + DemonInvasions.MODID + ".mod_eggs";
    private final EntityRegister.RegistryEntry<?> entity;

    public ModSpawnEggItem(EntityRegister.RegistryEntry<?> entity, final int primaryColor, final int secondaryColor) {
        super(EntityType.PIG, primaryColor, secondaryColor, new Item.Properties().group(ItemGroup.MISC));
        this.entity = entity;
        addDispenserBehavior();
    }

    private void addDispenserBehavior() {
        DispenserBlock.registerDispenseBehavior(this, new DefaultDispenseItemBehavior() {
            public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
                Direction direction = source.getBlockState().get(DispenserBlock.FACING);
                EntityType<?> type = ((SpawnEggItem) stack.getItem()).getType(stack.getTag());
                type.spawn(source.getWorld(), stack, null, source.getBlockPos().offset(direction), SpawnReason.DISPENSER, direction != Direction.UP, false);
                stack.shrink(1);
                return stack;
            }
        });
    }

    @Override
    public String getTranslationKey() {
        return TRANSLATION_KEY;
    }

    @Override
    public ITextComponent getName() {
        if (entity.getType() == null) {
            return new TranslationTextComponent(TRANSLATION_KEY, "???");
        } else {
            return new TranslationTextComponent(TRANSLATION_KEY, entity.getType().getName());
        }
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        return getName();
    }

    @Override
    public EntityType<?> getType(@Nullable final CompoundNBT nbt) {
        if (nbt != null && nbt.contains("EntityTag", 10)) {
            CompoundNBT compoundnbt = nbt.getCompound("EntityTag");
            if (compoundnbt.contains("id", 8)) {
                return EntityType.byKey(compoundnbt.getString("id")).orElse(entity.getType());
            }
        }

        return entity.getType();
    }
}
