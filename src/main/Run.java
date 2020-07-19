package main;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import constructor.Excel_reader;
import factory.Affectation;
import factory.Compagnon;
import factory.Instance;
import factory.Tache;

public class Run {

	public static void main(String[] args) {

		try {
			
			/***************************************************************/
			/******************** CREATION INSTANCES ***********************/
			/***************************************************************/
			ArrayList<Compagnon> workers_list = Excel_reader.createCompagnonsList();
			ArrayList<Tache> tasks_list = Excel_reader.createTasksList();
			ArrayList<Affectation> previous_couples = Excel_reader.getAffectations();
			
			Scanner sc = new Scanner(System.in);
			//System.out.println("Entrez la date de l'interruption (en centim�mes d'heures) : ");
			Instance Dassault = new Instance(workers_list, tasks_list, previous_couples, 4000/*sc.nextInt()*/);
			sc.close();
			
			/***************************************************************/
			/******************* DETERMINATION REPLAN **********************/
			/***************************************************************/
			//TODO NExt step : suivre graphe du PP
			
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("Erreur dans l'importation des donn�es des fichiers Excel !");
		}
	}

}
