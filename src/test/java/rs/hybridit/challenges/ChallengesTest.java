package rs.hybridit.challenges;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.Assert;

@RunWith(MockitoJUnitRunner.class)
public class ChallengesTest {

    @InjectMocks
    private Challenges challenges;

    @Test
    public void checkIfStringIsAPalindrome_palindromeIsProvidedAsInput_valueIsConfirmedToBeAPalindrome() {
        Assert.isTrue(challenges.checkIfStringIsAPalindrome("ana"), "I expected a palindrome but result doesn't match my expectations!");
        Assert.isTrue(challenges.checkIfStringIsAPalindrome("aaa"), "I expected a palindrome but result doesn't match my expectations!");
        Assert.isTrue(challenges.checkIfStringIsAPalindrome("bbb"), "I expected a palindrome but result doesn't match my expectations!");

    }

    @Test
    public void checkIfStringIsAPalindrome_notAPalindromeIsProvidedAsInput_valueIsConfirmedToNotBeAPalindrome() {
        Assert.isTrue(challenges.checkIfStringIsAPalindrome("abc"), "I expected a value which is not palindrome but result doesn't match my expectations!");
        Assert.isTrue(challenges.checkIfStringIsAPalindrome("cba"), "I expected a value which is not palindrome but result doesn't match my expectations!");
        Assert.isTrue(challenges.checkIfStringIsAPalindrome("edf"), "I expected a value which is not palindrome but result doesn't match my expectations!");

    }

    @Test
    public void test1() {
        // ...
    }

    @Test
    public void test2() {
        // ...
    }

    @Test
    public void test3() {
        // ...
    }
}
