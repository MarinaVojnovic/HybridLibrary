package rs.hybridit.challenges;

import java.util.*;

class Challenges implements Tasks {

    @Override
    public boolean checkIfStringIsAPalindrome(String text) {
        boolean result = true;

        for (int i = 0; i <text.length()/2; i++){
            if (text.charAt(i) != text.charAt(text.length()-1-i)){
                result = false;
                break;
            }
        }

        return result;
    }

    @Override
    public String findDuplicateCharacters(String text) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        for (int i = 0;i < text.length(); i++){
            Character letter = text.charAt(i);
            if (map.containsKey(letter)){
                map.replace(letter, map.get(letter)+1);
            }else {
                map.put(letter, 1);
            }
        }
        String result="";
        for (Character k : map.keySet()){
            if (map.get(k) > 1){
                result += k;
            }
        }

        return result;
    }

    @Override
    public String findFirstNonRepeatedCharacter(String text) {
        LinkedList<String> unrepeated = new LinkedList<String>();
        LinkedHashSet<String> repeated = new LinkedHashSet<String>();

        for (int i = 0; i < text.length(); i++){
            if (unrepeated.contains((text.charAt(i)+"").toLowerCase())==false && repeated.contains((text.charAt(i)+"").toLowerCase()) == false){
                unrepeated.add((text.charAt(i)+"").toLowerCase());
            }else {
                unrepeated.remove((text.charAt(i)+"").toLowerCase());
                repeated.add((text.charAt(i)+"").toLowerCase());
            }
        }
        if (unrepeated.size() > 0){
            return unrepeated.get(0);
        }else{
            return "";
        }

    }

    @Override
    public Set<Integer> findMissingNumberInArray(int[] numbers, int count) {
        Set<Integer> missingNumbers = new HashSet<Integer>();
        int position = 0;

        for (int i=1; i <count + 1; i++){
            if (numbers[position]!=i){
                missingNumbers.add(i);
            }else{
                position=position+1;
            }
        }

        return missingNumbers;
    }

    @Override
    public List<String> findAllPairsInsideAvrrayWithGienSum(int[] array, int sum) {
        List<String> pairs = new ArrayList<>();

        for (int i = 0; i < array.length-1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + array[j] == sum) {
                    pairs.add(array[i] + "+" + array[j]);

                }
            }
        }

        return pairs;
    }
}
