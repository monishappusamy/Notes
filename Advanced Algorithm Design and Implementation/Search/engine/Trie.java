package engine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Trie {
	
	static String[] stops ={"a", "about", "above", "above", "across", "after", "afterwards", "again", "against", "all", "almost", 
            "alone", "along", "already", "also","although","always","am","among", "amongst", "amoungst", "amount",  "an", "and", 
            "another", "any","anyhow","anyone","anything","anyway", "anywhere", "are", "around", "as",  "at", "back","be","became", 
            "because","become","becomes", "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside", "besides", 
            "between", "beyond", "bill", "both", "bottom","but", "by", "call", "can", "cannot", "cant", "co", "con", "could", "couldnt",
            "cry", "de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight", "either", "eleven","else",
            "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything", "everywhere", "except", "few", 
            "fifteen", "fify", "fill", "find", "fire", "first", "five", "for", "former", "formerly", "forty", "found", "four", "from", 
            "front", "full", "further", "get", "give", "go", "had", "has", "hasnt",
            "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", 
            "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in", "inc", "indeed", "interest", "into", 
            "is", "it", "its", "itself", "keep", "last", "latter", "latterly", "least", "less", "ltd", "made", "many", 
            "may", "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must", 
            "my", "myself", "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none", 
            "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", "onto", 
            "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "own","part", "per", "perhaps",
            "please", "put", "rather", "re", "same", "see", "seem", "seemed", "seeming", "seems", "serious", "several", "she",
            "should", "show", "side", "since", "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", 
            "sometime", "sometimes", "somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their", 
            "them", "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein", "thereupon", 
            "these", "they", "thickv", "thin", "third", "this", "those", "though", "three", "through", "throughout", "thru", 
            "thus", "to", "together", "too", "top", "toward", "towards", "twelve", "twenty", "two", "un", "under", "until", 
            "up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever", "when", "whence", "whenever",
            "where", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", 
            "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would", "yet",
            "you", "your", "yours", "yourself", "yourselves","1","2","3","4","5","6","7","8","9","10","1.","2.","3.","4.","5.","6.","11",
            "7.","8.","9.","12","13","14","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
            "terms","CONDITIONS","conditions","values","interested.","care","sure",".","!","@","#","$","%","^","&","*","(",")","{","}","[","]",":",";",",","<",".",">","/","?","_","-","+","=",
            "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
            "contact","grounds","buyers","tried","said,","plan","value","principle.","forces","sent:","is,","was","like",
            "discussion","tmus","diffrent.","layout","area.","thanks","thankyou","hello","bye","rise","fell","fall","psqft.","http://","km","miles"};
	 // Constructor
    public Trie()   {     root = new TrieNode((char)0);       }    
  
    // Method to insert a new word to Trie
    public void insert(String word, int value)  {
          
        // Find length of the given word
        int length = word.length();        
        TrieNode crawl = root;
          
        // Traverse through all characters of given word
        for( int level = 0; level < length; level++)
        {
            HashMap<Character,TrieNode> child = crawl.getChildren();            
            char ch = word.charAt(level);
              
            // If there is already a child for current character of given word 
            if( child.containsKey(ch))
                crawl = child.get(ch);
            else   // Else create a child
            {              
                TrieNode temp = new TrieNode(ch);
                child.put( ch, temp );
                crawl = temp;
            }
        }
          
        // Set bIsEnd true for last character
        crawl.setIsEnd(true);
        crawl.occurenceList.add(value);
    }
      
    // The main method that finds out the longest string 'input'
    public String getMatchingPrefix(String input)  {
        String result = ""; // Initialize resultant string
        int length = input.length();  // Find length of the input string       
          
        // Initialize reference to traverse through Trie
        TrieNode crawl = root;   
         
        // Iterate through all characters of input string 'str' and traverse 
        // down the Trie
        int level, prevMatch = 0; 
        for( level = 0 ; level < length; level++ )
        {    
            // Find current character of str
            char ch = input.charAt(level);    
             
            // HashMap of current Trie node to traverse down
            HashMap<Character,TrieNode> child = crawl.getChildren();                        
            
            // See if there is a Trie edge for the current character
            if( child.containsKey(ch) )
            {
               result += ch;          //Update result
               crawl = child.get(ch); //Update crawl to move down in Trie
                
               // If this is end of a word, then update prevMatch
               if( crawl.isEnd() ) 
                    prevMatch = level + 1;
            }            
            else  break;
        }
         
        // If the last processed character did not match end of a word, 
        // return the previously matching prefix
        if( !crawl.isEnd() )
                return result.substring(0, prevMatch);        
        
        else return result;
    }
      
    public Iterator<Integer> getValue(String input){
    	String result = ""; // Initialize resultant string
        int length = input.length();  // Find length of the input string       
          
        // Initialize reference to traverse through Trie
        TrieNode crawl = root;   
         
        // Iterate through all characters of input string 'str' and traverse 
        // down the Trie
        int level, prevMatch = 0; 
        for( level = 0 ; level < length; level++ )
        {    
            // Find current character of str
            char ch = input.charAt(level);    
             
            // HashMap of current Trie node to traverse down
            HashMap<Character,TrieNode> child = crawl.getChildren();                        
            
            // See if there is a Trie edge for the current character
            if( child.containsKey(ch) )
            {
               result += ch;          //Update result
               crawl = child.get(ch); //Update crawl to move down in Trie
                
               // If this is end of a word, then update prevMatch
               if( crawl.isEnd() ) 
                    prevMatch = level + 1;
            }            
            else  break;
        }
        
        if( !crawl.isEnd() )
            return null;        
        else return crawl.occurenceList.iterator();
    }
    
    private TrieNode root;      
}
