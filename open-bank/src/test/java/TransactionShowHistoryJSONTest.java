import org.junit.Before;
import org.junit.Test;
import org.sdmlib.openbank.*;

import java.math.BigInteger;
import java.util.Date;
import static org.junit.Assert.*;

/**
 * Created by FA on 4/4/2017.
 */
public class TransactionShowHistoryJSONTest {

    @Test
    // this test case testes the custom method(getTransactions) in transactions class
    public void testGetTransactionHistory() {
        Account accnt = new Account();
        Account resultAccnt = new Account();
        Transaction trans = new Transaction();

        User usr1 = new User()
                .withName("tina")
                .withUserID("tina1");

        accnt = new Account()
                .withOwner(usr1)
                .withAccountnum(1);


        resultAccnt = trans.getTransactions(usr1.getUserID());

        System.out.println();
        System.out.println("*******************************fromJson*********************************");
        System.out.println(resultAccnt.toString());
        System.out.println("UserID: " + resultAccnt.getOwner().getUserID());
        System.out.println("Name: " + resultAccnt.getOwner().getName());
        //System.out.println("Credit: Amount:" + resultAccnt.getCredit().toString() + ". Time:" + resultAccnt.getCredit().getCreationdate().toString());
       // System.out.println("Debit: " + resultAccnt.getDebit().toString());
        System.out.println("Balance: " + resultAccnt.getBalance());
    }

    @Test
    //
    public void testJSON(){
        Account accnt = new Account();
        JsonPersistency json = new JsonPersistency();

        Transaction trans = new Transaction();
        Transaction trans2 = new Transaction();
        Transaction trans3 = new Transaction();

        User usr1 = new User()
                .withName("tina")
                .withUserID("tina1");

        accnt = new Account()
                .withOwner(usr1)
                .withAccountnum(1);

        Date dt = new Date("03/19/2017");
        Date dtime = new Date("03/19/2017 13:13:26");

        trans.setAmount(BigInteger.valueOf(50));
        // set date
        trans.setCreationdate(dt);
        // set time
        //trans.setTime(dtime);
        trans.setNote("Deposit Trans 1");

        //*** Second Transaction
        dt = new Date("03/19/2017");
        dtime = new Date("03/19/2017 13:16:30");

        trans2.setAmount(BigInteger.valueOf(20));
        // set date
        trans2.setCreationdate(dt);
        // set time
        //trans2.setTime(dtime);
        trans2.setNote("Deposit Trans 2");

        //*** Third Transaction
        dt = new Date("03/19/2017");
        dtime = new Date("03/19/2017 13:20:30");

        trans3.setAmount(BigInteger.valueOf(40));
        // set date
        trans3.setCreationdate(dt);
        // set time
        // trans3.setTime(dtime);
        trans3.setNote("Withdraw Trans 3");


        Account accountBeforeJson = new Account().withOwner(usr1)
                .withBalance(BigInteger.valueOf(550)).withCreationdate(dt);
                //.withCredit(trans);

        accountBeforeJson.withBalance(BigInteger.valueOf(570)).withCreationdate(dt);
       //accountBeforeJson.withCredit(trans2);

        //accountBeforeJson.withBalance(540.00).withCreationdate(dt);
        //accountBeforeJson.withDebit(trans3);

        System.out.println("*********************************toJson*********************************");
        json.toJson(accountBeforeJson);

        Account accountAfterJson = json.fromJson(usr1.getUserID());

        System.out.println();
        System.out.println("*******************************fromJson*********************************");
        System.out.println(accountAfterJson.toString());
        System.out.println("UserID: " + accountAfterJson.getOwner().getUserID());
        System.out.println("Name: " + accountAfterJson.getOwner().getName());
        //System.out.println("Credit: Amount:" + accountAfterJson.getCredit().toString() + ". Time:" + accountAfterJson.getCredit().getCreationdate().toString());
        //System.out.println("Debit: " + accountAfterJson.getDebit().toString());

        System.out.println("Balance: " + accountAfterJson.getBalance());

    }


    @Test(expected=NullPointerException.class)
    // set user with null should throws NullPointerException exception
    public void testtoJsonWithNULL() {
        Account accnt = new Account();
        JsonPersistency json = new JsonPersistency();

        Transaction trans = new Transaction();

        User usr1 = null;
        /*
        new User()

                .withName("tina")
                .withUserID("tina1");
*/
        accnt = new Account()
                .withOwner(usr1)
                .withAccountnum(1);

        Date dt = new Date("03/19/2017");
        Date dtime = new Date("03/19/2017 13:13:26");

        trans.setAmount(BigInteger.valueOf(50));
        // set date
        trans.setCreationdate(dt);
        // set time
        //trans.setTime(dtime);
        trans.setNote("Deposit Trans 1");


        Account accountBeforeJson = new Account().withOwner(usr1)
                .withBalance(BigInteger.valueOf(550)).withCreationdate(dt);
                //.withCredit(trans);
/*
        accountBeforeJson.withBalance(570.00).withCreationdate(dt);
        accountBeforeJson.withCredit(trans2);

        accountBeforeJson.withBalance(540.00).withCreationdate(dt);
        accountBeforeJson.withDebit(trans3);
*/
        json.toJson(accountBeforeJson);

    }


    @Test(expected=NullPointerException.class)
    // set user with null should throws NullPointerException exception
    public void testFromJsonWithNULL() {
        Account accnt = new Account();
        JsonPersistency json = new JsonPersistency();

        Transaction trans = new Transaction();

        User usr1 = null;

        Account accountAfterJson = json.fromJson(usr1.getUserID());
    }

    @Test
    // set user with valid user should return account object
    public void testFromJson() {
        Account accnt = new Account();
        JsonPersistency json = new JsonPersistency();

        Transaction trans = new Transaction();

        User usr1 = new User()
                .withName("tina")
                .withUserID("tina1");

        Account accountAfterJson = json.fromJson(usr1.getUserID());

        assertTrue("tina1" == accountAfterJson.getOwner().getUserID());
        assertTrue("tina" == accountAfterJson.getOwner().getName());
        assertTrue(BigInteger.valueOf(540) == accountAfterJson.getBalance());
    }

}
