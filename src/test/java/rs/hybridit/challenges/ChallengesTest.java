package rs.hybridit.challenges;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.util.Assert.isTrue;


@RunWith(MockitoJUnitRunner.class)
public class ChallengesTest {

    @InjectMocks
    private Challenges challenges;

    @Test
    public void checkIfStringIsAPalindrome_palindromeIsProvidedAsInput_valueIsConfirmedToBeAPalindrome() {
        isTrue(challenges.checkIfStringIsAPalindrome("ana"), "I expected a palindrome but result doesn't match my expectations!");
        isTrue(challenges.checkIfStringIsAPalindrome("aaa"), "I expected a palindrome but result doesn't match my expectations!");
        isTrue(challenges.checkIfStringIsAPalindrome("bbb"), "I expected a palindrome but result doesn't match my expectations!");

    }

    @Test
    public void checkIfStringIsAPalindrome_notAPalindromeIsProvidedAsInput_valueIsConfirmedToNotBeAPalindrome() {
        /*
        isTrue(challenges.checkIfStringIsAPalindrome("abc"), "I expected a value which is not palindrome but result doesn't match my expectations!");
        isTrue(challenges.checkIfStringIsAPalindrome("cba"), "I expected a value which is not palindrome but result doesn't match my expectations!");
        isTrue(challenges.checkIfStringIsAPalindrome("edf"), "I expected a value which is not palindrome but result doesn't match my expectations!");
        */

        Assert.assertFalse(challenges.checkIfStringIsAPalindrome("abc"));
        Assert.assertFalse(challenges.checkIfStringIsAPalindrome("cba"));
        Assert.assertFalse(challenges.checkIfStringIsAPalindrome("edf"));

    }


    @Test
    public void findDuplicateCharactersTest() {
        Assert.assertEquals(challenges.findDuplicateCharacters("Marina"), ("a"));
        Assert.assertEquals(challenges.findDuplicateCharacters("ABABC"), ("AB"));
        Assert.assertEquals(challenges.findDuplicateCharacters("abc"), "");

    }

    @Test
    public void findFirstNonRepeatedCharacterTest() {
        Assert.assertEquals(challenges.findFirstNonRepeatedCharacter("Marina"), "m");
        Assert.assertEquals(challenges.findFirstNonRepeatedCharacter("aaaamMarina"), "r");
        Assert.assertEquals(challenges.findFirstNonRepeatedCharacter("aaabbb"), null);
    }


    @Test
    public void findMissingNumberInArrayTest() {
        //test 1
        // given
        int[] arr1 = new int[5];
        arr1[0] = 1;
        arr1[1] = 2;
        arr1[2] = 3;
        arr1[3] = 5;


        ArrayList<Integer> arr1Res = new ArrayList<Integer>();
        arr1Res.add(4);

        // when
        Object object = challenges.findMissingNumberInArray(arr1, 5);


        // then
        assertThat(object).isEqualTo(arr1Res);


        //test2
        //given
        int[] arr2 = {1, 5, 6, 7, 8, 9, 10};
        ArrayList<Integer> arr2Res= new ArrayList<Integer>();
        arr2Res.add(2);
        arr2Res.add(3);
        arr2Res.add(4);

        //when
        Object object2 = challenges.findMissingNumberInArray(arr2, 10);

        //then
        assertThat(object2).isEqualTo(arr2Res);


        //test3
        //given
        int[] arr3 = {1, 2, 3, 4, 5};
        ArrayList<Integer> arr3Res= new ArrayList<Integer>();


        //when
        Object object3 = challenges.findMissingNumberInArray(arr3, 5);

        //then
        assertThat(object3).isEqualTo(arr3Res);

}


    @Test
    public void findAllPairsInsideAvrrayWithGienSumTest(){
        //test 1
        //given
        int[] arr1 = {1, 2, 3};
        ArrayList arr1Res = new ArrayList<>();
        arr1Res.add("2+3");
        /*
        arr1Res.add(new int[]{2, 3});
         */


        //when
        Object object = challenges.findAllPairsInsideAvrrayWithGienSum(arr1, 5);

        //then

        //Assert.assertEquals(object.toString(), arr1Res.toString());
        //assertThat(object).(new int[]{2, 3});
        assertThat(object).isEqualTo(arr1Res);



        //test 2
        //given
        int[] arr2 = {1, 2, 3, 4, 3};
        ArrayList<String> arr2Res = new ArrayList<String>();
        arr2Res.add("2+4");
        arr2Res.add("3+3");

        //int[][] arr2Res = {{2, 4}, {1, 2, 3}};

        //when
        Object object2 = challenges.findAllPairsInsideAvrrayWithGienSum(arr2, 6);

        //then
        //assertThat(object2).isEqualToComparingFieldByField(Arrays.asList(arr2Res));
        assertThat(object2).isEqualTo(arr2Res);


       //test3
        //given
        int[] arr3 = {1, 2, 3};
        ArrayList<Integer> arr3Res = new ArrayList<Integer>();

        //when
        Object object3 = challenges.findAllPairsInsideAvrrayWithGienSum(arr3, 15);

        //then
        assertThat(object3).isEqualTo(arr3Res);



    }





}
