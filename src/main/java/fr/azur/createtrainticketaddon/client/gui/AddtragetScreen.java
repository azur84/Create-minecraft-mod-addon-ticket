
package fr.azur.createtrainticketaddon.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import fr.azur.createtrainticketaddon.world.inventory.AddtragetMenu;
import fr.azur.createtrainticketaddon.procedures.AddtragetbuttonaddvisibilityProcedure;
import fr.azur.createtrainticketaddon.network.AddtragetButtonMessage;
import fr.azur.createtrainticketaddon.CreateTicketMod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class AddtragetScreen extends AbstractContainerScreen<AddtragetMenu> {
	private final static HashMap<String, Object> guistate = AddtragetMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox gare_name;
	EditBox second_gare_name;
	ImageButton imagebutton_no_texture;

	public AddtragetScreen(AddtragetMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("create_ticket:textures/screens/addtraget.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		gare_name.render(ms, mouseX, mouseY, partialTicks);
		second_gare_name.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		RenderSystem.setShaderTexture(0, new ResourceLocation("create_ticket:textures/screens/add_bouton.png"));
		this.blit(ms, this.leftPos + 150, this.topPos + 61, 0, 0, 18, 18, 18, 18);

		RenderSystem.setShaderTexture(0, new ResourceLocation("create_ticket:textures/screens/from.png"));
		this.blit(ms, this.leftPos + 6, this.topPos + 9, 0, 0, 20, 7, 20, 7);

		RenderSystem.setShaderTexture(0, new ResourceLocation("create_ticket:textures/screens/to.png"));
		this.blit(ms, this.leftPos + 6, this.topPos + 36, 0, 0, 10, 7, 10, 7);

		RenderSystem.setShaderTexture(0, new ResourceLocation("create_ticket:textures/screens/cost.png"));
		this.blit(ms, this.leftPos + 141, this.topPos + 7, 0, 0, 20, 7, 20, 7);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (gare_name.isFocused())
			return gare_name.keyPressed(key, b, c);
		if (second_gare_name.isFocused())
			return second_gare_name.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		gare_name.tick();
		second_gare_name.tick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
	}

	@Override
	public void init() {
		super.init();
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		gare_name = new EditBox(this.font, this.leftPos + 6, this.topPos + 16, 120, 20, new TranslatableComponent("gui.create_ticket.addtraget.gare_name"));
		gare_name.setMaxLength(32767);
		guistate.put("text:gare_name", gare_name);
		this.addWidget(this.gare_name);
		second_gare_name = new EditBox(this.font, this.leftPos + 6, this.topPos + 43, 120, 20, new TranslatableComponent("gui.create_ticket.addtraget.second_gare_name"));
		second_gare_name.setMaxLength(32767);
		guistate.put("text:second_gare_name", second_gare_name);
		this.addWidget(this.second_gare_name);
		imagebutton_no_texture = new ImageButton(this.leftPos + 151, this.topPos + 62, 16, 16, 0, 0, 16, new ResourceLocation("create_ticket:textures/screens/atlas/imagebutton_no_texture.png"), 16, 32, e -> {
			if (AddtragetbuttonaddvisibilityProcedure.execute(entity, guistate)) {
				CreateTicketMod.PACKET_HANDLER.sendToServer(new AddtragetButtonMessage(0, x, y, z));
				AddtragetButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}) {
			@Override
			public void render(PoseStack ms, int gx, int gy, float ticks) {
				if (AddtragetbuttonaddvisibilityProcedure.execute(entity, guistate))
					super.render(ms, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_no_texture", imagebutton_no_texture);
		this.addRenderableWidget(imagebutton_no_texture);
	}
}
