
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.azur.createtrainticketaddon.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

import fr.azur.createtrainticketaddon.item.TicketItem;
import fr.azur.createtrainticketaddon.CreateTicketMod;

public class CreateTicketModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, CreateTicketMod.MODID);
	public static final RegistryObject<Item> TRAINTICKETTERMINALHITBOX = block(CreateTicketModBlocks.TRAINTICKETTERMINALHITBOX, null);
	public static final RegistryObject<Item> TRAINTICKETTERMINAL = block(CreateTicketModBlocks.TRAINTICKETTERMINAL, CreativeModeTab.TAB_TRANSPORTATION);
	public static final RegistryObject<Item> TICKET = REGISTRY.register("ticket", () -> new TicketItem());

	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
