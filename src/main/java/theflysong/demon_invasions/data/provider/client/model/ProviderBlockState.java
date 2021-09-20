package theflysong.demon_invasions.data.provider.client.model;

import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import theflysong.demon_invasions.block.ModBlocks;

public class ProviderBlockState extends BlockStateProvider {
    public ProviderBlockState(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    private ModelFile getModel(String loc) {
        return new ModelFile.UncheckedModelFile(this.modLoc(loc));
    }

    protected void simpleBlock(Block block, String texture) {
        simpleBlock(block, models().cubeAll("block/" + texture, modLoc("block/" + texture)));
    }

    protected void crossBlock(Block block, String texture) {
        simpleBlock(block, models().cross("block/" + texture, modLoc("block/" + texture)));
    }

    protected void logBlock(Block block, String side, String end) {
        if (!(block instanceof RotatedPillarBlock)) {
            throw new RuntimeException("Block is not rotatable.");
        }

        axisBlock((RotatedPillarBlock) block, "block/" + side, "block/" + end);
    }

    protected void axisBlock(RotatedPillarBlock block, String side, String end) {
        ModelBuilder<BlockModelBuilder> modelBuilder =
                models().cubeColumn(block.getRegistryName().getPath(), modLoc(side), modLoc(end));
        getVariantBuilder(block)
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(modelBuilder).rotationX(90).rotationY(90).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(modelBuilder).rotationY(90).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(modelBuilder).rotationX(90).addModel();

    }

    protected void barkBlock(Block block, String all) {
        simpleBlock(block, models().cubeAll(block.getRegistryName().getPath(), modLoc("block/" + all)));
    }


    @Override
    protected void registerStatesAndModels() {
        addSimpleBlock();
        addCrops();
        addTrees();
        addBuildingBlock();
        addMachineBlock();
        addOtherBlock();
    }

    private void addSimpleBlock() {
        simpleBlock(ModBlocks.LIFE_CRYSTAL.get(), getModel("block/life_crystal"));
    }

    private void addCrops() {
    }

    private void addTrees() {
    }

    private void addBuildingBlock() {
    }

    private void addMachineBlock() {
    }

    private void addOtherBlock() {
    }
}
