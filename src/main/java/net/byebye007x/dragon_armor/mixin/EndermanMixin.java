package net.byebye007x.dragon_armor.mixin;

import net.byebye007x.dragon_armor.util.ModUtil;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndermanEntity.class)
public abstract class EndermanMixin {
    @Inject(method = "isPlayerStaring", at = @At("HEAD"), cancellable = true)
    private void allowDragonArmorStare(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (ModUtil.isWearingFullDragonArmor(player)) {
            cir.setReturnValue(false);
        }
    }
}
