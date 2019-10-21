package rs.hybridit.challenges;

/*
	A list of challenges that should be implemented in the best possible way
	Tests will be written to cover the methods and check expected output
 */
interface Tasks {

    /*
        Implement logic to check if the given string is a palindrome.
        An String is said to be palindrome if it is equal to itself after reversing.
    */
    boolean checkIfStringIsAPalindrome(String text);

    /*
        Implement a solution to find and return all the duplicate characters inside the provided string
        For example inside AA is A, ABCDB is B
        change what is necessary
    */
    Object findDuplicateCharacters(String text);

    /*
        Implement a solution to find and return the first non repeated character inside a String
        For example given "Something" S is the first non repeating character, "aaaaaaSbbbbbDfffff" S is again the first non repeating char
        change what is necessary
    */
    Object findFirstNonRepeatedCharacter(String text);

    /*
        Implement a solution to find the missing numbers in a given array
        numbers from 1 to n only.
        can be used to find missing elements on integer array of
         numbers from 1 to 100 or 1 - 1000
    */
    Object findMissingNumberInArray(int[] numbers, int count);

    /*
        Find all pairs from the array where the sum is equal to the given sum
    */
    Object findAllPairsInsideArrayWithGivenSum(int[] array, int sum);

}
