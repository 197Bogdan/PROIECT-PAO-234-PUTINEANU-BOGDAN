# PROIECT-PAO-234-PUTINEANU-BOGDAN

Classes:
Player = participant to a game instance
Item = object the participant can use to improve his chances
Weapon = item type used for fighing
RangedWeapon
MeleeWeapon
UtilityItem = item used for survival purposes (e.g.: lighter, food, etc.)
Map = the entire area in which the players play the game
MapSection = a single square of the map 

Operations:
setters, getters for all
players: add, remove items, verify isAlive
map: initialize map sections randomly
mapService: simulate player movement, simulate player encounter/combat, display winner
