package com.shoddytcg.server.backend.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.shoddytcg.server.backend.entity.TrainerCard.Type;

/**
 * @author Nushio
 *
 *
 * Recursive filelisting code by:   
 * @author javapractices.com
 * @author Alex Wong
 * @author anonymous user
 */
public class CardReader {

	public static void main(String args[]){
		try{
			File startingDirectory= new File("res/sets/");
			List<File> files = CardReader.getFileListing(startingDirectory);

			for(File file : files ){
				String filename = file.getAbsolutePath();
				if(!filename.contains(".svn")){
					DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
					Document doc = docBuilder.parse(file);
					doc.getDocumentElement().normalize();
					System.out.println("Reading cardset: "+file.getName());
					NodeList cardsetNodeList = doc.getElementsByTagName("cardset");
					Element cardsetElement = (Element) cardsetNodeList.item(0);
					String code = cardsetElement.getAttribute("code");
					int cardcount = 0;
					for (int s = 0; s < cardsetNodeList.getLength(); s++) {
						Node cardsetNode = cardsetNodeList.item(s);
						if (cardsetNode.getNodeType() == Node.ELEMENT_NODE) {
							for (int i = 0; i < cardsetNodeList.getLength(); i++) {
								NodeList cardNodeList = ((Element)cardsetNodeList.item(i)).getElementsByTagName("card");
								for (int j = 0; j < cardNodeList.getLength(); j++) {
									try{
										Card card;
										// Set ID
										NodeList idNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("id");
										card = new Card(code+"-"+idNodeList.item(0).getChildNodes().item(0).getNodeValue());

										// Set UniqueName
										try{
											NodeList uniqueNameNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("uniqueName");
											card.setUniqueName(uniqueNameNodeList.item(0).getChildNodes().item(0).getNodeValue());
										}catch(Exception e){
											System.out.println(card.getId()+" UniqueName tag is wrong or missing!");
										}

										// Set Name
										try{
											NodeList nameNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("name");
											card.setName(nameNodeList.item(0).getChildNodes().item(0).getNodeValue());
										}catch(Exception e){
											System.out.println(card.getId()+" Name tag is wrong or missing!");
										}

										boolean cardDefined = false;
										// Check If Its a Pokemon
										try{
											NodeList pokemonNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("pokemon");
											Element pokemonElement = (Element) pokemonNodeList.item(0);
											if(pokemonNodeList.getLength()==1){
												PokemonCard pokemon = new PokemonCard();

												// HP
												try{
													NodeList hpList = pokemonElement.getElementsByTagName("hp");
													pokemon.setHP(Integer.parseInt(hpList.item(0).getChildNodes().item(0).getNodeValue()));
												}catch(Exception e){
													System.out.println(card.getId()+" Pokemon HP is wrong or missing!");
												}

												// Type
												try{
													NodeList typeList = pokemonElement.getElementsByTagName("type");
													pokemon.setType(PokemonCard.returnType(typeList.item(0).getChildNodes().item(0).getNodeValue()));
												}catch(Exception e){
													System.out.println(card.getId()+" Pokemon Type is wrong or missing!");
												}

												// Stage
												try{
													NodeList stageList = pokemonElement.getElementsByTagName("stage");
													pokemon.setStage(PokemonCard.returnStage(stageList.item(0).getChildNodes().item(0).getNodeValue()));
												}catch(Exception e){
													System.out.println(card.getId()+" Pokemon Stage is wrong or missing!");
												}

												// PreStage
												if(!pokemon.getStage().equals(PokemonCard.Stage.BASIC)){
													try{
														NodeList preStageList = pokemonElement.getElementsByTagName("prestage");
														pokemon.setPreStage(preStageList.item(0).getChildNodes().item(0).getNodeValue());
													}catch(Exception e){
														System.out.println(card.getId()+" Pokemon Stage is wrong or missing!");
													}
												}else{
													pokemon.setPreStage("");
												}
												
												// Held Items
												try{
													NodeList itemNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("item");
													for(int hi=0;hi<itemNodeList.getLength();hi++){
														Item item = new Item();
														Element itemElement = (Element) itemNodeList.item(hi);

														// Held Item Name
														try{
															NodeList nameList = itemElement.getElementsByTagName("name");
															item.setName(nameList.item(0).getChildNodes().item(0).getNodeValue());
														}catch(Exception e){
															System.out.println(card.getName()+" Item Name is wrong or missing!");
														}

														// Held Item Text
														try{
															NodeList textList = itemElement.getElementsByTagName("text");
															item.setText(textList.item(0).getChildNodes().item(0).getNodeValue().replaceAll("	","").replaceAll("\n",""));
														}catch(Exception e){
															System.out.println(card.getName()+" Pokepower Text is wrong or missing!");
														}
														pokemon.setItem(item);
													}
												}catch(Exception ex){}
												
												// Poke Bodies
												try{
													NodeList pokebodyNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("pokebody");
													for(int pb=0;pb<pokebodyNodeList.getLength();pb++){
														PokeBody pokebody = new PokeBody();
														Element powerElement = (Element) pokebodyNodeList.item(pb);

														// PokeBody Name
														try{
															NodeList nameList = powerElement.getElementsByTagName("name");
															pokebody.setName(nameList.item(0).getChildNodes().item(0).getNodeValue());
														}catch(Exception e){
															System.out.println(card.getName()+" Pokepower Name is wrong or missing!");
														}

														// PokeBody Text
														try{
															NodeList textList = powerElement.getElementsByTagName("text");
															pokebody.setText(textList.item(0).getChildNodes().item(0).getNodeValue().replaceAll("	","").replaceAll("\n",""));
														}catch(Exception e){
															System.out.println(card.getName()+" Pokepower Text is wrong or missing!");
														}
														pokemon.addPokebody(pokebody);
													}
												}catch(Exception ex){}
												
												// Poke Powers
												try{
													NodeList pokepowerNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("pokepower");
													for(int pp=0;pp<pokepowerNodeList.getLength();pp++){
														PokePower pokepower = new PokePower();
														Element powerElement = (Element) pokepowerNodeList.item(pp);

														// PokePower Name
														try{
															NodeList nameList = powerElement.getElementsByTagName("name");
															pokepower.setName(nameList.item(0).getChildNodes().item(0).getNodeValue());
														}catch(Exception e){
															System.out.println(card.getName()+" Pokepower Name is wrong or missing!");
														}

														// PokePower Text
														try{
															NodeList textList = powerElement.getElementsByTagName("text");
															pokepower.setText(textList.item(0).getChildNodes().item(0).getNodeValue().replaceAll("	","").replaceAll("\n",""));
														}catch(Exception e){
															System.out.println(card.getName()+" Pokepower Text is wrong or missing!");
														}
														pokemon.addPokepower(pokepower);
													}
												}catch(Exception ex){}

												// Attacks
												try{
													NodeList attackNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("attack");
													for(int atk=0;atk<attackNodeList.getLength();atk++){
														Attack attack = new Attack();
														Element attackElement = (Element) attackNodeList.item(atk);
														// Attack Name
														try{
															NodeList nameList = attackElement.getElementsByTagName("name");
															attack.setName(nameList.item(0).getChildNodes().item(0).getNodeValue());
														}catch(Exception e){
															System.out.println(card.getName()+" Attack Name is wrong or missing!");
														}

														// Attack Cost
														try{
															NodeList costList = attackElement.getElementsByTagName("cost");
															attack.setCost(costList.item(0).getChildNodes().item(0).getNodeValue());
														}catch(Exception e){
															System.out.println(card.getName()+" Attack Cost is wrong or missing!");
														}

														// Attack Damage
														try{
															NodeList damageList = attackElement.getElementsByTagName("damage");
															attack.setDamage(damageList.item(0).getChildNodes().item(0).getNodeValue());
														}catch(Exception e){
															attack.setDamage("--");
														}

														// Attack Text
														try{
															NodeList textList = attackElement.getElementsByTagName("text");
															attack.setText(textList.item(0).getChildNodes().item(0).getNodeValue().replaceAll("	","").replaceAll("\n",""));
														}catch(Exception e){
															attack.setText("");
														}
														pokemon.addAttack(attack);
													}
												}catch(Exception ex){}

												// Weakness
												try{
													NodeList weakList = pokemonElement.getElementsByTagName("weakness");
													pokemon.setWeakness(weakList.item(0).getChildNodes().item(0).getNodeValue());
												}catch(Exception e){
													pokemon.setWeakness("0");
												}

												// Resistance
												try{
													NodeList resistList = pokemonElement.getElementsByTagName("resistance");
													pokemon.setResistance(resistList.item(0).getChildNodes().item(0).getNodeValue());
												}catch(Exception e){
													pokemon.setResistance("0");
												}

												// Retreat
												try{
													NodeList retreatList = pokemonElement.getElementsByTagName("retreat");
													pokemon.setRetreat(retreatList.item(0).getChildNodes().item(0).getNodeValue());
												}catch(Exception e){
													System.out.println(card.getName()+" retreat is wrong or missing!");
												}
											}//else, not a pokemon
										}catch(Exception e){

										}//Not a Pokemon

										// Check If Its a Trainer
										try{
											NodeList trainerNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("trainer");
											if(trainerNodeList.getLength()==1){
												TrainerCard trainer = new TrainerCard();
												Element trainerElement = (Element) trainerNodeList.item(0);

												//Read Trainer Type
												try{
													NodeList typeList = trainerElement.getElementsByTagName("type");
													trainer.setType(TrainerCard.returnType(typeList.item(0).getChildNodes().item(0).getNodeValue().replaceAll("	","").replaceAll("\n","")));
												}catch(Exception e){
													trainer.setType(Type.TRAINER);
												}
												
												//Read Trainer Text
												
												try{
													NodeList textList = trainerElement.getElementsByTagName("text");
													trainer.setText(textList.item(0).getChildNodes().item(0).getNodeValue().replaceAll("	","").replaceAll("\n",""));
												}catch(Exception e){
													System.out.println(card.getName()+" has missing or invalid text!");
												}
												card.setCardType(trainer);

												if(!cardDefined){
													cardDefined=true;
												}else{
													System.out.println("Card "+card.getId()+" cannot be a Pokemon and a Trainer!");
												}
											}//else not a trainer
										}catch(Exception e){}//Not a trainer

										// Check If Its a Supporter
										try{
											NodeList supporterNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("supporter");
											if(supporterNodeList.getLength()==1){
												Element textElement = (Element) supporterNodeList.item(0);
												SupporterCard supporter = new SupporterCard();

												//Read Supporter Text
												try{
													NodeList textList = textElement.getElementsByTagName("text");
													supporter.setText(textList.item(0).getChildNodes().item(0).getNodeValue().replaceAll("	","").replaceAll("\n",""));
												}catch(Exception e){
													System.out.println(card.getName()+" has missing or invalid text!");
												}

												card.setCardType(supporter);
												if(!cardDefined){
													cardDefined=true;
												}else{
													System.out.println("Card "+card.getName()+" cannot be a Pokemon and/or a Trainer and/or a supporter!!");
												}
											}//else, not a supporter
										}catch(Exception e){}//Not a supporter

										// Check If Its a Stadium
										try{
											NodeList stadiumNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("stadium");
											if(stadiumNodeList.getLength()==1){
												Element textElement = (Element) stadiumNodeList.item(0);
												StadiumCard stadium = new StadiumCard();

												//Read Stadium Text
												try{
													NodeList textList = textElement.getElementsByTagName("text");
													stadium.setText(textList.item(0).getChildNodes().item(0).getNodeValue().replaceAll("	","").replaceAll("\n",""));
												}catch(Exception e){
													System.out.println(card.getName()+" has missing or invalid text!");
												}
												card.setCardType(stadium);
												if(!cardDefined){
													cardDefined=true;
												}else{
													System.out.println("Card "+card.getName()+" cannot be a Pokemon and/or a Trainer and/or a supporter!!");
												}
											}
										}catch(Exception e){}//Not a stadium

										// Check If Its an Energy
										try{
											NodeList energyNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("energy");
											if(energyNodeList.getLength()==1){
												EnergyCard energy = new EnergyCard();
												Element energyElement = (Element) energyNodeList.item(0);

												//Read Energy Type (Basic or Special)
												try{
													NodeList typeList = energyElement.getElementsByTagName("type");
													energy.setType(EnergyCard.returnType(typeList.item(0).getChildNodes().item(0).getNodeValue().replaceAll("	","").replaceAll("\n","")));
												}catch(Exception e){
													energy.setType(EnergyCard.Type.BASIC);
													System.out.println(card.getName()+" has missing or invalid Energy Type!!");
												}
												
												//Read Energy Provides Text
												try{
													NodeList providesList = energyElement.getElementsByTagName("provides");
													energy.setProvides(EnergyCard.returnProvides(providesList.item(0).getChildNodes().item(0).getNodeValue().replaceAll("	","").replaceAll("\n","")));
												}catch(Exception e){
													System.out.println(card.getName()+" has missing or invalid energy provides!");
												}
												
												//Read Energy Text
												try{
													NodeList textList = energyElement.getElementsByTagName("text");
													energy.setText(textList.item(0).getChildNodes().item(0).getNodeValue().replaceAll("	","").replaceAll("\n",""));
												}catch(Exception e){
													energy.setText("");
												}
												card.setCardType(energy);

												if(!cardDefined){
													cardDefined=true;
												}else{
													System.out.println("Card "+card.getId()+" cannot be a Pokemon and a Trainer!");
												}
											}//else not a trainer
										}catch(Exception e){}//Not a trainer
										// Set Rarity
										try{
											NodeList rarityNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("rarity");
											card.setRarity(rarityNodeList.item(0).getChildNodes().item(0).getNodeValue());
										}catch(Exception e){
											System.out.println(card.getName()+" Rarity is wrong or missing!");
										}
										// Set Illustrator
										try{
											NodeList illusNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("illustration");
											card.setIllustrator(illusNodeList.item(0).getChildNodes().item(0).getNodeValue());
										}catch(Exception e){
											System.out.println(card.getName()+" Illustrator is wrong or missing!");
										}
										cardcount++;
									}catch(Exception e){
										System.out.println("Card "+(j+1)+" card could not be read");
									}
								}
							}
						}
					}
					System.out.println("Done\nCards Read: "+cardcount+"\n");
				}
			}
		} catch (SAXParseException err) {
			System.out.println("** Parse error, on line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());
		} catch (SAXException e) {
			Exception x = e.getException();
			(x == null ? (Exception) e : x).printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	/**
	 * Recursively walk a directory tree and return a List of all
	 * Files found; the List is sorted using File.compareTo().
	 *
	 * @param aStartingDir is a valid directory, which can be read.
	 */
	static public List<File> getFileListing(
			File aStartingDir
	) throws FileNotFoundException {
		validateDirectory(aStartingDir);
		List<File> result = getFileListingNoSort(aStartingDir);
		Collections.sort(result);
		return result;
	}
	// PRIVATE //
	static private List<File> getFileListingNoSort(
			File aStartingDir
	) throws FileNotFoundException {
		List<File> result = new ArrayList<File>();
		File[] filesAndDirs = aStartingDir.listFiles();
		List<File> filesDirs = Arrays.asList(filesAndDirs);
		for(File file : filesDirs) {
			result.add(file); //always add, even if directory
			if ( ! file.isFile() ) {
				//must be a directory
				//recursive call!
				List<File> deeperList = getFileListingNoSort(file);
				result.addAll(deeperList);
			}
		}
		return result;
	}

	/**
	 * Directory is valid if it exists, does not represent a file, and can be read.
	 */
	static private void validateDirectory (
			File aDirectory
	) throws FileNotFoundException {
		if (aDirectory == null) {
			throw new IllegalArgumentException("Directory should not be null.");
		}
		if (!aDirectory.exists()) {
			throw new FileNotFoundException("Directory does not exist: " + aDirectory);
		}
		if (!aDirectory.isDirectory()) {
			throw new IllegalArgumentException("Is not a directory: " + aDirectory);
		}
		if (!aDirectory.canRead()) {
			throw new IllegalArgumentException("Directory cannot be read: " + aDirectory);
		}
	}
}
