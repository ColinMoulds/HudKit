package com.excal1bur.hudkit.vitals;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = HudKitVitalsMod.MODID, dist = Dist.CLIENT)
public class HudKitVitalsMod {
    public static final String MODID = "hudkitvitals";

    public HudKitVitalsMod(IEventBus modEventBus) {
    }
}
