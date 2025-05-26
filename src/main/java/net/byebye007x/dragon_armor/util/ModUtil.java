package net.byebye007x.dragon_armor.util;

import net.byebye007x.dragon_armor.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;

public class ModUtil {
    public static boolean isWearingFullDragonArmor(PlayerEntity player) {
        return !player.getInventory().getArmorStack(3).isEmpty() &&
                player.getInventory().getArmorStack(3).getItem() == ModItems.DRAGON_HELMET &&
                !player.getInventory().getArmorStack(2).isEmpty() &&
                player.getInventory().getArmorStack(2).getItem() == ModItems.DRAGON_CHESTPLATE &&
                !player.getInventory().getArmorStack(1).isEmpty() &&
                player.getInventory().getArmorStack(1).getItem() == ModItems.DRAGON_LEGGING &&
                !player.getInventory().getArmorStack(0).isEmpty() &&
                player.getInventory().getArmorStack(0).getItem() == ModItems.DRAGON_BOOTS;
    }
}
