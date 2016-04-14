## Introduction ##

In ShoddyTCG, Databases are written in pure, raw XML.
Due to the different card types that any given Pokemon Card can be, this guide will be split into several sections.


## The Card Set ##

The Card Set XML must be placed in the res/sets folder, with a short name, like dp.xml, where dp is the set's codename.

The following is a table with the Set's name and its Codename, ordered by release date.
| Set Name | Code |
|:---------|:-----|
| Diamond and Pearl | DP   |
| Mysterious Treasures	| MT   |
| OP Series 6 | OP6  |
| Secret Wonders | SW   |
| DP Trainer Kit 1 | TK3  |
| Great Encounters | GE   |
| OP Series 7 | OP7  |
| Majestic Dawn | MD   |
| Legends Awakened | LA   |
| OP Series 8 | OP8  |
| Stormfront | SF   |
| Platinum | PL   |
| Rising Rivals | RR   |
| OP Series 9 | OP9  |
| Supreme Victors | SV   |
| Arceus   | AR   |
| DP Promos | NDP  |

A sample cardset is provided below.
```
<?xml version="1.0" encoding="UTF-8"?>
<cardset code="DP">
	<card>
		...
	</card>
	<card>
		...
	</card>
	<card>
		...
	</card>
</cardset>
```

## The Cards ##

In PokemonTCG there's 5 different types of cards, each with different subtypes.
  * PokemonCard
  * TrainerCard
  * SupporterCard
  * StadiumCard
  * EnergyCard