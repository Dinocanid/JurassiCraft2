package org.jurassicraft.server.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.server.dinosaur.Dinosaur;
import org.jurassicraft.server.entity.EntityHandler;
import org.jurassicraft.server.item.ItemHandler;

import java.util.List;

public class JurassiCraftDNATab extends CreativeTabs {
    private ItemStack[] stacks = null;

    public JurassiCraftDNATab(String label) {
        super(label);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {
        if (this.stacks == null) {
            List<Dinosaur> registeredDinosaurs = EntityHandler.getRegisteredDinosaurs();

            int dinosaurs = registeredDinosaurs.size();
            this.stacks = new ItemStack[dinosaurs * 3];

            int i = 0;

            for (Dinosaur dino : registeredDinosaurs) {
                int id = EntityHandler.getDinosaurId(dino);

                this.stacks[i] = new ItemStack(ItemHandler.DNA, 1, id);
                this.stacks[i + dinosaurs] = new ItemStack(ItemHandler.SOFT_TISSUE, 1, id);
                this.stacks[i + (dinosaurs * 2)] = new ItemStack(ItemHandler.SYRINGE, 1, id);

                i++;
            }
        }

        return this.stacks[(int) ((JurassiCraft.timerTicks / 20) % this.stacks.length)];
    }

    @Override
    public Item getTabIconItem() {
        return ItemHandler.DNA;
    }
}
