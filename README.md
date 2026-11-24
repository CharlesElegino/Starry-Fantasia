# Starry Fantasy

**Starry Fantasy** is a small turn-based fantasy RPG made in Java as a second-year college class project. It uses a Swing desktop interface with animated character art, stage backgrounds, and music.

## About the game

Start a new game, choose a character class, and take that character through three stages of turn-based battles. Combat includes basic and stronger attacks, buffs, ultimate skills that use skill points, and enemy turns. Victories contribute to character progression; you can save your character and continue later.

Available classes:

- **Swordsman** — a balanced melee fighter
- **Ranger** — a faster, attack-focused fighter
- **Magus** — a magic-focused fighter with high attack

The game window is designed for a 960 × 540 display. A save created with the in-game Save button is stored in `saveFile.txt` in the working directory.

## Requirements

- Java Development Kit (JDK) 8 or newer
- The `resource` directory, which contains the game's images, animations, and audio

## Run from source

Open a terminal in the project directory and compile the Java files:

```bash
javac *.java
```

Then launch the game:

```bash
java Launcher
```

Run it from the project directory so the game can find its `resource` assets. The project also includes an IntelliJ IDEA module file (`Starry Fantasy.iml`) and can be opened in IntelliJ IDEA.

## Project structure

```text
.
├── Launcher.java                 # Application entry point
├── Game.java                     # Game state, screens, and gameplay logic
├── *Screen.java                  # Menu, selection, and battle screen layouts
├── CharacterStatus.java          # Selectable class statistics
├── PlayerStats.java              # Player health and progression
├── EnemyStatus.java              # Stage enemy statistics
└── resource/
    ├── Backgrounds/
    ├── Characters/
    ├── Sounds/
    └── UI/
```

## Asset and trademark disclaimer

This is an unofficial, non-commercial student project created for a college class. Some visual and audio assets are from or associated with **FINAL FANTASY BRAVE EXVIUS** (sometimes informally misspelled “Brave Exius”), a property of its respective rights holders. All third-party characters, artwork, audio, names, trademarks, and other materials remain the property of their respective owners. Their inclusion here does not imply sponsorship, endorsement, or affiliation with the rights holders. This project is not an official *FINAL FANTASY* product and is not intended for commercial use. No ownership of third-party assets is claimed; please contact the relevant rights holders for permissions or licensing questions.

## Credits

Created as a second-year college class project. The project also contains original code and project-specific UI/game design. Third-party asset rights remain with their respective owners.
