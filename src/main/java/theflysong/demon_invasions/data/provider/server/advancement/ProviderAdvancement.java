package theflysong.demon_invasions.data.provider.server.advancement;

import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.KilledTrigger;
import net.minecraft.advancements.criterion.TickTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import theflysong.demon_invasions.entity.ModEntities;
import theflysong.demon_invasions.item.ModItems;

public class ProviderAdvancement extends ProviderBaseAdvancement {
    public ProviderAdvancement(DataGenerator generatorIn, String modId) {
        super(generatorIn, modId);
    }

    /**
     * Put advancement after dependencies.
     */
    @Override
    protected void registerAdvancements() {
        add(modLoc("basic/root"), root(new ItemStack(ModItems.WOODEN_BOW.get()), "root",
            modLoc("textures/gui/advancements/backgrounds/bone_block_top.png"),
            FrameType.TASK, false)
            .withCriterion("only", new TickTrigger.Instance(EntityPredicate.AndPredicate.ANY_AND)));

        add(modLoc("basic/break_heart"), child(new ItemStack(ModItems.LIFE_CRYSTAL.get()), "break_heart",
            modLoc("basic/root"), FrameType.GOAL, true, true, false)
            .withCriterion("got_heart", hasItem(ModItems.LIFE_CRYSTAL.get())));

        add(modLoc("basic/blind"), child(new ItemStack(ModItems.STRANGE_EYE.get()), "blind",
                modLoc("basic/root"), FrameType.CHALLENGE, true, true, false)
                .withCriterion("kill_cthulhul_eye", killEntity(ModEntities.CTHULHUL_EYE.get())));
    }

    @Override
    public String getName() {
        return "Advancements";
    }
}
