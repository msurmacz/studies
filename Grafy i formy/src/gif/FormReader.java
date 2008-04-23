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
			//http://java.sun.com/javase/6/docs/api/java/util/regex/Pattern.html#split(java.lang.CharSequence)
			//http://www.unitedti.org/lofiversion/index.php/t6285.html
			s.useDelimiter(java.util.regex.Pattern.compile("\\|"));
			while (s.hasNextLine()) {
				Scanner s2 = new Scanner(s.nextLine());
				if (!s2.hasNext()) {	//pusta linia
					s2.close();
					break;
				}
				while(s2.hasNext()) {	//Wczytywanie
					System.out.println(s2.next());
//					subList = new ArrayList<Integer>();
//					int[] a = new int[3];
//					for (int i = 0; i < a.length; i++)
//						a[i] = s2.nextInt();
//					list.add(a);
				}
			}
			
			poset = new Poset(list);
		} finally {
			if (s != null)
				s.close();
		}
		
		return poset;
	}
}
