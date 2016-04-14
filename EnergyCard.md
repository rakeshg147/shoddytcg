# Introduction #

Energy cards are attached to a Pokémon to enable it to attack.

There are two types of Energy cards: Basic Energy cards and Special Energy cards.

There are eight different Basic Energy types: Fighting, Fire, Grass, Lightning, Psychic, Water, Darkness and Metal.

The amount of Basic Energy cards allowed in a deck is unrestricted, while Special Energy cards follow the standard rule restricting the number of cards with the same name in a deck to four.

# Details #

Energy Cards may have no Illustrator, and may have no text. If the Energy card has a condition to the amount of energy it provides, place the lowest amount of energy provided.

Provides:
| Code | Name |
|:-----|:-----|
| G    | Grass |
| R    | Fire |
| W    | Water |
| L    | Lightning  |
| P    | Psychic  |
| F    | Fighting |
| D    | Darkness  |
| M    | Metal |
| C    | Colorless |
| `*`  | All Colors |

Basic Energy Example:
```
<card>
	<id>124</id>
	<uniqueName>Fire Energy</uniqueName>
	<name>Fire Energy</name>		
	<energy>
		<type>Basic</type>
		<provides>R</provides>
		<text />
	</energy>
	<rarity>C</rarity>
	<illustration />
</card>
```

Special Energy Example:
```
<card>
	<id>124</id>
	<uniqueName>Darkness Energy</uniqueName>
	<name>Darkness Energy</name>		
	<energy>
		<type>Special</type>
		<provides>D</provides>
		<text>
		If the Pokémon Darkness Energy is attached to attacks, the attack does 10 more damage to the Active Pokémon (before applying Weakness and Resistance). 
		Ignore this effect if the Pokémon that Darkness Energy is attached to isn't {D}. 
		Darkness Energy provides {D} Energy. (Doesn't count as a basic Energy card.)
		</text>
	</energy>
	<rarity>U</rarity>
	<illustration >Takumi Akabane</illustration>
</card>
```

Rainbow Energy:
```
<card>
	<id>121</id>
	<uniqueName>Rainbow Energy</uniqueName>
	<name>Rainbow Energy</name>		
	<energy>
		<type>Special</type>
		<provides>*</provides>
		<text>
		Attach Rainbow Energy to 1 of your Pokémon. 
		While in play, Rainbow Energy provides every type of Energy but provides only 1 Energy at a time. (Has no effect other than providing Energy.) 
		When you attach this card from your hand to 1 of your Pokémon, put 1 damage counter on that Pokémon.
		</text>
	</energy>
	<rarity>U</rarity>
	<illustration >Takumi Akabane</illustration>
</card>
```

Upper Energy:
```
<card>
	<id>102</id>
	<uniqueName>Upper Energy</uniqueName>
	<name>Upper Energy</name>		
	<energy>
		<type>Special</type>
		<provides>C</provides>
		<text>
		Upper Energy provides {C} Energy. 
		If you have more Prize cards left than your opponent and this card is attached to a Pokémon (excluding Pokémon LV.X), Upper Energy provides {C}{C} Energy.
		</text>
	</energy>
	<rarity>U</rarity>
	<illustration>Ryo Ueda</illustration>
</card>
```