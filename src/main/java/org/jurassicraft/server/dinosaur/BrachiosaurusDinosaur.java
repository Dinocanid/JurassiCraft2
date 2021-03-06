package org.jurassicraft.server.dinosaur;

import net.minecraftforge.common.BiomeDictionary;
import org.jurassicraft.server.entity.Diet;
import org.jurassicraft.server.entity.dinosaur.BrachiosaurusEntity;
import org.jurassicraft.server.period.TimePeriod;

public class BrachiosaurusDinosaur extends Dinosaur {
    public BrachiosaurusDinosaur() {
        super();

        this.setName("Brachiosaurus");
        this.setDinosaurClass(BrachiosaurusEntity.class);
        this.setTimePeriod(TimePeriod.JURASSIC);
        this.setEggColorMale(0x87987F, 0x607343);
        this.setEggColorFemale(0xAA987D, 0x4F4538);
        this.setHealth(20, 150);
        this.setSpeed(0.3, 0.32);
        this.setStrength(5, 15);
        this.setMaximumAge(this.fromDays(85));
        this.setEyeHeight(2.2F, 18.4F);
        this.setSizeX(0.9F, 6.5F);
        this.setSizeY(1.5F, 7.0F);
        this.setStorage(54);
        this.setDiet(Diet.HERBIVORE);
        this.setBones("skull", "tooth", "tail_vertebrae", "shoulder", "ribcage", "pelvis", "neck_vertebrae", "hind_leg_bones", "front_leg_bones");
        this.setHeadCubeName("head");
        this.setScale(2.5F, 0.3F);
        this.setOffset(0.0F, 0.0F, 1.0F);
        this.setAttackBias(1200.0);
        this.setMaxHerdSize(4);
        this.setSpawn(5, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.FOREST));
    }
}
