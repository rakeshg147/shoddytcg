# Introduction #

Supporter Cards were introduced in the Expedition Base Set. They are cards themed on Pokémon characters. They are substantially more powerful than Trainer cards, but only one can be played per turn (as opposed to normal Trainers, which have no limit).

# Creating a Supporter Card #

Just like creating StadiumCard and TrainerCard, Supporter Cards are quite simple to make.
The fields that matter are:
  * ID
  * Unique Name
  * Name
  * Text (Inside the Supporter tag)
  * Rarity
  * Illustration

Example:
```
<card>
	<id>113</id>
	<uniqueName>Rival</uniqueName>
	<name>Rival</name>		
	<supporter>
		<text>
		You can play only one Supporter card each turn. 
		When you play this card, put it next to your Active Pokémon. When your turn ends, discard this card.
		Reveal the top 5 cards of your deck. Your opponent chooses 3 of those cards. Put those cards into your hand and put other 2 cards on top of your deck. Shuffle your deck afterward.
		</text>
	</supporter>
	<rarity>U</rarity>
	<illustration>Ken Sugimori</illustration>	
</card>	
```