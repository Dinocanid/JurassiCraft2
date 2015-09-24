package net.timeless.animationapi.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.entity.base.EntityDinosaur;

public class PacketAnim implements IMessage
{
    private int animID;
    private int entityID;

    public PacketAnim()
    {
    }

    public PacketAnim(AnimID parAnimID, int parEntityID)
    {
        JurassiCraft.instance.getLogger().debug("Constructing PacketAnim for entity " + parEntityID + " with animation id " + parAnimID);

        animID = parAnimID.ordinal();
        entityID = parEntityID;
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeInt(animID);
        buffer.writeInt(entityID);
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        animID = buffer.readInt();
        entityID = buffer.readInt();
    }

    public static class Handler implements IMessageHandler<PacketAnim, IMessage>
    {
        @Override
        public IMessage onMessage(final PacketAnim packet, MessageContext ctx)
        {
            JurassiCraft.instance.getLogger().info("PacketAnim received for entity " + packet.entityID + " and animation ID " + AnimID.values()[packet.animID]);

            final EntityPlayer player = JurassiCraft.proxy.getPlayerEntityFromContext(ctx);

            Minecraft.getMinecraft().addScheduledTask(new Runnable()
            {
                private AnimID animation = AnimID.values()[packet.animID];

                @Override
                public void run()
                {
                    World world = player.worldObj;
                    EntityDinosaur entity = (EntityDinosaur) world.getEntityByID(packet.entityID);

                    if (entity != null)
                    {
                        entity.setAnimID(this.animation);
                    }

                }
            });

            return null;
        }
    }
}
