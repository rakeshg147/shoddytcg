# Introduction #

Every Pokemon deck must have exactly 60 cards under 'normal' tournament rules.
Except for Basic Energies and Arceus, a player can only have up to 4 copies of any given card, based on its name. (This means 4 Pikachu, regardless of the set, although you can still add Lt. Surge's Pikachu or Flying Pikachu, because their card name is different).

This guide should cover the basics of making a .deck file, which ShoddyTCG reads as your Pokemon deck.


# Details #

```
// Deck file for ShoddyTCG
// You can place comments using Double Slash
// You don't really need to place any, but it serves as a comment. 

// Pokemon
    3 [DP] Dialga LV.68 // You can even write comments here, after the card. 
    4 [DP] Dusknoir LV.42
    1 [DP] Electivire LV.46
    1 DP-6 // CODE-Number works too. 
    1 DP-8 Dialga LV.68 //This would load Magnezone and not Dialga!
    1 [DP] Empoleon LV.42
    1 [DP] Infernape LV.40
    3 [MT] Aggron LV.49
    4 [MT] Alakazam LV.47 
    3 [MT] Azelf LV.50 
    4 [SW] Blastoise LV.52
    3 [SW] Charizard LV.55 

// Trainers
    4 [DP] Potion 
    4 [DP] Pokédex 
    2 [DP] Poké Ball 

// Energies
    11 [DP] Water Energy 
    11 [DP] Psychic Energy 
```