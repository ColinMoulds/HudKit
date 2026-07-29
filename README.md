# HudKit

A client-side UI framework for NeoForge. HudKit gives mods a shared way to
register HUD overlay elements — health bars, status effect displays, target
frames, whatever — instead of everyone rolling their own GuiLayer boilerplate
and settings screen.

This repo is split into two modules:

- **hudkit-api** — the base mod. Handles overlay registration, the shared
  config/settings framework, and the pause menu integration. No opinionated
  UI of its own.
- **hudkit-vitals** — the reference addon. A health bar and status effect
  overlay, built entirely through HudKit's own API.

## Requirements

- Minecraft 26.1.2
- NeoForge (see `gradle.properties` for the exact version)

## Building

```
./gradlew build
```

## Running in dev

```
./gradlew runClient
```

## Status

Early WIP. API surface is not stable yet — expect breaking changes between
versions until a 1.0 release.

## License

MIT, see `LICENSE`.
