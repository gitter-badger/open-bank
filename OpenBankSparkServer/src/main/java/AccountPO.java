import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;

import java.math.BigInteger;
import java.util.Date;

public class AccountPO extends PatternObject<AccountPO, Account>
{

    public AccountSet allMatches()
   {
      this.setDoAllMatches(true);
      
      AccountSet matches = new AccountSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Account) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public AccountPO(){
      newInstance(null);
   }

   public AccountPO(Account... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public AccountPO(String modifier)
   {
      this.setModifier(modifier);
   }
   
   //==========================================================================
   
   public void Account(BigInteger initialAmount)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Account) getCurrentMatch()).Account(initialAmount);
      }
   }

   
   //==========================================================================

   public boolean transferToUser(BigInteger amount, Account destinationAccount, String note)
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) getCurrentMatch()).transferToAccount(amount, destinationAccount, note);
      }
      return false;
   }

   
   //==========================================================================
//
//   public boolean myBankTransaction(double amount, Account destinationAccount)
//   {
//      if (this.getPattern().getHasMatch())
//      {
//         return ((Account) getCurrentMatch()).myBankTransaction(amount, destinationAccount);
//      }
//      return false;
//   }
//
//
//   //==========================================================================
//
//   public boolean receiveFound(double amount, Account sourceAccount)
//   {
//      if (this.getPattern().getHasMatch())
//      {
//         return ((Account) getCurrentMatch()).receiveFound(amount, sourceAccount);
//      }
//      return false;
//   }
//
//
//   //==========================================================================
//
//   public boolean sendTransactionInfo(Transaction transaction, double amount, Date p0, Date p1, String note)
//   {
//      if (this.getPattern().getHasMatch())
//      {
//         return ((Account) getCurrentMatch()).sendTransactionInfo(transaction, amount, p0, p1, note);
//      }
//      return false;
//   }

   
   //==========================================================================
   
   public void withdraw(BigInteger amount)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Account) getCurrentMatch()).withdraw(amount);
      }
   }

   
   //==========================================================================
   
   public void deposit(BigInteger amount)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Account) getCurrentMatch()).deposit(amount);
      }
   }

   public AccountPO createBalanceCondition(double value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_BALANCE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createBalanceCondition(double lower, double upper)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_BALANCE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createBalanceAssignment(double value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_BALANCE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BigInteger getBalance()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) getCurrentMatch()).getBalance();
      }
      return BigInteger.valueOf(0);
   }
   
   public AccountPO withBalance(BigInteger value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Account) getCurrentMatch()).setBalance(value);
      }
      return this;
   }
   
   public AccountPO createAccountnumCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_ACCOUNTNUM)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createAccountnumCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_ACCOUNTNUM)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createAccountnumAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_ACCOUNTNUM)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getAccountnum()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) getCurrentMatch()).getAccountnum();
      }
      return 0;
   }
   
   public AccountPO withAccountnum(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Account) getCurrentMatch()).setAccountnum(value);
      }
      return this;
   }
   
   public AccountPO createCreationdateCondition(Date value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_CREATIONDATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createCreationdateAssignment(Date value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_CREATIONDATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
//   public Date getCreationdate()
//   {
//      if (this.getPattern().getHasMatch())
//      {
//         return ((Account) getCurrentMatch()).getCreationdate();
//      }
//      return null;
//   }
   
//   public AccountPO withCreationdate(Date value)
//   {
//      if (this.getPattern().getHasMatch())
//      {
//         ((Account) getCurrentMatch()).setCreationdate(value);
//      }
//      return this;
//   }
   
   public AccountPO createIsConnectedCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_ISCONNECTED)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createIsConnectedAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_ISCONNECTED)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public boolean getIsConnected()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) getCurrentMatch()).isIsConnected();
      }
      return false;
   }
   
   public AccountPO withIsConnected(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Account) getCurrentMatch()).setIsConnected(value);
      }
      return this;
   }
   
   public UserPO createOwnerPO()
   {
      UserPO result = new UserPO(new User[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Account.PROPERTY_OWNER, result);
      
      return result;
   }

   public UserPO createOwnerPO(String modifier)
   {
      UserPO result = new UserPO(new User[]{});
      
      result.setModifier(modifier);
      super.hasLink(Account.PROPERTY_OWNER, result);
      
      return result;
   }

   public AccountPO createOwnerLink(UserPO tgt)
   {
      return hasLinkConstraint(tgt, Account.PROPERTY_OWNER);
   }

   public AccountPO createOwnerLink(UserPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Account.PROPERTY_OWNER, modifier);
   }

   public User getOwner()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) this.getCurrentMatch()).getOwner();
      }
      return null;
   }


   public Date getCreationdate()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) getCurrentMatch()).getCreationdate();
      }
      return null;
   }
   
   public AccountPO withCreationdate(Date value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Account) getCurrentMatch()).setCreationdate(value);
      }
      return this;
   }
   
   
   //==========================================================================
   
   public boolean receiveFunds(Account giver, BigInteger amount, String note)
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) getCurrentMatch()).receiveFunds(amount, note);
      }
      return false;
   }


   public AccountPO createCreationdateCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_CREATIONDATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createCreationdateCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_CREATIONDATE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createCreationdateAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_CREATIONDATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   
   //==========================================================================
   


   public AccountPO createTypeCondition(AccountTypeEnum value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_TYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createTypeAssignment(AccountTypeEnum value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_TYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountTypeEnum getType()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) getCurrentMatch()).getType();
      }
      return null;
   }
   
   public AccountPO withType(AccountTypeEnum value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Account) getCurrentMatch()).setType(value);
      }
      return this;
   }
   
   
   //==========================================================================
   
   public boolean transferToAccount(BigInteger amount, Account destinationAccount, String note)
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) getCurrentMatch()).transferToAccount(amount, destinationAccount, note);
      }
      return false;
   }



   public BankPO createBankPO()
   {
      BankPO result = new BankPO(new Bank[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Account.PROPERTY_BANK, result);
      
      return result;
   }

   public BankPO createBankPO(String modifier)
   {
      BankPO result = new BankPO(new Bank[]{});
      
      result.setModifier(modifier);
      super.hasLink(Account.PROPERTY_BANK, result);
      
      return result;
   }

   public AccountPO createBankLink(BankPO tgt)
   {
      return hasLinkConstraint(tgt, Account.PROPERTY_BANK);
   }

   public AccountPO createBankLink(BankPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Account.PROPERTY_BANK, modifier);
   }

   public Bank getBank()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) this.getCurrentMatch()).getBank();
      }
      return null;
   }

   public BankPO createEmployingBankPO()
   {
      BankPO result = new BankPO(new Bank[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Account.PROPERTY_EMPLOYINGBANK, result);
      
      return result;
   }

   public BankPO createEmployingBankPO(String modifier)
   {
      BankPO result = new BankPO(new Bank[]{});
      
      result.setModifier(modifier);
      super.hasLink(Account.PROPERTY_EMPLOYINGBANK, result);
      
      return result;
   }

   public AccountPO createEmployingBankLink(BankPO tgt)
   {
      return hasLinkConstraint(tgt, Account.PROPERTY_EMPLOYINGBANK);
   }

   public AccountPO createEmployingBankLink(BankPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Account.PROPERTY_EMPLOYINGBANK, modifier);
   }

   public Bank getEmployingBank()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) this.getCurrentMatch()).getEmployingBank();
      }
      return null;
   }

  

   public AccountPO createBalanceCondition(BigInteger value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_BALANCE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createBalanceAssignment(BigInteger value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_BALANCE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TransactionPO createToTransactionPO()
   {
      TransactionPO result = new TransactionPO(new Transaction[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Account.PROPERTY_TOTRANSACTION, result);
      
      return result;
   }

   public TransactionPO createToTransactionPO(String modifier)
   {
      TransactionPO result = new TransactionPO(new Transaction[]{});
      
      result.setModifier(modifier);
      super.hasLink(Account.PROPERTY_TOTRANSACTION, result);
      
      return result;
   }

   public AccountPO createToTransactionLink(TransactionPO tgt)
   {
      return hasLinkConstraint(tgt, Account.PROPERTY_TOTRANSACTION);
   }

   public AccountPO createToTransactionLink(TransactionPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Account.PROPERTY_TOTRANSACTION, modifier);
   }

   public TransactionSet getToTransaction()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) this.getCurrentMatch()).getToTransaction();
      }
      return null;
   }

   public TransactionPO createFromTransactionPO()
   {
      TransactionPO result = new TransactionPO(new Transaction[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Account.PROPERTY_FROMTRANSACTION, result);
      
      return result;
   }

   public TransactionPO createFromTransactionPO(String modifier)
   {
      TransactionPO result = new TransactionPO(new Transaction[]{});
      
      result.setModifier(modifier);
      super.hasLink(Account.PROPERTY_FROMTRANSACTION, result);
      
      return result;
   }

   public AccountPO createFromTransactionLink(TransactionPO tgt)
   {
      return hasLinkConstraint(tgt, Account.PROPERTY_FROMTRANSACTION);
   }

   public AccountPO createFromTransactionLink(TransactionPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Account.PROPERTY_FROMTRANSACTION, modifier);
   }

   public TransactionSet getFromTransaction()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) this.getCurrentMatch()).getFromTransaction();
      }
      return null;
   }

   
   //==========================================================================
   
   public Transaction recordTransaction(Account sender, Account reciever, TransactionTypeEnum type, BigInteger amount, String note)
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) getCurrentMatch()).recordTransaction(sender, reciever, type, amount, note);
      }
      return null;
   }

   //==========================================================================
   
   public boolean receiveFunds(BigInteger amount, String note)
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) getCurrentMatch()).receiveFunds(amount, note);
      }
      return false;
   }

   public AccountPO createIsClosedCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_ISCLOSED)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public AccountPO createIsClosedAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Account.PROPERTY_ISCLOSED)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public boolean getIsClosed()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Account) getCurrentMatch()).isIsClosed();
      }
      return false;
   }
   
   public AccountPO withIsClosed(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Account) getCurrentMatch()).setIsClosed(value);
      }
      return this;
   }
   
}
