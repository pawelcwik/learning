package pl.com.clockworkgnome.pragmaticunittesting.third;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class AssertTest {

    Account account;

    @Before
    public void init() {
        account = new Account("Account name");
    }

    @Test
    public void hasPositiveBalance() {
        account.deposit(50);
        assertTrue(account.hasPositiveBalance());
    }

    @Test
    public void depositIncreasesBalance() {
        int initialBalance = account.getBalance();
        account.deposit(100);
        assertTrue(account.getBalance() > initialBalance);
        assertThat(account.getBalance(),equalTo(100));
        assertThat(account.getName(), startsWith("Acc"));
        assertThat(new String[] {"a", "b"}, equalTo(new String[] {"a", "b"}));
        assertThat(Arrays.asList(new String[] {"a"}),
                equalTo(Arrays.asList(new String[] {"a"})));
        assertThat(account.getName(), not(equalTo("plunderings")));
    }


}
