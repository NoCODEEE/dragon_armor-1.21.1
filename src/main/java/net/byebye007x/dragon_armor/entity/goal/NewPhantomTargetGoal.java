package net.byebye007x.dragon_armor.entity.goal;

import net.byebye007x.dragon_armor.util.ModUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Comparator;
import java.util.List;

public class NewPhantomTargetGoal extends Goal {
    private final PhantomEntity phantom;
    private final TargetPredicate targetPredicate = TargetPredicate.createAttackable().setBaseMaxDistance(64.0);
    private int delay = toGoalTicks(20);

    public NewPhantomTargetGoal(PhantomEntity phantom) {
        this.phantom = phantom;
    }

    @Override
    public boolean canStart() {
        if (this.delay > 0) {
            this.delay--;
        } else {
            this.delay = toGoalTicks(60);

            List<PlayerEntity> players = phantom.getWorld().getPlayers(
                    targetPredicate,
                    phantom,
                    phantom.getBoundingBox().expand(16.0, 64.0, 16.0)
            );

            if (!players.isEmpty()) {
                players.sort(Comparator.comparing(Entity::getY).reversed());

                for (PlayerEntity player : players) {
                    if (ModUtil.isWearingFullDragonArmor(player)) continue;

                    if (phantom.isTarget(player, TargetPredicate.DEFAULT)) {
                        phantom.setTarget(player);
                        return true;
                    }
                }
            }

        }
        return false;
    }

    @Override
    public boolean shouldContinue() {
        LivingEntity target = phantom.getTarget();
        return target != null && phantom.isTarget(target, TargetPredicate.DEFAULT);
    }
}
