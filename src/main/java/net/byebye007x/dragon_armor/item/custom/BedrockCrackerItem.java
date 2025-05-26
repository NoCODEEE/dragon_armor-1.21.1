package net.byebye007x.dragon_armor.item.custom;

import net.byebye007x.dragon_armor.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class BedrockCrackerItem extends Item {
    public BedrockCrackerItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);

        if (state.getBlock() == Blocks.BEDROCK) {
            if (!world.isClient) {

                ItemEntity bedrockShardItem = new ItemEntity(
                        world,
                        pos.getX() + 0.5,
                        pos.getY() + 1.0,
                        pos.getZ() + 0.5,
                        new ItemStack(ModItems.BEDROCK_SHARD)
                );

                bedrockShardItem.setPickupDelay(5);

                bedrockShardItem.setVelocity(
                        world.random.nextGaussian() * 0.05,
                        0.3 + world.random.nextFloat() * 0.1,
                        world.random.nextGaussian() * 0.05
                );

                world.spawnEntity(bedrockShardItem);

                context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                        item -> Objects.requireNonNull(context.getPlayer()).sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                world.playSound(null, context.getBlockPos(), SoundEvents.BLOCK_DEEPSLATE_BREAK, SoundCategory.BLOCKS);
            }

            return ActionResult.SUCCESS;
        }

        return super.useOnBlock(context);
    }
}
