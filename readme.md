# AzingoCore

AzingoCore is a custom Minecraft plugin developed for the `azingo.world` server. This plugin provides various commands and event listeners to enhance the gameplay experience.

## Features

- **Discord Command**: Provides a link to the server's Discord.
- **Balance Command**: Displays the player's XP balance.
- **Pay Command**: Allows players to transfer XP to each other.
- **Player Death Listener**: Handles player death events, storing their inventory and XP.
- **Player Move Listener**: Restores player inventory and XP when they move near their death location.

## Commands

### /discord
- **Description**: Provides a link to the server's Discord.
- **Usage**: `/discord`
- **Aliases**: `dc`
- **Permission**: `azingocore.discord`

### /balance
- **Description**: Displays the player's XP balance.
- **Usage**: `/balance`
- **Aliases**: `bal`
- **Permission**: `azingocore.balance`

### /pay
- **Description**: Allows players to transfer XP to each other.
- **Usage**: `/pay <player> <amount>`
- **Permission**: `azingocore.pay`

## Setup

1. Clone the repository.
2. Navigate to the project directory.
3. Build the project using Gradle:
    ```sh
    ./gradlew build
    ```
4. Place the generated JAR file in your Minecraft server's `plugins` directory.
