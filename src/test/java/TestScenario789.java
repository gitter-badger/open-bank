import org.junit.Test;
import org.sdmlib.openbank.Account;
import org.sdmlib.openbank.User;
import static org.junit.Assert.*;

/**
 * author: Samuel Luu
 */

public class TestScenario789 {
    private User tina, nick;
    private Account t, n;

    public void precondition() {
        this.tina = new User()
                .withName("Tina")
                .withUserID("tina2017");

        this.nick = new User()
                .withName("Nick")
                .withUserID("nick2017");
        this.t = new Account()
                .withOwner(tina)
                .withAccountnum(1);
        this.n = new Account()
                .withOwner(nick)
                .withAccountnum(2);
    }

    /**
     * S7:
     * Title: Tina gives Nick 10 dollars through their accounts
     * (Pre)* Tina wants to give Nick 10 dollars by connecting their accounts through phone.
     *        Tina has 50 dollars in her checking and Nick has 15.
     * 1) Tina connects to Nick's phone -> See S6
     * 2) Tina selects the exchange funds option
     * 3) Tina enters the amount she wants to transfer
     * 4) Tina enters the account she wants to funds to come out of
     * 5) Tina selects the transfer funds option
     * 6) Nick selects the approve option on his phone to receive the funds
     * (Post)* Tina has transferred 10 dollars to Nick between their accounts
     */
    @Test //Test the Player functionality
    public void S7Test(){
        this.precondition();
        this.t.withBalance(50);
        this.n.withBalance(15).setIsConnected(true);
        this.t.transferToUser(10, this.n);

        assertEquals(40, this.t.getBalance(), 0);
        assertEquals(25, this.n.getBalance(), 0);
    }

    /**
     * S8:
     * Title: Tina tries to withdraw too much money
     *(Pre)* Tina is desperate for money and needs 1 million dollars. Tina has 40 dollars in her checking account
     * 1) Tine logs into her banking account -> See S1
     * 2) Tina selects withdraw option
     * 3) Tina enters savings account
     * 4) Tina enters 1 million dollars
     * 5) Tina select the withdraw button
     * 6) Tina reads the alert that that reads "Cannot complete transaction: Too many funds requested"
     *(Post)* Tina does not have 1 million in cash
     */
    @Test (expected = IllegalArgumentException.class)
    public void S8Test() {
        this.precondition();
        this.t.withBalance(40);
        this.t.withdraw(1000000);   //This should throw an IllegalArgumentException
    }

    /**
     * S9:
     * Title: Tina tries to transfer too much money to Nick
     *(Pre)* Tina wants to transfer 1 million dollars to Nick. She has 30 dollars in her checking account
     * 1) Tina connects to Nick's phone -> See S6
     * 2) Tina selects the exchange funds option
     * 3) Tina enters 1 million dollars to transfer
     * 4) Tina selects the checking account to get the money
     * 5) Tina selects the transfer funds option
     *(Post)* Tina is presented with a message saying she does not have enough funds
     */
    @Test
    public void S9Test() {
        this.precondition();
        this.t.withBalance(30);
        this.n.withBalance(15).withIsConnected(true);
        assertFalse(this.t.transferToUser(1000000, this.n));
    }
}