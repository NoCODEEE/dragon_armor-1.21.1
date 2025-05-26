package net.byebye007x.dragon_armor.mixin;

import net.byebye007x.dragon_armor.entity.goal.NewPhantomTargetGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PhantomEntity.class)
public abstract class PhantomMixin extends FlyingEntity implements Monster {
    protected PhantomMixin(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals", at = @At("TAIL"))
    private void replaceTargetGoal(CallbackInfo ci) {
        PhantomEntity self = (PhantomEntity) (Object) this;
        this.targetSelector.getGoals().removeIf(entry -> {
            Goal goal = entry.getGoal();
            return goal.getClass().getSimpleName().equals("FindTargetGoal"); // Phantom's default
        });

        this.targetSelector.add(1, new NewPhantomTargetGoal(self));
    }
}
