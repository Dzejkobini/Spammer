package spammer.manager.registration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class NameGenerator {
	
	/**
	 * Konstruktor
	 */
	public NameGenerator()
	{
		//NOP
	}
	
	/**
	 * Generowanie losowego imienia 
	 * @return {String} randomName
	 * @throws IOException
	 */
	public String generateRandomName() throws IOException
	{
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("NamesList.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		List<String> lines = new ArrayList<String>();
		String line;
		Random r = new Random();
		StringBuilder randomName = new StringBuilder();
		
		while((line = br.readLine()) != null)
		{
			lines.add(line);
		}

		for(int i = 0; i < 2; i++)
		{
			randomName.append(" "+lines.get(r.nextInt(lines.size())));
		}
		return randomName.toString().trim();
	}
	
}
