package theflysong.demon_invasions.data.provider.server.recipe;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import theflysong.demon_invasions.item.ModItems;

import java.util.function.Consumer;

public class ProviderRecipe extends ProviderBaseRecipe {
    public String modid;

    public ProviderRecipe(DataGenerator generatorIn, String modid) {
        super(generatorIn);
        this.modid = modid;
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        registerShapedRecipes(consumer);
        registerShapelessRecipes(consumer);
        registerFurnaceRecipes(consumer);
    }

    private void registerShapedRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ModItems.STRANGE_EYE.get())
                .patternLine("AA ")
                .patternLine("ABA")
                .patternLine(" AA")
                .key('A', ModItems.LENS.get())
                .key('B', Items.ENDER_EYE)
                .addCriterion("get_lens", triggerItem(ModItems.LENS.get()))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModItems.WOODEN_BOW.get())
                .patternLine(" #X")
                .patternLine("# X")
                .patternLine(" #X")
                .key('#', Items.STICK)
                .key('X', Items.STRING)
                .addCriterion("get_string", triggerItem(Items.STRING))
                .addCriterion("get_stick", triggerItem(Items.STICK))
                .build(consumer, new ResourceLocation("minecraft", "bow"));

        ShapedRecipeBuilder.shapedRecipe(ModItems.IRON_BOW.get())
                .patternLine(" #X")
                .patternLine("# X")
                .patternLine(" #X")
                .key('#', Items.IRON_INGOT)
                .key('X', Items.STRING)
                .addCriterion("get_string", triggerItem(Items.STRING))
                .addCriterion("get_iron", triggerItem(Items.IRON_INGOT))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModItems.GOLDEN_BOW.get())
                .patternLine(" #X")
                .patternLine("# X")
                .patternLine(" #X")
                .key('#', Items.GOLD_INGOT)
                .key('X', Items.STRING)
                .addCriterion("get_string", triggerItem(Items.STRING))
                .addCriterion("get_gold", triggerItem(Items.GOLD_INGOT))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModItems.DIAMOND_BOW.get())
                .patternLine(" #X")
                .patternLine("# X")
                .patternLine(" #X")
                .key('#', Items.DIAMOND)
                .key('X', Items.STRING)
                .addCriterion("get_string", triggerItem(Items.STRING))
                .addCriterion("get_diamond", triggerItem(Items.DIAMOND))
                .build(consumer);
    }

    private void registerShapelessRecipes(Consumer<IFinishedRecipe> consumer) {

    }

    private void registerFurnaceRecipes(Consumer<IFinishedRecipe> consumer) {

    }
}
