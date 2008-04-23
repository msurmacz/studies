package gif;

import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;

public class FormReader {
	private Scanner s;

	/**
	 * @param s
	 */
	public FormReader() {
		this.s = null;
	}

	public PlainStruct openPlain(File f) throws IOException, InputMismatchException {
		int n;
		List<int[]> list = new ArrayList<int[]>();
		PlainStruct ps;
		
		try {
			s = new Scanner(new BufferedReader(new FileReader(f)));
			n = s.nextInt();	//pierwsza linia zawiera iloœæ wspó³czyników
			s.nextLine();
			while (s.hasNextLine()) {
				Scanner s2 = new Scanner(s.nextLine());
				if (!s2.hasNext()) {	//pusta linia
					s2.close();
					break;
				}
				while(s2.hasNext()) {	//Wczytywanie do wektora
					int[] a = new int[3];
					for (int i = 0; i < a.length; i++)
						a[i] = s2.nextInt();
					list.add(a);
				}
			}
			ps = new PlainStruct(n, list);
		} finally {
			if (s != null)
				s.close();
		}
		return ps;
	}
	
	public Poset openPoset(File f)  throws IOException, InputMismatchException {
		Poset poset = null;
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		List<Integer> subList;
		
		try {
			s = new Scanner(new BufferedReader(new FileReader(f)));
			
			while (s.hasNextLine()) {
				Scanner s2 = new Scanner(s.nextLine());
				s2.useDelimiter("[\\|,]");	// separatory | albo ,
				if (!s2.hasNext()) {	//pusta linia oznacza koniec danych
					s2.close();
					break;
				}
				subList = new ArrayList<Integer>();	//wczytuje liste
				while(s2.hasNext())
					subList.add(s2.nextInt());
				list.add(subList);
			}
			
			poset = new Poset(list);
		} finally {
			if (s != null)
				s.close();
		}
		
		return poset;
	}
}
