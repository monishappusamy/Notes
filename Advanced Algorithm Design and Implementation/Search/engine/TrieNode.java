package engine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TrieNode {
	public TrieNode(char ch)  {
        value = ch;
        children = new HashMap<>();
        bIsEnd = false;
        occurenceList = new HashSet<Integer>();
    }    
    public HashMap<Character,TrieNode> getChildren() {   return children;  }    
    public char getValue()                           {   return value;     }    
    public void setIsEnd(boolean val)                {   bIsEnd = val;     }    
    public boolean isEnd()                           {   return bIsEnd;    }
    
      
    private char value;    
    private HashMap<Character,TrieNode> children;
    private boolean bIsEnd;  
    Set<Integer> occurenceList;
}
