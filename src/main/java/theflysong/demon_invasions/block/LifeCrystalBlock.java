package theflysong.demon_invasions.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemTier;
import net.minecraftforge.common.ToolType;

public class LifeCrystalBlock extends Block {
    public LifeCrystalBlock() {
        super(Properties
                .create(Material.GLASS)
                .notSolid()
                .setLightLevel(s->3)
                .sound(SoundType.GLASS)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2)
                .hardnessAndResistance(0.7f, 0.7f)
        );
    }
}
