# HudKit

HudKit is a client-side UI framework for NeoForge that gives mods a shared foundation for HUD overlays, settings, and in-game interface elements. Instead of every mod rolling its own overlay boilerplate, HudKit provides a reusable API and configuration layer that can be adopted across projects.

> Build cleaner, more consistent in-game interfaces with a shared HUD foundation.

## Table of Contents

- [Overview](#overview)
- [Modules](#modules)
- [Requirements](#requirements)
- [Building](#building)
- [Status](#status)
- [Contributing](#contributing)
- [License](#license)

## Overview

The project is split into two modules:

- **hudkit-api** — the shared foundation for overlay registration, configuration, and pause-menu integration.
- **hudkit-vitals** — a reference addon that demonstrates a health bar and status-effect overlay built entirely on top of HudKit.

## Modules

- **hudkit-api** — base API, shared settings framework, overlay lifecycle hooks, and integration points.
- **hudkit-vitals** — example implementation for a compact, gameplay-facing HUD experience.

## Requirements

| Component | Version |
|---|---|
| Minecraft | 26.1.2 |
| NeoForge | 26.1.2.87 or compatible tested build |
| Java | 21+ for development builds |

## Building

From the repository root, run:

```bash
./gradlew build
```

For local development and testing:

```bash
./gradlew runClient
```

## Status

HudKit is still in early development. The API is not yet considered stable, and breaking changes may occur until the project reaches a more mature release.

## Contributing

Contributions are welcome. If you are working on the framework or reference overlays, keep changes focused and document any API shifts clearly.

## License

HudKit is licensed under the [MIT License](LICENSE).
