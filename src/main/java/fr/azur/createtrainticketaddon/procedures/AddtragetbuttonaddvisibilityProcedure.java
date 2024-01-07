package fr.azur.createtrainticketaddon.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.EditBox;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

public class AddtragetbuttonaddvisibilityProcedure {
	public static boolean execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return false;
		return !(0 == new Object() {
			public int getAmount(int sltid) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
					if (stack != null)
						return stack.getCount();
				}
				return 0;
			}
		}.getAmount(0))
				&& !(guistate.containsKey("text:gare_name") ? ((EditBox) guistate.get("text:gare_name")).getValue() : "").equals(guistate.containsKey("text:second_gare_name") ? ((EditBox) guistate.get("text:second_gare_name")).getValue() : "")
				&& !(guistate.containsKey("text:gare_name") ? ((EditBox) guistate.get("text:gare_name")).getValue() : "").equals("")
				&& !(guistate.containsKey("text:second_gare_name") ? ((EditBox) guistate.get("text:second_gare_name")).getValue() : "").equals("");
	}
}
