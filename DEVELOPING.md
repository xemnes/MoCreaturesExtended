# DEVELOPING.md

## Our Recommended DevTools

### IDE:
1. Single Hotswap Plugin: [Plugin Link](https://plugins.jetbrains.com/plugin/14832-single-hotswap)
2. GitHub Copilot: [Plugin Link](https://plugins.jetbrains.com/plugin/17718-github-copilot)
3. Recommended SDK for Project Structure: coretto-1.8


### Loot table editing/testing:
* [Loot Table Generator](https://misode.github.io/loot-table/)
* [1.12.2 loot table docs](https://docs.minecraftforge.net/en/1.12.x/items/loot_tables/)
* [1.12.2 loot table condition docs with examples](https://learn.microsoft.com/en-us/minecraft/creator/documents/loottableconditions?view=minecraft-bedrock-stable)
1. Select a loot table you'd like to view/test
2. Copy the json (e.g. dark_manticore.json)
3. Open up [Loot Table Generator](https://misode.github.io/loot-table/) and change the version to "1.15" for the best compatibility
4. Paste the json into the box at the bottom right (Click "Show output <>")
5. Keep clicking "Generate new seed" near the top right to view example drops


### Running Multiple Clients
We've added two default run configurations for IntelliJ IDEA, "1. Run Client" and "2. Run Second Client". 
Running both of these at the same time will create 2 clients, with "Developer" and "Developer2" as players.
Then you can connect them to a dedicated server, or a LAN server, to test multiplayer functionalities.