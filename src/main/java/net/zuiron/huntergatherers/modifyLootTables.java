package net.zuiron.huntergatherers;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Items;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.zuiron.huntergatherers.item.ModItems;

public class modifyLootTables {

    private static final Identifier OAK_LEAVES_BLOCK_LOOT_TABLE_ID = new Identifier("minecraft", "blocks/oak_leaves");
    private static final Identifier BIRCH_LEAVES_BLOCK_LOOT_TABLE_ID = new Identifier("minecraft", "blocks/birch_leaves");
    //private static final Identifier WHITE_SHEEP_LOOT_TABLE_ID = new Identifier("minecraft", "entities/sheep/white");
    private static final Identifier COMMON_SHEEP_LOOT_TABLE_ID = new Identifier("minecraft", "entities/sheep");

    public static void registerModifyLootTables() {

        LootTableLoadingCallback.EVENT.register(((resourceManager, manager, id, supplier, setter) -> {

            //modify oak leaves.
            if(OAK_LEAVES_BLOCK_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(Items.STICK))
                        .with(ItemEntry.builder(ModItems.CONE_OAK))
                        .with(ItemEntry.builder(ModItems.LEAF_OAK))
                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.0f)).build());
                supplier.withPool(poolBuilder.build());
            }

            //modify birch leaves
            if(BIRCH_LEAVES_BLOCK_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(Items.STICK))
                        .with(ItemEntry.builder(ModItems.CONE_BIRCH))
                        .with(ItemEntry.builder(ModItems.LEAF_BIRCH))
                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.0f)).build());
                supplier.withPool(poolBuilder.build());
            }

            //add sheep bone to "all/common" sheep.
			if(COMMON_SHEEP_LOOT_TABLE_ID.equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
						.rolls(ConstantLootNumberProvider.create(1))
						.with(ItemEntry.builder(ModItems.SHEEP_BONES))
						.withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.0f)).build());
				supplier.withPool(poolBuilder.build());
			}

        } ));

        //System.out.println("Registered Mod Items for " + HunterGatherers.MOD_ID);
        HunterGatherers.LOGGER.info("modifying loot tables.");
    }
}
