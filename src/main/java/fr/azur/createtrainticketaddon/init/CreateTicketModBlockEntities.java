
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.azur.createtrainticketaddon.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import fr.azur.createtrainticketaddon.block.entity.TrainticketterminalBlockEntity;
import fr.azur.createtrainticketaddon.CreateTicketMod;

public class CreateTicketModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, CreateTicketMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> TRAINTICKETTERMINAL = register("trainticketterminal", CreateTicketModBlocks.TRAINTICKETTERMINAL, TrainticketterminalBlockEntity::new);

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
