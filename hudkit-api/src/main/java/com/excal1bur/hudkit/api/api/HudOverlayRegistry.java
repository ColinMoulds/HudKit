package com.excal1bur.hudkit.api.api;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.resources.Identifier;

public final class HudOverlayRegistry {
    private final Map<Identifier, HudOverlay> overlays = new LinkedHashMap<>();

    void register(HudOverlay overlay) {
        overlays.put(overlay.id(), overlay);
    }

    public Collection<HudOverlay> overlays() {
        return overlays.values();
    }
}
