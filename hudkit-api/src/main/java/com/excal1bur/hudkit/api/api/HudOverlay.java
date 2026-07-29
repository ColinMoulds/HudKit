package com.excal1bur.hudkit.api.api;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;

public interface HudOverlay {
    Identifier id();

    Anchor anchor();

    default int offsetX() {
        return 0;
    }

    default int offsetY() {
        return 0;
    }

    // used to resolve CENTER/RIGHT/BOTTOM anchors against the overlay's own footprint
    default int width() {
        return 0;
    }

    default int height() {
        return 0;
    }

    void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker);
}
