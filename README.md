# PROIECT-PAO-234-PUTINEANU-BOGDAN
## Idea: 
Hunger games simulator (inspired from the movie/books). A number of players are put into a large arena and must fight to the death.
 ## Classes:
Game = instance of a game
Player = participant to a game <br>
Item = object the participant can use to improve his chances <br>
Weapon = item type used for fighing <br>
RangedWeapon <br>
MeleeWeapon <br>
UtilityItem = item used for survival purposes (e.g.: lighter, food, etc.) <br>
Map = the entire area in which the players play the game <br>
MapSection = a single square of the map <br>

## Operations:
setters, getters for all <br>
players: add, remove items, verify isAlive <br>
map: initialize map sections randomly <br>
gameService: simulate player movement, simulate player encounter/combat, display winner <br>
