package com.excal1bur.hudkit.api;

import com.excal1bur.hudkit.api.client.ClientConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(value = HudKitMod.MODID, dist = Dist.CLIENT)
public class HudKitMod {
    public static final String MODID = "hudkit";

    public HudKitMod(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
    }
}
