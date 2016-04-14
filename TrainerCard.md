# Introduction #

Trainer Cards perform various functions to affect the game. Some can remove damage counters from Pokémon, remove energy from the opposing Pokémon, or revive Pokémon that have been knocked out. Before the Diamond & Pearl expansion, all cards that were not Pokémon or Energy were considered Trainer cards.

# Creating a Trainer Card #

Trainer Cards
  * ID
  * Unique Name
  * Name
  * Type (Inside the Trainer tag) _(This tag can be ignored if the card is a regular trainer)_
  * Text (Inside the Trainer tag)
  * Rarity
  * Illustration

| Type | Card Category |
|:-----|:--------------|
| (Blank) | Normal Trainer |
| Tool | Pokemon Tool  |
| TM   | Technical Machine |
| Pokemon | Pokemon Cards |

Pokemon Trainers are reserved for cards like Clefairy Doll and Fossils like Old Amber and Mysterious Fossil.

Trainer Example:
```
<card>
	<id>105</id>
	<uniqueName>Double Full Heal</uniqueName>
	<name>Double Full Heal</name>		
	<trainer>
		<text>Remove all Special Conditions from each of your Active Pokémon.</text>
	</trainer>
	<rarity>U</rarity>
	<illustration>Ryo Ueda</illustration>	
</card>
```

Trainer Tool Example:
```
<card>
	<id>99</id>
	<uniqueName>Leftovers</uniqueName>
	<name>Leftovers</name>		
	<trainer>
		<type>Tool</type>
		<text>
		Attach Leftovers to 1 of your Pokémon that doesn't already have a Pokémon Tool attached to it. 
		If that Pokémon is Knocked Out, discard this card.
		If the Pokémon Leftovers is attached to is your Active Pokémon at the end of your turn, remove 1 damage counter from that Pokémon.
		</text>
	</supporter>
	<rarity>U</rarity>
	<illustration>Daisuke Ito</illustration>	
</card>	
```