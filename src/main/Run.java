import data.Affectation;
import data.Compagnon;
import data.Task;
import factory.Affectations;
import factory.Compagnons;
import factory.Instance;
import factory.Tasks;

import java.util.ArrayList;
import java.util.Scanner;

public class Run {

	public static void main(String[] args) {

		try {
			
			/***************************************************************/
			/******************** CREATION INSTANCES ***********************/
			/***************************************************************/
			ArrayList<Compagnon> workers_list = new Compagnons().initialize();
			ArrayList<Task> tasks_list = new Tasks().initialize();
			ArrayList<Affectation> previous_couples = new Affectations().initialize();
			
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
