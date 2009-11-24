package com.shoddytcg.server.backend.entity;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Nushio
 *
 */
public class CardReader {

	public static void main(String args[]){
		try{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File("res/sets/dp.xml"));
			doc.getDocumentElement().normalize();

			NodeList cardsetNodeList = doc.getElementsByTagName("cardset");
			for (int s = 0; s < cardsetNodeList.getLength(); s++) {
				Node cardsetNode = cardsetNodeList.item(s);
				if (cardsetNode.getNodeType() == Node.ELEMENT_NODE) {
					for (int i = 0; i < cardsetNodeList.getLength(); i++) {
						NodeList cardNodeList = ((Element)cardsetNodeList.item(i)).getElementsByTagName("card");
						for (int j = 0; j < cardNodeList.getLength(); j++) {
							//						for (int j = 0; j < 1; j++) {
							// Set ID
							NodeList idNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("id");
							System.out.println(idNodeList.item(0).getChildNodes().item(0).getNodeValue());

							// Set UniqueName
							NodeList uniqueNameNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("uniqueName");
							System.out.println(uniqueNameNodeList.item(0).getChildNodes().item(0).getNodeValue());

							// Set Name
							NodeList nameNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("name");
							System.out.println(nameNodeList.item(0).getChildNodes().item(0).getNodeValue());

							// Check If Its a Pokemon
							try{
								NodeList pokemonNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("pokemon");
								Element pokemonElement = (Element) pokemonNodeList.item(0);

								// HP
								NodeList hpList = pokemonElement.getElementsByTagName("hp");
								System.out.println(hpList.item(0).getChildNodes().item(0).getNodeValue());

								// Type
								NodeList typeList = pokemonElement.getElementsByTagName("type");
								System.out.println(typeList.item(0).getChildNodes().item(0).getNodeValue());

								// Stage
								NodeList stageList = pokemonElement.getElementsByTagName("stage");
								System.out.println(stageList.item(0).getChildNodes().item(0).getNodeValue());

								// PreStage
								try{
									NodeList preStageList = pokemonElement.getElementsByTagName("prestage");
									System.out.println(preStageList.item(0).getChildNodes().item(0).getNodeValue());
								}catch(Exception e){}
								
								// Poke Powers
								try{
									NodeList pokepowerNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("pokepower");
									for(int pp=0;pp<pokepowerNodeList.getLength();pp++){
										Element powerElement = (Element) pokepowerNodeList.item(pp);

										// Attack Name
										NodeList nameList = powerElement.getElementsByTagName("name");
										System.out.println(nameList.item(0).getChildNodes().item(0).getNodeValue());

										// Attack Text
										NodeList textList = powerElement.getElementsByTagName("text");
										System.out.println(textList.item(0).getChildNodes().item(0).getNodeValue().replaceAll("	","").replaceAll("\n",""));
									}
								}catch(Exception ex){}

								// Attacks
								try{
									NodeList attackNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("attack");
									for(int atk=0;atk<attackNodeList.getLength();atk++){
										Element attackElement = (Element) attackNodeList.item(atk);
										// Attack Name
										NodeList nameList = attackElement.getElementsByTagName("name");
										System.out.println(nameList.item(0).getChildNodes().item(0).getNodeValue());

										// Attack Cost
										NodeList costList = attackElement.getElementsByTagName("cost");
										System.out.println(costList.item(0).getChildNodes().item(0).getNodeValue());

										// Attack Damage
										NodeList damageList = attackElement.getElementsByTagName("damage");
										System.out.println(damageList.item(0).getChildNodes().item(0).getNodeValue());

										// Attack Text
										NodeList textList = attackElement.getElementsByTagName("text");
										System.out.println(textList.item(0).getChildNodes().item(0).getNodeValue().replaceAll("	","").replaceAll("\n",""));
									}
								}catch(Exception ex){}

								// Weakness
								try{
									NodeList weakList = pokemonElement.getElementsByTagName("weakness");
									System.out.println(weakList.item(0).getChildNodes().item(0).getNodeValue());
								}catch(Exception e){}

								// Resistance
								try{
									NodeList resistList = pokemonElement.getElementsByTagName("resistance");
									System.out.println(resistList.item(0).getChildNodes().item(0).getNodeValue());
								}catch(Exception e){}
								
								// Retreat
								NodeList retreatList = pokemonElement.getElementsByTagName("retreat");
								System.out.println(retreatList.item(0).getChildNodes().item(0).getNodeValue());

							}catch(Exception e){}//Not a Pokemon

							// Check If Its a Trainer
							try{
								NodeList trainerNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("trainer");
								Element textElement = (Element) trainerNodeList.item(0);
								NodeList textList = textElement.getElementsByTagName("text");
								System.out.println(textList.item(0).getChildNodes().item(0).getNodeValue().replaceAll("	","").replaceAll("\n",""));

							}catch(Exception e){}//Not a trainer

							// Check If Its a Supporter
							try{
								NodeList supporterNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("supporter");
								Element textElement = (Element) supporterNodeList.item(0);
								NodeList textList = textElement.getElementsByTagName("text");
								System.out.println(textList.item(0).getChildNodes().item(0).getNodeValue().replaceAll("	","").replaceAll("\n",""));

							}catch(Exception e){}//Not a supporter

							// Check If Its a Stadium
							try{
								NodeList stadiumNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("stadium");
								Element textElement = (Element) stadiumNodeList.item(0);
								NodeList textList = textElement.getElementsByTagName("text");
								System.out.println(textList.item(0).getChildNodes().item(0).getNodeValue().replaceAll("	","").replaceAll("\n",""));

							}catch(Exception e){}//Not a stadium

							// Set Rarity
							NodeList rarityNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("rarity");
							System.out.println(rarityNodeList.item(0).getChildNodes().item(0).getNodeValue());

							// Set Illustrator
							NodeList illusNodeList = ((Element)cardNodeList.item(j)).getElementsByTagName("illustration");
							System.out.println(illusNodeList.item(0).getChildNodes().item(0).getNodeValue());
						}
					}
				}
			}
		} catch (SAXParseException err) {
			System.out.println("** Error de Parseo, linea "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());
		} catch (SAXException e) {
			Exception x = e.getException();
			(x == null ? (Exception) e : x).printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
