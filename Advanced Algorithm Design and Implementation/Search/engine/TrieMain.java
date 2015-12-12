package engine;

import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class TrieMain {
	public static void main(String[] args) {

		String file1 = "/Users/Monish/Desktop/search/robert_downey.txt";
		String file2 = "/Users/Monish/Desktop/search/gandhi.txt";
		String file3 = "/Users/Monish/Desktop/search/iron_man.txt";
		Trie dict = new Trie();

		Scanner sc2 = null;
		try {
			sc2 = new Scanner(new File(file1));
		} catch (FileNotFoundException e) {
			System.out.println("couldn't read file robert_downey.txt file"); 
		}
		while (sc2.hasNextLine()) {
			Scanner s2 = new Scanner(sc2.nextLine());
			while (s2.hasNext()) {
				String s = s2.next();
				dict.insert(s, 1);
			}
			s2.close();
		}
		
		try {
			sc2 = new Scanner(new File(file2));
		} catch (FileNotFoundException e) {
			System.out.println("couldn't read file iron_man.txt file"); 
		}
		while (sc2.hasNextLine()) {
			Scanner s2 = new Scanner(sc2.nextLine());
			while (s2.hasNext()) {
				String s = s2.next();
				dict.insert(s, 2);
			}
			s2.close();
		}
		
		try {
			sc2 = new Scanner(new File(file3));
		} catch (FileNotFoundException e) {
			System.out.println("couldn't read file gandhi.txt file"); 
		}
		while (sc2.hasNextLine()) {
			Scanner s2 = new Scanner(sc2.nextLine());
			while (s2.hasNext()) {
				String s = s2.next();
				dict.insert(s, 3);
			}
			s2.close();
		}
		
		String input = "";
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter your search string: ");
		String s = in.nextLine();
		String[] words = s.split("\\s+");
		int downey = 0;
		int gandhi = 0;
		int iron = 0;
		for(int i=0; i<words.length; i++){
			int flag = 0;
			for(int j=0;j<Trie.stops.length;j++){
	              if(words[i].equals(Trie.stops[j])){
	            	  flag = 1;
	            	  break;
	              }
			}
			if(flag == 0){
				Iterator<Integer> iter;
				iter = dict.getValue(words[i]);
				while(iter.hasNext()){
					int temp = (int)iter.next();
					if(temp == 1){
						downey++;
					}
					else if (temp == 2){
						gandhi++;
					}
					else if(temp == 3){
						iron++;
					}
				}
			}
		}
		System.out.println("Searching...");
		if (iron == 0 && downey == 0 && gandhi == 0){
			System.out.println("The string occurs in None of the wiki page");
		}
		else if (downey == gandhi && downey == iron){
			System.out.println("3 results found");
			System.out.println("https://en.wikipedia.org/wiki/Robert_Downey,_Jr.");
			System.out.println("https://en.wikipedia.org/wiki/Mahatma_Gandhi");
			System.out.println("https://en.wikipedia.org/wiki/Iron_Man_(2008_film)");
		}
		else if(downey > gandhi && downey > iron){
			System.out.println("1 results found");
			System.out.println("https://en.wikipedia.org/wiki/Robert_Downey,_Jr.");
		}
		else if (gandhi > downey && gandhi > iron){
			System.out.println("1 results found");
			System.out.println("https://en.wikipedia.org/wiki/Mahatma_Gandhi");
		}
		else if (iron > downey && iron > gandhi){
			System.out.println("1 results found");
			System.out.println("https://en.wikipedia.org/wiki/Iron_Man_(2008_film)");
		}
	}
}
