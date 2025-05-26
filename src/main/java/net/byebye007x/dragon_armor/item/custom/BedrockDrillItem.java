package net.byebye007x.dragon_armor.item.custom;

import net.byebye007x.dragon_armor.item.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BedrockDrillItem extends MiningToolItem {
    public BedrockDrillItem(ToolMaterial material, Settings settings) {
        super(material, ModTags.Blocks.BEDROCK_DRILL_ABLE, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        PlayerEntity player = context.getPlayer();

        if (!world.isClient && player != null) {
            BlockState state = world.getBlockState(pos);
            if (!state.isAir()) {

                world.playSound(null, pos, state.getSoundGroup().getBreakSound(), SoundCategory.BLOCKS, 3.0F, 1.0F);
                world.breakBlock(pos, false, player);
            }
        }

        return ActionResult.SUCCESS;
    }
}
