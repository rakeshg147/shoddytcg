# Introduction #

New from the Gym Leaders Expansion Set where Stadium that affected Both Player's Pokemon. Only one stadium may be in action, and placing a new stadium card automatically removes the older one.

# Creating a Stadium Card #

Just like creating TrainerCard and SupporterCard, Stadiums are quite simple to make.
The fields that matter are:
  * ID
  * Unique Name
  * Name
  * Text (Inside the Stadium tag)
  * Rarity
  * Illustration

Example:
```
<card>
	<id>114</id>
	<uniqueName>Speed Stadium</uniqueName>
	<name>Speed Stadium</name>		
	<stadium>
		<text>
		This card stays in play when you play it. Discard this card if another Stadium card comes into play. 
		If another card with the same name is in play, you can't play this card.
		Once during each player's turn, the player may flip a coin until he or she gets tails. For each heads, that player draws a card.
		</text>
	</stadium>
	<rarity>U</rarity>
	<illustration>Ryo Ueda</illustration>	
</card>
```