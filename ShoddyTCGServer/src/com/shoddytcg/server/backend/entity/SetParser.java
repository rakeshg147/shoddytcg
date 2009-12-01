/**
 *  Copyright (C) 2009 ShoddyTCG Developer Team
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *  
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.shoddytcg.server.backend.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.shoddytcg.server.backend.entity.EnergyCard.EnergyType;
import com.shoddytcg.server.backend.entity.TrainerCard.TrainerType;
import com.shoddytcg.server.utils.FileListing;

/**
 * @author Nushio
 *
 */
public class SetParser {

	public static void main(String args[]){
		File startingDirectory= new File("res/text/");
		try {
			List<File> files = FileListing.getFileListing(startingDirectory);
			for(File file : files ){
				String filename = file.getAbsolutePath();
				if(!filename.contains(".svn")){ //Ignores all .svn files and folders. 
					BufferedReader br = null;
					try
					{
						br = new BufferedReader(new FileReader(file));
						String line;
						int counter = 0;
						ArrayList<Card> cards = new ArrayList<Card>();
						while ( (line = br.readLine()) != null )
						{					
							Card card = new Card();
							card.setUniqueName(line);
//							System.out.println("Name: "+card.getUniqueName());
							String tempType = br.readLine();
							if(tempType.contains("LV")){
//								System.out.println("Pokemon");
								card.setName(card.getUniqueName()+" "+tempType);
								PokemonCard pokemon = new PokemonCard();
								pokemon.setHP(Integer.parseInt(br.readLine().split(" ")[0]));
								String temp = br.readLine();
								temp = temp.replace("Pokemon Type: ","");
								pokemon.setPokemonType(PokemonCard.StringToPokemonType(temp));

								temp = br.readLine();
								temp = temp.replace(" Pokemon","");
								pokemon.setStage(PokemonCard.StringToStage(temp));

								if(!pokemon.getStage().equals(PokemonCard.Stage.BASIC)){
									temp = br.readLine();
									if(temp.contains("Evolves")){
										temp = temp.replace("Evolves from ","");
										pokemon.setPreStage(temp);

									}else{
										pokemon.setPreStage("");
									}
								}

								temp = br.readLine();
								temp = temp.replace("Illus. ","");
								card.setIllustrator(temp);

								boolean stop = false;
								while(!stop){
									line = br.readLine();
									if(line.contains("[Poke-Body]")){
										line = line.replace("[Poke-Body] ","");
										PokeBody pb = new PokeBody();
										pb.setName(line);
										line = br.readLine();
										pb.setText(line);
										pokemon.addPokebody(pb);
									}else if(line.contains("[Poke-Power]")){
										line = line.replace("[Poke-Power] ","");
										PokePower pp = new PokePower();
										pp.setName(line);
										line = br.readLine();
										pp.setText(line);
										pokemon.addPokepower(pp);
									}else{
										try{
											String[] attack = line.split("\\|");
											Attack atk = new Attack();
											if(attack.length>2){
												atk.setCost(attack[0]);
												atk.setName(attack[1]);
												try{atk.setDamage(attack[2]);}catch(Exception e){
													System.out.println("Check attack "+atk.getName()+" on "+card.getUniqueName());
												}
												try{
													atk.setText(attack[3]);
												}catch(Exception e){
													e.printStackTrace();
													System.out.println("Check text "+atk.getName()+" on "+card.getUniqueName());
													atk.setText("");}
												//												System.out.println(line);
												pokemon.addAttack(atk);
											}
										}catch(Exception e){
											e.printStackTrace();
										}

									}
									if(line.contains("aaaaaaack"))
										stop = true;
								}

								temp = br.readLine();
								if(temp.contains("Weakness")){
									temp = temp.replace("Weakness: ","");
									pokemon.setWeakness(temp);
								}else if(temp.contains("Resistance")){
									temp = temp.replace("Resistance: ","");
									pokemon.setResistance(temp);
								}else if(temp.contains("Retreat")){
									temp = temp.replace("Retreat Cost: ","");
									pokemon.setRetreat(temp);
								}else if(temp.contains("Collector")){
									temp = temp.replace("Collector Number: ","");
									card.setId(temp.split("/")[0]);
								}

								temp = br.readLine();
								if(temp.contains("Resistance")){
									temp = temp.replace("Resistance: ","");
									pokemon.setResistance(temp);
								}else if(temp.contains("Retreat")){
									temp = temp.replace("Retreat Cost: ","");
									pokemon.setRetreat(temp);
								}else if(temp.contains("Collector")){
									temp = temp.replace("Collector Number: ","");
									card.setId(temp.split("/")[0]);
								}

								temp = br.readLine();
								if(temp.contains("Retreat")){
									temp = temp.replace("Retreat Cost: ","");
									pokemon.setRetreat(temp);
								}else if(temp.contains("Collector")){
									temp = temp.replace("Collector Number: ","");
									card.setId(temp.split("/")[0]);
								}

								temp = br.readLine();
								if(temp.contains("Collector")){
									temp = temp.replace("Collector Number: ","");
									card.setId(temp.split("/")[0]);
								}else if(temp.contains("Rarity")){
									temp = temp.replace("Rarity: ","");
									card.setRarity(temp);
								}

								if(pokemon.getWeakness()==null)
									pokemon.setWeakness("");
								if(pokemon.getResistance()==null)
									pokemon.setResistance("");
								if(pokemon.getRetreat()==null)
									pokemon.setRetreat("0");
								
								temp = br.readLine();
								if(temp.contains("Rarity")){
									temp = temp.replace("Rarity: ","");
									card.setRarity(temp);
								}

								if(temp.equals("/**\\"))
									line = temp;
								
								card.setCardType(pokemon);
							}else if(tempType.contains("Stadium")){
//								System.out.println("Stadium");
								card.setName(card.getUniqueName());
								String temp = br.readLine();
								temp = temp.replace("Illus. ","");
								card.setIllustrator(temp);
								StadiumCard stadium = new StadiumCard();
								boolean stop = false;
								String text = "";
								while(!stop){
									temp = br.readLine();
									if(temp.contains("Collector"))
										stop=true;
									else
										text += temp+ " ";
								}
								stadium.setText(text);
								card.setCardType(stadium);
								//								System.out.println("Text: "+((StadiumCard)card.getCardType()).getText());
								if(temp.contains("Collector")){
									temp = temp.replace("Collector Number: ","");
									card.setId(temp.split("/")[0]);
								}

								temp = br.readLine();
								if(temp.contains("Rarity")){
									temp = temp.replace("Rarity: ","");
									card.setRarity(temp);
								}
							}else if(tempType.contains("Supporter")){
//								System.out.println("Supporter");
								card.setName(card.getUniqueName());
								String temp = br.readLine();
								temp = temp.replace("Illus. ","");
								card.setIllustrator(temp);
								SupporterCard supporter = new SupporterCard();
								boolean stop = false;
								String text = "";
								while(!stop){
									temp = br.readLine();
									if(temp.contains("Collector"))
										stop=true;
									else
										text += temp+ " ";
								}
								supporter.setText(text);
								card.setCardType(supporter);
								//								System.out.println("Text: "+((SupporterCard)card.getCardType()).getText());
								if(temp.contains("Collector")){
									temp = temp.replace("Collector Number: ","");
									card.setId(temp.split("/")[0]);
								}

								temp = br.readLine();
								if(temp.contains("Rarity")){
									temp = temp.replace("Rarity: ","");
									card.setRarity(temp);
								}
							}else if(tempType.contains("Trainer")){
//								System.out.println("Trainer");
								card.setName(card.getUniqueName());
								String temp = br.readLine();
								temp = temp.replace("Illus. ","");
								card.setIllustrator(temp);
								TrainerCard trainer = new TrainerCard();
								boolean stop = false;
								String text = "";
								while(!stop){
									temp = br.readLine();
									if(temp.contains("Collector"))
										stop=true;
									else
										text += temp+ " ";
								}
								trainer.setText(text);
								trainer.setTrainerType(TrainerType.TRAINER);
								card.setCardType(trainer);
								//								System.out.println("Text: "+((TrainerCard)card.getCardType()).getText());
								if(temp.contains("Collector")){
									temp = temp.replace("Collector Number: ","");
									card.setId(temp.split("/")[0]);
								}

								temp = br.readLine();
								if(temp.contains("Rarity")){
									temp = temp.replace("Rarity: ","");
									card.setRarity(temp);
								}
							}else if(tempType.contains("Pokemon Tool")){
//								System.out.println("Pokemon Tool");
								card.setName(card.getUniqueName());
								String temp = br.readLine();
								temp = temp.replace("Illus. ","");
								card.setIllustrator(temp);
								TrainerCard trainer = new TrainerCard();
								boolean stop = false;
								String text = "";
								while(!stop){
									temp = br.readLine();
									if(temp.contains("Collector"))
										stop=true;
									else
										text += temp+ " ";
								}
								trainer.setText(text);
								trainer.setTrainerType(TrainerType.TOOL);
								card.setCardType(trainer);
								//								System.out.println("Text: "+((TrainerCard)card.getCardType()).getText());
								if(temp.contains("Collector")){
									temp = temp.replace("Collector Number: ","");
									card.setId(temp.split("/")[0]);
								}

								temp = br.readLine();
								if(temp.contains("Rarity")){
									temp = temp.replace("Rarity: ","");
									card.setRarity(temp);
								}
							}else if(tempType.contains("FOSSIL")){
//								System.out.println("FOSSIL");

								card.setName(card.getUniqueName());
								TrainerCard trainer = new TrainerCard();
								String temp = br.readLine();
								trainer.setHP(temp.replace(" HP",""));
								temp = br.readLine();
								temp = temp.replace("Illus. ","");
								card.setIllustrator(temp);

								boolean stop = false;
								String text = "";
								while(!stop){
									temp = br.readLine();
									if(temp.contains("Collector"))
										stop=true;
									else
										text += temp+ " ";
								}
								trainer.setText(text);
								trainer.setTrainerType(TrainerType.POKEMON);
								card.setCardType(trainer);
								//								System.out.println("Text: "+((TrainerCard)card.getCardType()).getText());
								if(temp.contains("Collector")){
									temp = temp.replace("Collector Number: ","");
									card.setId(temp.split("/")[0]);
								}

								temp = br.readLine();
								if(temp.contains("Rarity")){
									temp = temp.replace("Rarity: ","");
									card.setRarity(temp);
								}
							}else if(tempType.contains("Special Energy")){
//								System.out.println("Special Energy");
								EnergyCard energy = new EnergyCard();
								card.setName(card.getUniqueName());
								String temp = br.readLine();
								temp = temp.replace("Illus. ","");
								card.setIllustrator(temp);

								boolean stop = false;
								String text = "";
								while(!stop){
									temp = br.readLine();
									if(temp.contains("Collector"))
										stop=true;
									else
										text += temp+ " ";
								}
								energy.setText(text);
								energy.setEnergyType(EnergyType.SPECIAL);
								energy.setProvides(EnergyCard.returnProvides("0"));
								card.setCardType(energy);
								if(temp.contains("Collector")){
									temp = temp.replace("Collector Number: ","");
									card.setId(temp.split("/")[0]);
								}

								temp = br.readLine();
								if(temp.contains("Rarity")){
									temp = temp.replace("Rarity: ","");
									card.setRarity(temp);
								}
							}
//							do{
							while(line!=null && !line.contains("/**\\")){
								line = br.readLine();
								if(line!=null)
									if(line.contains("/**\\"))
										counter++;
							}
//							}while(line!=null && !line.contains("/**\\"));
							cards.add(card);
						}
//						System.out.println("Cards total: "+cards.size());
						System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
						System.out.println("<cardset code=\"PL\">");
						for(int i=0;i<cards.size();i++){
							Card card = cards.get(i);
							System.out.println("<card>");
							System.out.println("<id>"+card.getId()+"</id>");
							System.out.println("<uniqueName>"+card.getUniqueName()+"</uniqueName>");
							System.out.println("<name>"+card.getName()+"</name>");
							System.out.println("<"+card.getCardType().getType().toString().toLowerCase()+">");
							if(card.getCardType().isTrainer()){
								System.out.println("<text>"+((TrainerCard)card.getCardType()).getText()+"</text>");
							}else if(card.getCardType().isSupporter()){
								System.out.println("<text>"+((SupporterCard)card.getCardType()).getText()+"</text>");
							}else if(card.getCardType().isStadium()){
								System.out.println("<text>"+((StadiumCard)card.getCardType()).getText()+"</text>");
							}else if(card.getCardType().isEnergy()){
								System.out.println("<type>"+((EnergyCard)card.getCardType()).getEnergyType()+"</type>");
								System.out.println("<provides>"+((EnergyCard)card.getCardType()).getProvidesText()+"</provides>");
								System.out.println("<text>"+((EnergyCard)card.getCardType()).getText()+"</text>");
							}else if(card.getCardType().isPokemon()){
								PokemonCard pokemon = (PokemonCard)card.getCardType();
								System.out.println("<hp>"+pokemon.getHP()+"</hp>");
								System.out.println("<type>"+pokemon.getPokemonTypeText()+"</type>");
								System.out.println("<stage>"+pokemon.getStageText()+"</stage>");
								if(!pokemon.getStage().equals(PokemonCard.Stage.BASIC))
									if(!pokemon.getStage().equals(PokemonCard.Stage.LVLX))
										System.out.println("<prestage>"+pokemon.getPreStage()+"</prestage>");
									else
										System.out.println("<prestage>"+card.getName()+"</prestage>");
								for(int x = 0; x<pokemon.getPokebodies().size();x++){
									PokeBody pb = pokemon.getPokebodies().get(x);
									System.out.println("<pokebody>");
									System.out.println("<name>"+pb.getName()+"</name>");
									System.out.println("<text>"+pb.getText()+"</text>");
									System.out.println("</pokebody>");
								}
								for(int x = 0; x<pokemon.getPokepowers().size();x++){
									PokePower pp = pokemon.getPokepowers().get(x);
									System.out.println("<pokepower>");
									System.out.println("<name>"+pp.getName()+"</name>");
									System.out.println("<text>"+pp.getText()+"</text>");
									System.out.println("</pokepower>");
								}
								for(int x = 0; x<pokemon.getAttacks().size();x++){
									Attack attack = pokemon.getAttacks().get(x);
									System.out.println("<attack>");
									System.out.println("<name>"+attack.getName()+"</name>");
									System.out.println("<cost>"+attack.getCost()+"</cost>");
									System.out.println("<damage>"+attack.getDamage()+"</damage>");
									System.out.println("<text>"+attack.getText()+"</text>");
									System.out.println("</attack>");
								}
								System.out.println("<weakness>"+pokemon.getWeakness()+"</weakness>");
								System.out.println("<resistance>"+pokemon.getResistance()+"</resistance>");
								System.out.println("<retreat>"+pokemon.getRetreat()+"</retreat>");
							} 
							System.out.println("</"+card.getCardType().getType().toString().toLowerCase()+">");
							System.out.println("<rarity>"+card.getRarity()+"</rarity>");
							System.out.println("<illustration>"+card.getIllustrator()+"</illustration>");
							System.out.println("</card>");
						}
						System.out.println("</cardset>");
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
}
