import factory.Affectations;
import factory.Compagnons;
import factory.Instance;
import factory.Tasks;

import java.util.Scanner;

public class Run {

	public static void main(String[] args) {

		try {
			
			/***************************************************************/
			/******************** CREATION INSTANCES ***********************/
			/***************************************************************/
			Compagnons workers = new Compagnons().initialize();
			Tasks tasks = new Tasks().initialize();
			Affectations previousCouples = new Affectations().initialize();
			
			Scanner sc = new Scanner(System.in);
			//System.out.println("Entrez la date de l'interruption (en centim�mes d'heures) : ");
			Instance Dassault = new Instance(workers, tasks, previousCouples, 4000/*sc.nextInt()*/);
			sc.close();

			tasks.printAllTasksFollowing("102");
			System.out.println("Done");


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
