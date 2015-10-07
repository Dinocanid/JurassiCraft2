package org.jurassicraft.common.plant;

import java.util.ArrayList;
import java.util.List;

public class JCPlantRegistry
{
    private static List<Plant> plants = new ArrayList<>();

    public static final Plant small_royal_fern = new PlantSmallRoyalFern();
    public static final Plant calamites = new PlantCalamites();
    public static final Plant small_chain_fern = new PlantSmallChainFern();
    public static final Plant small_cycad = new PlantSmallCycad();
    public static final Plant ginkgo = new PlantGinkgo();
    public static final Plant bennettitalean_cycadeoidea = new PlantBennettitaleanCycadeoidea();
    public static final Plant cry_pansy = new PlantCryPansy();
    public static final Plant scaly_tree_fern = new PlantScalyTreeFern();
    public static final Plant cycad_zamites = new PlantZamites();
    public static final Plant dicksonia = new PlantDicksonia();

    public void register()
    {
        registerPlant(small_royal_fern);
        registerPlant(calamites);
        registerPlant(small_chain_fern);
        registerPlant(small_cycad);
        registerPlant(ginkgo);
        registerPlant(bennettitalean_cycadeoidea);
        registerPlant(cry_pansy);
        registerPlant(scaly_tree_fern);
        registerPlant(cycad_zamites);
        registerPlant(dicksonia);
    }

    public static Plant getPlantById(int id)
    {
        if (id >= plants.size() || id < 0)
        {
            return null;
        }

        return plants.get(id);
    }

    public static int getPlantId(Plant plant)
    {
        return plants.indexOf(plant);
    }

    public static List<Plant> getPlants()
    {
        return plants;
    }

    public void registerPlant(Plant plant)
    {
        if (!plants.contains(plant))
        {
            plants.add(plant);
        }
    }
}
