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
    public Object findDuplicateCharacters(String text) {
        HashMap<Character, Integer> mapa = new HashMap<Character, Integer>();
        for (int i = 0;i < text.length(); i++){
            Character slovo = text.charAt(i);
            if (mapa.containsKey(slovo)){
                mapa.replace(slovo, mapa.get(slovo)+1);
            }else {
                mapa.put(slovo, 1);
            }
        }
        String rezultat="";
        for (Character k : mapa.keySet()){
            if (mapa.get(k) > 1){
                rezultat += k;
            }
        }
        return rezultat;
    }

    @Override
    public Object findFirstNonRepeatedCharacter(String text) {
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
            return null;
        }

    }

    @Override
    public Object findMissingNumberInArray(int[] numbers, int count) {
        ArrayList<Integer> missingNumbers = new ArrayList<Integer>();
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
    public Object findAllPairsInsideAvrrayWithGienSum(int[] array, int sum) {


        List<String> pairs = new ArrayList<>();

        for (int i = 0; i < array.length-1; i++){
            for (int j=i+1; j < array.length; j++){
                if (array[i]+array[j] ==sum){
                    pairs.add(array[i]+"+"+array[j]);

                }
            }
        }





        return pairs;
    }
}
