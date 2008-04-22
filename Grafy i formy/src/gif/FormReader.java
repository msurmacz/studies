package gif;

import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;

public class FormReader {

	public PlainStruct openPlain(File f) throws IOException, InputMismatchException {
		int n;
		Scanner s = null;
		List<int[]> list = new ArrayList<int[]>();
		PlainStruct ps;
		
		try {
			s = new Scanner(new BufferedReader(new FileReader(f)));
			n = s.nextInt();	//pierwsza linia zawiera iloœæ wspó³czyników
			s.nextLine();
			while (s.hasNextLine()) {
				Scanner s2 = new Scanner(s.nextLine());
				if (!s2.hasNext()) {
					s2.close();
					break;	//pusta linia
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
	
	public Poset openPoset(File f) {
		Poset form = null;
		
		return form;
	}
}
