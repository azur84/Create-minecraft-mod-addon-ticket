
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

import fr.azur.createtrainticketaddon.world.inventory.TrainticketterminalguiMenu;
import fr.azur.createtrainticketaddon.procedures.Visibleslot4Procedure;
import fr.azur.createtrainticketaddon.procedures.Visibleslot3Procedure;
import fr.azur.createtrainticketaddon.procedures.Visibleslot2Procedure;
import fr.azur.createtrainticketaddon.procedures.Visibleslot1Procedure;
import fr.azur.createtrainticketaddon.procedures.To4Procedure;
import fr.azur.createtrainticketaddon.procedures.To3Procedure;
import fr.azur.createtrainticketaddon.procedures.To2Procedure;
import fr.azur.createtrainticketaddon.procedures.To1Procedure;
import fr.azur.createtrainticketaddon.procedures.From4Procedure;
import fr.azur.createtrainticketaddon.procedures.From3Procedure;
import fr.azur.createtrainticketaddon.procedures.From2Procedure;
import fr.azur.createtrainticketaddon.procedures.Form1Procedure;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class TrainticketterminalguiScreen extends AbstractContainerScreen<TrainticketterminalguiMenu> {
	private final static HashMap<String, Object> guistate = TrainticketterminalguiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox search;
	ImageButton imagebutton_no_texture;
	ImageButton imagebutton_setting;
	ImageButton imagebutton_add_shopping_cart;
	ImageButton imagebutton_add_shopping_cart1;
	ImageButton imagebutton_add_shopping_cart2;
	ImageButton imagebutton_add_shopping_cart3;
	ImageButton imagebutton_down;
	ImageButton imagebutton_up;

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
		search.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		RenderSystem.setShaderTexture(0, new ResourceLocation("create_ticket:textures/screens/schedule.png"));
		this.blit(ms, this.leftPos + -47, this.topPos + -29, 0, 0, 256, 256, 256, 256);

		if (Visibleslot1Procedure.execute(entity)) {
			RenderSystem.setShaderTexture(0, new ResourceLocation("create_ticket:textures/screens/gui.png"));
			this.blit(ms, this.leftPos + -20, this.topPos + -11, 0, 0, 200, 40, 200, 40);
		}
		if (Visibleslot2Procedure.execute(entity)) {
			RenderSystem.setShaderTexture(0, new ResourceLocation("create_ticket:textures/screens/gui.png"));
			this.blit(ms, this.leftPos + -20, this.topPos + 30, 0, 0, 200, 40, 200, 40);
		}
		if (Visibleslot3Procedure.execute(entity)) {
			RenderSystem.setShaderTexture(0, new ResourceLocation("create_ticket:textures/screens/gui.png"));
			this.blit(ms, this.leftPos + -20, this.topPos + 71, 0, 0, 200, 40, 200, 40);
		}
		if (Visibleslot4Procedure.execute(entity)) {
			RenderSystem.setShaderTexture(0, new ResourceLocation("create_ticket:textures/screens/gui.png"));
			this.blit(ms, this.leftPos + -20, this.topPos + 112, 0, 0, 200, 40, 200, 40);
		}
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (search.isFocused())
			return search.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		search.tick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack,

				Form1Procedure.execute(entity), -10, -5, -1);
		this.font.draw(poseStack,

				To1Procedure.execute(entity), -10, 12, -1);
		this.font.draw(poseStack,

				From2Procedure.execute(entity), -10, 36, -1);
		this.font.draw(poseStack,

				To2Procedure.execute(entity), -10, 53, -1);
		this.font.draw(poseStack,

				From3Procedure.execute(entity), -10, 77, -1);
		this.font.draw(poseStack,

				To3Procedure.execute(entity), -10, 94, -1);
		this.font.draw(poseStack,

				From4Procedure.execute(entity), -10, 118, -1);
		this.font.draw(poseStack,

				To4Procedure.execute(entity), -10, 135, -1);
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
		search = new EditBox(this.font, this.leftPos + -3, this.topPos + 172, 120, 20, new TranslatableComponent("gui.create_ticket.trainticketterminalgui.search"));
		search.setMaxLength(32767);
		guistate.put("text:search", search);
		this.addWidget(this.search);
		imagebutton_no_texture = new ImageButton(this.leftPos + 167, this.topPos + 174, 16, 16, 0, 0, 16, new ResourceLocation("create_ticket:textures/screens/atlas/imagebutton_no_texture.png"), 16, 32, e -> {
		});
		guistate.put("button:imagebutton_no_texture", imagebutton_no_texture);
		this.addRenderableWidget(imagebutton_no_texture);
		imagebutton_setting = new ImageButton(this.leftPos + -27, this.topPos + 173, 18, 18, 0, 0, 18, new ResourceLocation("create_ticket:textures/screens/atlas/imagebutton_setting.png"), 18, 36, e -> {
		});
		guistate.put("button:imagebutton_setting", imagebutton_setting);
		this.addRenderableWidget(imagebutton_setting);
		imagebutton_add_shopping_cart = new ImageButton(this.leftPos + 136, this.topPos + -8, 36, 16, 0, 0, 16, new ResourceLocation("create_ticket:textures/screens/atlas/imagebutton_add_shopping_cart.png"), 36, 32, e -> {
		}) {
			@Override
			public void render(PoseStack ms, int gx, int gy, float ticks) {
				if (Visibleslot1Procedure.execute(entity))
					super.render(ms, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_add_shopping_cart", imagebutton_add_shopping_cart);
		this.addRenderableWidget(imagebutton_add_shopping_cart);
		imagebutton_add_shopping_cart1 = new ImageButton(this.leftPos + 136, this.topPos + 33, 36, 16, 0, 0, 16, new ResourceLocation("create_ticket:textures/screens/atlas/imagebutton_add_shopping_cart1.png"), 36, 32, e -> {
		}) {
			@Override
			public void render(PoseStack ms, int gx, int gy, float ticks) {
				if (Visibleslot2Procedure.execute(entity))
					super.render(ms, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_add_shopping_cart1", imagebutton_add_shopping_cart1);
		this.addRenderableWidget(imagebutton_add_shopping_cart1);
		imagebutton_add_shopping_cart2 = new ImageButton(this.leftPos + 136, this.topPos + 74, 36, 16, 0, 0, 16, new ResourceLocation("create_ticket:textures/screens/atlas/imagebutton_add_shopping_cart2.png"), 36, 32, e -> {
		}) {
			@Override
			public void render(PoseStack ms, int gx, int gy, float ticks) {
				if (Visibleslot3Procedure.execute(entity))
					super.render(ms, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_add_shopping_cart2", imagebutton_add_shopping_cart2);
		this.addRenderableWidget(imagebutton_add_shopping_cart2);
		imagebutton_add_shopping_cart3 = new ImageButton(this.leftPos + 136, this.topPos + 115, 36, 16, 0, 0, 16, new ResourceLocation("create_ticket:textures/screens/atlas/imagebutton_add_shopping_cart3.png"), 36, 32, e -> {
		}) {
			@Override
			public void render(PoseStack ms, int gx, int gy, float ticks) {
				if (Visibleslot4Procedure.execute(entity))
					super.render(ms, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_add_shopping_cart3", imagebutton_add_shopping_cart3);
		this.addRenderableWidget(imagebutton_add_shopping_cart3);
		imagebutton_down = new ImageButton(this.leftPos + 118, this.topPos + 173, 18, 18, 0, 0, 18, new ResourceLocation("create_ticket:textures/screens/atlas/imagebutton_down.png"), 18, 36, e -> {
		});
		guistate.put("button:imagebutton_down", imagebutton_down);
		this.addRenderableWidget(imagebutton_down);
		imagebutton_up = new ImageButton(this.leftPos + 139, this.topPos + 173, 18, 18, 0, 0, 18, new ResourceLocation("create_ticket:textures/screens/atlas/imagebutton_up.png"), 18, 36, e -> {
		});
		guistate.put("button:imagebutton_up", imagebutton_up);
		this.addRenderableWidget(imagebutton_up);
	}
}
