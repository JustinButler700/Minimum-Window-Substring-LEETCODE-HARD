//Justin Butler
//11/9/2021
/*
Runtime: 12 ms
Memory Usage: 39.5 MB
Beats 60% of Java Submissions.
*/
class Solution {
    public String minWindow(String s, String t) 
    {
        HashMap<Character, Integer> target = new HashMap<>();
        if(s.equals(t)){return t;} // handle edge cases
        if(s.isEmpty() || t.isEmpty()){return "";}
        //1. We will map the target chars that need to be in our substring.
        for(int i = 0; i < t.length(); i++)
        {
            char ch = t.charAt(i);
            if(target.containsKey(ch))
            {target.put(ch, target.get(ch)+1);}
            else
            {target.put(ch, 1);}
        }
        // Set up for out sliding window.
        // count starts out at target size, when it = 0, we know it is a matching substring.
        // int left and int right will hold the location indexes of the current smallest substring.
        int i = 0, j = 0, count = target.size(); 
        int left = 0, right = s.length()-1, min =s.length();
        boolean found = false; // for our return statement.
        
        
        while(j < s.length())
        {
            char lastChar = s.charAt(j++);
            if(target.containsKey(lastChar))
            {
                target.put(lastChar, target.get(lastChar)-1);
                if(target.get(lastChar)==0){count-=1;}
            }
            if(count >0) continue;
            
            while(count ==0){
                char startChar = s.charAt(i++);
                if(target.containsKey(startChar))
                {
                    target.put(startChar, target.get(startChar)+1);
                    if(target.get(startChar)>0){count+=1;}
                }
            }
            if((j-i)<min) // check if current substring is smaller than our known min.
            {
                left = i;
                right = j;
                min = j-i;
                found = true;
            }
        }
        return found ? s.substring(left-1, right) : ""; 
        
    }
}
