package com.excal1bur.hudkit.api;

import com.excal1bur.hudkit.api.api.HudOverlay;
import com.excal1bur.hudkit.api.api.HudOverlayRegistry;
import com.excal1bur.hudkit.api.api.RegisterHudOverlaysEvent;
import com.excal1bur.hudkit.api.client.ClientConfig;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;

@Mod(value = HudKitMod.MODID, dist = Dist.CLIENT)
public class HudKitMod {
    public static final String MODID = "hudkit";

    private static final HudOverlayRegistry OVERLAYS = new HudOverlayRegistry();

    public HudKitMod(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);

        modEventBus.addListener(this::onClientSetup);
        modEventBus.addListener(this::onRegisterGuiLayers);
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        // fired from client setup so every mod's own listeners are already registered by the time this reaches them
        RegisterHudOverlaysEvent registerEvent = new RegisterHudOverlaysEvent(OVERLAYS);
        ModList.get().forEachModContainer((modId, container) -> {
            IEventBus bus = container.getEventBus();
            if (bus != null) {
                bus.post(registerEvent);
            }
        });
    }

    private void onRegisterGuiLayers(RegisterGuiLayersEvent event) {
        event.registerAboveAll(Identifier.fromNamespaceAndPath(MODID, "overlays"), this::renderOverlays);
    }

    private void renderOverlays(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        int screenWidth = graphics.guiWidth();
        int screenHeight = graphics.guiHeight();

        for (HudOverlay overlay : OVERLAYS.overlays()) {
            int x = overlay.anchor().resolveX(screenWidth, overlay.width(), overlay.offsetX());
            int y = overlay.anchor().resolveY(screenHeight, overlay.height(), overlay.offsetY());

            graphics.pose().pushMatrix();
            graphics.pose().translate(x, y);
            overlay.render(graphics, deltaTracker);
            graphics.pose().popMatrix();
        }
    }
}
