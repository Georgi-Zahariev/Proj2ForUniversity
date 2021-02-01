import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Star implements Comparable<Star> {
	private String name;
	private double distance;
	private Byte qualif;
	private double weight;
	private String group;

	Star(Scanner inp, boolean prompt) {

		if (prompt)
			System.out.print("Name: ");
		name = inp.nextLine();
		if (prompt)
			System.out.print("Distance : ");
		distance = inp.nextDouble();
		if (prompt)
			System.out.print("Star qualification : ");
		qualif = inp.nextByte();
		if (prompt)
			System.out.print("Star weight: ");
		weight = inp.nextDouble();
		if (prompt)
			System.out.print("Stargroup that the star is in: ");
		group = inp.next();

		inp.nextLine();
	}

	public double getDistance() {
		return distance;
	}

	public String getName() {
		return name;
	}

	public Byte getQualif() {
		return qualif;
	}

	public double getWeight() {
		return weight;
	}

	public String getGroup() {
		return group;
	}

	private String Qualification(byte a) {

		switch (a) {
		case 1:
			return "Hyper giants";
		case 2:
			return "Superb giants";
		case 3:
			return "Bright giants";
		case 4:
			return "Giants";
		case 5:
			return "Sub giants";
		case 6:
			return "Dwarfs";
		case 7:
			return "Sub dwarls";
		case 8:
			return "Red dwarfs";
		case 9:
			return "Brown dwarfs";

		default:
			return "Not valid qualification ";

		}
	}

	@Override
	public String toString() {
		return name + ", " + distance + " s.y." + ", " + Qualification(qualif) + ", " + weight + " s.m." + ", " + group;
	}

	public int compareTo(Star s) {
		if (this.getDistance() < s.getDistance())
			return -1;
		if (this.getDistance() > s.getDistance())
			return 1;
		return 0;
	}

}

class Cmp1 implements Comparator<Star> {
	@Override
	public int compare(Star a, Star b) {
		int c = a.getGroup().compareTo(b.getGroup());
		if (c > 0)
			return 1;
		if (c < 0)
			return -1;
		else {
			if (a.getWeight() < b.getWeight())
				return 1;
			if (a.getWeight() > b.getWeight())
				return -1;
			return 0;
		}

	}
}

class Cmp2 implements Comparator<Star> {
	@Override
	public int compare(Star a, Star b) {
		return a.getGroup().compareTo(b.getGroup());
	}

}

public class Uni2019 {
	static int N;
	static Scanner inp = null;
	static ArrayList<Star> stars = new ArrayList<Star>();

	static void problem1(boolean prompt) {
		for (int i = 0; i < N; i++) {
			stars.add(new Star(inp, prompt));
		}
	}

	static void problem2() {
		Collections.sort(stars);
		for (Star s : stars)
			System.out.println(s);
	}

	static void problem3() {
		stars.sort(new Cmp1());

		for (Star s : stars)
			System.out.println(s);
	}

	static void problem4() {
		stars.sort(new Cmp2());

		String g = stars.get(0).getGroup();
		double sum = 0;
		int s = 0;
		for (Star c : stars) {
			if (!c.getGroup().equals(g)) {
				if (s > 0)
					System.out.println(g + " - " + String.format("%.2f",average(sum, s)));
				s = 1;
				sum = c.getWeight();
				g = c.getGroup();
			} else {
				s++;
				sum += c.getWeight();
			}
		}
		if (s > 0)
			System.out.println(g + " - " + String.format("%.2f",average(sum, s)));
	}

	static double average(double a, int n) {
		return a / n;
	}

	public static void main(String[] args) {
		final boolean prompt = false;
		File f = null;
		if (prompt) {
			inp = new Scanner(System.in);
			do {
				System.out.print("Star count (1 to 2000): ");
				N = inp.nextInt();
			} while (N < 1 || N > 2000);
		} else {
			f = new File("Data.txt");
			try {
				inp = new Scanner(f);
			} catch (Exception e) {
				System.out.println("File Data.txt not found");
				return;
			}
			N = inp.nextInt();
		}
		inp.nextLine();
		problem1(prompt);
		problem2();
		System.out.println();
		problem3();
		System.out.println();
		problem4();

	}
}