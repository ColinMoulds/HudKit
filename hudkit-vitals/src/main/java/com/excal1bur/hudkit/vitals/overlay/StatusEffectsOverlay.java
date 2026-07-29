package com.excal1bur.hudkit.vitals.overlay;

import com.excal1bur.hudkit.api.api.Anchor;
import com.excal1bur.hudkit.api.api.HudOverlay;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ARGB;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

import java.util.Comparator;
import java.util.List;

public final class StatusEffectsOverlay implements HudOverlay {
    private static final Identifier ID = Identifier.fromNamespaceAndPath("hudkitvitals", "status_effects");
    private static final Identifier BACKGROUND = Identifier.withDefaultNamespace("hud/effect_background");
    private static final Identifier AMBIENT_BACKGROUND = Identifier.withDefaultNamespace("hud/effect_background_ambient");
    private static final int ICON_SIZE = 24;

    @Override
    public Identifier id() {
        return ID;
    }

    @Override
    public Anchor anchor() {
        return Anchor.TOP_RIGHT;
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
        return visibleEffects().size() * ICON_SIZE;
    }

    @Override
    public int height() {
        return ICON_SIZE;
    }

    @Override
    public void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        List<MobEffectInstance> effects = visibleEffects();

        int x = 0;
        for (MobEffectInstance instance : effects) {
            Identifier background = instance.isAmbient() ? AMBIENT_BACKGROUND : BACKGROUND;
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, background, x, 0, ICON_SIZE, ICON_SIZE);

            Holder<MobEffect> effect = instance.getEffect();
            Identifier icon = effect.unwrapKey()
                    .map(ResourceKey::identifier)
                    .map(id -> id.withPrefix("mob_effect/"))
                    .orElse(null);
            if (icon != null) {
                graphics.blitSprite(RenderPipelines.GUI_TEXTURED, icon, x + 3, 3, 18, 18, ARGB.white(1.0F));
            }

            x += ICON_SIZE;
        }
    }

    private static List<MobEffectInstance> visibleEffects() {
        Player player = Minecraft.getInstance().player;
        if (player == null) {
            return List.of();
        }

        return player.getActiveEffects().stream()
                .filter(MobEffectInstance::showIcon)
                .sorted(Comparator.<MobEffectInstance>naturalOrder().reversed())
                .toList();
    }
}
