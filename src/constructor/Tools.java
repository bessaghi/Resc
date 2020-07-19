package constructor;

import java.util.ArrayList;

public class Tools {
	
	/**
	 * Fais la liste des zones dans l'instance pour les compter
	 * @param toSplit
	 * @return
	 */
	public static ArrayList<String> StringSplitter(String toSplit) {
		
		ArrayList<String> result = new ArrayList<String>();

		String str = toSplit.replace(",", "");
		str = str.replace("{","");
		str = str.replace("}","");
		str = str.replace("\"\"",";");
		str = str.replace("\"","");
		
		String[] list = str.split(";");
		
		for(int i = 0; i < list.length; i++)
			if(!result.contains(list[i]))
				result.add(list[i]);
		
		return result;
	}
	
}
