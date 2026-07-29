package com.excal1bur.hudkit.api.api;

import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;

public final class RegisterHudOverlaysEvent extends Event implements IModBusEvent {
    private final HudOverlayRegistry registry;

    public RegisterHudOverlaysEvent(HudOverlayRegistry registry) {
        this.registry = registry;
    }

    public void register(HudOverlay overlay) {
        registry.register(overlay);
    }
}
