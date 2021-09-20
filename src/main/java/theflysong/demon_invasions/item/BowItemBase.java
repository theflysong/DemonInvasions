package theflysong.demon_invasions.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BowItemBase extends Item {
    private double damage;
    private int countdown;

    public BowItemBase(Properties properties, double damage, int countdown) {
        super(properties.group(ItemGroup.COMBAT).maxStackSize(1));
        this.damage = damage;
        this.countdown = countdown;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand handIn) {
        ItemStack stack = player.getHeldItem(handIn);
        player.getCooldownTracker().setCooldown(stack.getItem(), countdown);
        //TODO: Use Custom Arrow
        AbstractArrowEntity arrow = ((ArrowItem)Items.ARROW).createArrow(world, new ItemStack(Items.ARROW), player);
        arrow.setDamage(arrow.getDamage() + damage);
        arrow.setDirectionAndMovement(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.0F * 3.0F, 1.0F);
        world.addEntity(arrow);
        return ActionResult.resultSuccess(stack);
    }
}
