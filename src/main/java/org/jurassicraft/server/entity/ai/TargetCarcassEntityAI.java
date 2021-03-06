package org.jurassicraft.server.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import org.jurassicraft.server.entity.DinosaurEntity;

import java.util.List;

public class TargetCarcassEntityAI extends EntityAIBase {
    private DinosaurEntity entity;
    private DinosaurEntity targetEntity;

    public TargetCarcassEntityAI(DinosaurEntity entity) {
        this.entity = entity;
    }

    @Override
    public void resetTask() {
        super.resetTask();
        this.entity.resetAttackCooldown();
    }

    @Override
    public void startExecuting() {
        this.entity.setAttackTarget(this.targetEntity);
    }

    @Override
    public boolean shouldExecute() {
        if (!this.entity.getMetabolism().isHungry()) {
            return false;
        }

        if (this.entity.getRNG().nextInt(10) != 0) {
            return false;
        }

        if (!(this.entity.herd != null && this.entity.herd.fleeing) && !this.entity.isSleeping()) {
            List<DinosaurEntity> entities = this.entity.worldObj.getEntitiesWithinAABB(DinosaurEntity.class, this.entity.getEntityBoundingBox().expand(16, 16, 16));

            if (entities.size() > 0) {
                this.targetEntity = null;
                int bestScore = Integer.MAX_VALUE;

                for (DinosaurEntity entity : entities) {
                    if (entity.isCarcass()) {
                        int score = (int) this.entity.getDistanceSqToEntity(entity);

                        if (score < bestScore) {
                            bestScore = score;
                            this.targetEntity = entity;
                        }
                    }
                }

                return this.targetEntity != null;
            }
        }

        return false;
    }
}
