
package fr.azur.createtrainticketaddon.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import fr.azur.createtrainticketaddon.world.inventory.TrainticketterminalguiMenu;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class TrainticketterminalguiScreen extends AbstractContainerScreen<TrainticketterminalguiMenu> {
	private final static HashMap<String, Object> guistate = TrainticketterminalguiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	ImageButton imagebutton_no_texture;
	ImageButton imagebutton_setting;

	public TrainticketterminalguiScreen(TrainticketterminalguiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		RenderSystem.setShaderTexture(0, new ResourceLocation("create_ticket:textures/screens/schedule.png"));
		this.blit(ms, this.leftPos + -47, this.topPos + -29, 0, 0, 256, 256, 256, 256);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
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
		imagebutton_no_texture = new ImageButton(this.leftPos + 167, this.topPos + 174, 16, 16, 0, 0, 16, new ResourceLocation("create_ticket:textures/screens/atlas/imagebutton_no_texture.png"), 16, 32, e -> {
		});
		guistate.put("button:imagebutton_no_texture", imagebutton_no_texture);
		this.addRenderableWidget(imagebutton_no_texture);
		imagebutton_setting = new ImageButton(this.leftPos + -27, this.topPos + 173, 18, 18, 0, 0, 18, new ResourceLocation("create_ticket:textures/screens/atlas/imagebutton_setting.png"), 18, 36, e -> {
		});
		guistate.put("button:imagebutton_setting", imagebutton_setting);
		this.addRenderableWidget(imagebutton_setting);
	}
}
