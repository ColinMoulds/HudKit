package com.excal1bur.hudkit.api.api;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.resources.Identifier;

public final class HudOverlayRegistry {
    private final Map<Identifier, HudOverlay> overlays = new LinkedHashMap<>();

    void register(HudOverlay overlay) {
        if (overlays.putIfAbsent(overlay.id(), overlay) != null) {
            throw new IllegalArgumentException("Overlay already registered: " + overlay.id());
        }
    }

    public Collection<HudOverlay> overlays() {
        return overlays.values();
    }
}
