package net.byebye007x.dragon_armor.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.byebye007x.dragon_armor.util.ModUtil.isWearingFullDragonArmor;

@Mixin(EnderPearlEntity.class)
public abstract class ThrownEnderPearlMixin extends ThrownItemEntity {
    public ThrownEnderPearlMixin(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Redirect(
            method = "onCollision",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"
            )
    )
    private boolean redirectEnderPearlDamage(Entity target, DamageSource source, float amount) {
        if (target instanceof PlayerEntity player && isWearingFullDragonArmor(player)) {
            return false;
        }
        return target.damage(source, amount);
    }
}
