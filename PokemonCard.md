# Introduction #

Just like in the VideoGames, the Trading Card Game features Pokemon in their different stages, with different attacks and even varying Weakness and Resistance.

# IDs, Names and Unique Names #

Each Card needs an ID to identify it properly. The ID for each card is the Collector's Number.

The uniqueName tag is made for Deck Building purposes. Remember that other than Arceus, and Basic Energies, you can only have up to 4 copies of any given card, based on its Unique Name.

The Name tag is the Name that is actually printed on the card, along with the Level it is.

```
<id>4</id>
<uniqueName>Azelf</uniqueName>
<name>Azelf LV.50</name>
```

# Pokemon Stages #

The Pokemon Stages in the Trading Card Game are:
  * Basic
  * Stage 1
  * Stage 2
  * LV.X

When making a card, unless it's a Basic Pokemon, always place the "preStage" tag _(Also known as "Evolves from")_

# Pokemon Types #

The Trading Card Game features 10 different Pokemon Types, where as the Video versions have more.
| Code | Name | Color | Represents |
|:-----|:-----|:------|:-----------|
| G    | Grass | Green | Grass, Bug, Poison |
| R    | Fire | Red   | Fire       |
| W    | Water | Blue  | Water, Ice |
| L    | Lightning | Yellow | Electric   |
| P    | Psychic | Purple | Psychic, Ghost, Poison |
| F    | Fighting | Brown/Orange | Fighting, Rock, Ground |
| D    | Darkness | Black | Dark       |
| M    | Metal | Silver | Steel      |
| C    | Colorless | White/Light Grey | Normal, Flying, Dragon |

# Powers, Bodies, Items, Attacks #

Pokemon have varied Attacks, Pokemon Powers, PokeBodies and some, even Items.

```
<pokepower>
	<name>DarkPalm</name>
	<text>Once during your turn (before your attack), if your opponent has 4 or more
	Benched Pokémon, you may choose 1 of them and shuffle that Pokémon and all cards
	attached to it into his or her deck. This power can't be used if Dusknoir is
	affected by a Special Condition.
	</text>
</pokepower>
```

```
<pokebody>
	<name>Downer Material</name>
	<text>If you have Uxie and Mespirit in play, the attack cost of each
	of your opponent's Basic Pokemon's attack is {C} more. You can't
	use more than 1 Downer Material Poke-Body each turn
	</text>
</pokebody>
```

```
<attack>
	<name>Blind Pulse</name>
	<cost>P</cost>
	<damage>10</damage>
	<text>Durin your opponent's next turn, your opponent can't attach
	any Special Energy cards from his or her hand to any of his or her
	Pokemon.
	</text>
</attack>
```

# Weakness, Resistance and Retreating #

Each Pokemon Card has a Weakness, a Resistance and a Retreat Cost, to mark it on the card. Use the following format:

```
<weakness>P+20</weakness>
<resistance>C-30</resistance>
<retreat>CCCC</retreat>
```

Note that some Pokemon have no Weakness, and no Resistance. If that's the case, you can either use a blank tag:

```
<weakness />
<resistance />
```

If the Retreat cost of a Pokemon is 0, mark it as such
```
<retreat>0</retreat>
```

# Rarity, Illustrator #
Cards have varying Rarities and Illustrators.

| Code | Rarity Name |
|:-----|:------------|
| H    | Rare Holo   |
| R    | Rare        |
| U    | Uncommon    |
| C    | Common      |
| RH-LV.X | Rare Holo LV.X |

```
<rarity>H</rarity>
<illustration>Ryo Ueda</illustration>
```

## Template ##
```
    <card>
        <id></id>
        <uniqueName></uniqueName>
        <name></name>
        <pokemon>
            <hp></hp>
            <type></type>
            <stage></stage>  
            <prestage></prestage>   
            <attack>
                <name></name>
                <cost></cost>
                <damage></damage>
                <text></text>
            </attack>
            <attack>
                <name></name>
                <cost></cost>
                <damage></damage>
                <text></text>
            </attack>
            <weakness></weakness>
            <resistance></resistance>
            <retreat></retreat>
            </pokemon>
        <rarity></rarity>
        <illustration></illustration>
    </card>

When using this template change <attack></attack> to <pokebody></pokebody> or <pokepower></pokepower> respectively.
Also delete the <cost></cost> and <damage></damage> tags since they would not be needed.
```

# All together now! #

The following corresponds to the card DP-5.
```
<card>
	<id>5</id>
	<uniqueName>Blissey</uniqueName>
	<name>Blissey LV.44</name>
	<pokemon>
		<hp>130</hp>
		<type>C</type>
		<stage>Stage 1</stage>
		<prestage>Chansey</prestage>
		<pokepower>
			<name>Kind Egg</name>
			<text>Once during your turn (before your attack), if Happiny is
			anywhere under Blisey, you may choose a number of cards in your
			hand up to the ammount of Energy attached to Blissey and put those
			cards in your hand up to the ammount of Energy attached to Blissey
			and put those cards on top of your deck. Shuffle your deck
			afterward. Then, draw that many cards. This power can't be used if
			Blissey is affected by a Special Condition</text>
		</pokepower>
		<attack>
			<name>Happy Chance</name>
			<cost>C</cost>
			<damage>20+</damage>
			<text>Does 20 damage for each Energy attached to Blissey. Before
			doing damage, you may search your discard pile for a basic Energy
			card and attach it to Blissey.</text>
		</attack>
		<weakness>F+30</weakness>
		<resistance />
		<retreat>CCC</retreat>
	</pokemon>
	<rarity>H</rarity>
	<illustration>Kagemaru Himeno</illustration>
</card>	
```