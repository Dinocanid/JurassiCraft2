package org.jurassicraft.common.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.item.JCItemRegistry;

import java.util.List;

public class EntityAIEatMeat extends EntityAIBase
{
    protected EntityDinosaur dinosaur;

    protected EntityItem item;

    public EntityAIEatMeat(EntityDinosaur dinosaur)
    {
        this.dinosaur = dinosaur;
    }

    @Override
    public boolean shouldExecute()
    {
        double energy = dinosaur.getEnergy();

        if (((int) energy) > 0 && dinosaur.ticksExisted % 8 == 0)
        {
            if (dinosaur.getRNG().nextInt((int) energy) < (energy / 4))
            {
                double posX = dinosaur.posX;
                double posY = dinosaur.posY;
                double posZ = dinosaur.posZ;

                double closestDist = Integer.MAX_VALUE;
                EntityItem closest = null;

                boolean found = false;

                World world = dinosaur.worldObj;

                List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.fromBounds(posX - 16, posY - 16, posZ - 16, posX + 16, posY + 16, posZ + 16));

                for (EntityItem item : items)
                {
                    ItemStack stack = item.getEntityItem();

                    if(stack != null && stack.getItem() == JCItemRegistry.dino_meat)
                    {
                        double diffX = posX - item.posX;
                        double diffY = posY - item.posY;
                        double diffZ = posZ - item.posZ;

                        double dist = (diffX * diffX) + (diffY * diffY) + (diffZ * diffZ);

                        if (dist < closestDist)
                        {
                            closestDist = dist;
                            closest = item;

                            found = true;
                        }
                    }
                }

                if (found)
                {
                    dinosaur.getNavigator().tryMoveToEntityLiving(closest, 1.0);
                    this.item = closest;

                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void updateTask()
    {
        if (dinosaur.getEntityBoundingBox().intersectsWith(item.getEntityBoundingBox().expand(0.5D, 0.5D, 0.5D)))
        {
            AnimationAPI.sendAnimPacket(dinosaur, AnimID.EATING);

            if (dinosaur.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"))
            {
                if(item.getEntityItem().stackSize > 1)
                {
                    item.getEntityItem().stackSize--;
                }
                else
                {
                    item.setDead();
                }
            }

            dinosaur.setEnergy(dinosaur.getEnergy() + 200);
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
        return dinosaur != null && !this.dinosaur.getNavigator().noPath() && item != null && !item.isDead;
    }
}