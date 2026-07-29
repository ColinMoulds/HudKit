package com.excal1bur.hudkit.vitals.overlay;

import com.excal1bur.hudkit.api.api.Anchor;
import com.excal1bur.hudkit.api.api.HudOverlay;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public final class HealthBarOverlay implements HudOverlay {
    private static final Identifier ID = Identifier.fromNamespaceAndPath("hudkitvitals", "health_bar");
    private static final int WIDTH = 100;
    private static final int HEIGHT = 10;

    @Override
    public Identifier id() {
        return ID;
    }

    @Override
    public Anchor anchor() {
        return Anchor.TOP_LEFT;
    }

    @Override
    public int offsetX() {
        return 4;
    }

    @Override
    public int offsetY() {
        return 4;
    }

    @Override
    public int width() {
        return WIDTH;
    }

    @Override
    public int height() {
        return HEIGHT;
    }

    @Override
    public void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        Player player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }

        float maxHealth = (float) player.getAttributeValue(Attributes.MAX_HEALTH);
        if (maxHealth <= 0) {
            return;
        }
        float health = Math.max(0, Math.min(player.getHealth(), maxHealth));
        float percent = health / maxHealth;

        graphics.fill(0, 0, WIDTH, HEIGHT, 0xFF2B2B2B);

        int fillWidth = Math.round(WIDTH * percent);
        if (fillWidth > 0) {
            graphics.fill(0, 0, fillWidth, HEIGHT, healthColor(percent));
        }

        graphics.fill(0, 0, WIDTH, 1, 0xFF000000);
        graphics.fill(0, HEIGHT - 1, WIDTH, HEIGHT, 0xFF000000);
        graphics.fill(0, 0, 1, HEIGHT, 0xFF000000);
        graphics.fill(WIDTH - 1, 0, WIDTH, HEIGHT, 0xFF000000);

        String label = Math.round(health) + " / " + Math.round(maxHealth);
        var font = Minecraft.getInstance().font;
        int textWidth = font.width(label);
        graphics.text(font, Component.literal(label), (WIDTH - textWidth) / 2, 1, 0xFFFFFFFF);
    }

    private static int healthColor(float percent) {
        if (percent > 0.5F) {
            return 0xFF2ECC40;
        } else if (percent > 0.25F) {
            return 0xFFFFDC00;
        } else {
            return 0xFFFF4136;
        }
    }
}
