package org.jurassicraft.server.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import org.jurassicraft.server.block.BlockHandler;
import org.jurassicraft.server.item.ItemHandler;

public class TabHandler {
    public static final CreativeTabs ITEMS = new CreativeTabs("jurassicraft.items") {
        @Override
        public Item getTabIconItem() {
            return ItemHandler.AMBER;
        }
    };

    public static final CreativeTabs BLOCKS = new CreativeTabs("jurassicraft.blocks") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(BlockHandler.GYPSUM_BRICKS);
        }
    };

    public static final CreativeTabs PLANTS = new CreativeTabs("jurassicraft.plants") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(BlockHandler.SMALL_ROYAL_FERN);
        }
    };

    public static final JurassiCraftFossilTab FOSSILS = new JurassiCraftFossilTab("jurassicraft.fossils");
    public static final JurassiCraftDNATab DNA = new JurassiCraftDNATab("jurassicraft.dna");
    public static final JurassiCraftFoodTab FOODS = new JurassiCraftFoodTab("jurassicraft.foods");
    public static final JurassiCraftDecorationsTab DECORATIONS = new JurassiCraftDecorationsTab("jurassicraft.decorations");
}
