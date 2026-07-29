package com.excal1bur.hudkit.api.api;

public enum Anchor {
    TOP_LEFT, TOP_CENTER, TOP_RIGHT,
    MIDDLE_LEFT, CENTER, MIDDLE_RIGHT,
    BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT;

    public int resolveX(int screenWidth, int overlayWidth, int offsetX) {
        return switch (this) {
            case TOP_LEFT, MIDDLE_LEFT, BOTTOM_LEFT -> offsetX;
            case TOP_CENTER, CENTER, BOTTOM_CENTER -> (screenWidth - overlayWidth) / 2 + offsetX;
            case TOP_RIGHT, MIDDLE_RIGHT, BOTTOM_RIGHT -> screenWidth - overlayWidth - offsetX;
        };
    }

    public int resolveY(int screenHeight, int overlayHeight, int offsetY) {
        return switch (this) {
            case TOP_LEFT, TOP_CENTER, TOP_RIGHT -> offsetY;
            case MIDDLE_LEFT, CENTER, MIDDLE_RIGHT -> (screenHeight - overlayHeight) / 2 + offsetY;
            case BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT -> screenHeight - overlayHeight - offsetY;
        };
    }
}
