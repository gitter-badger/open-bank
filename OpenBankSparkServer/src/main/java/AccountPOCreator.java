import de.uniks.networkparser.IdMap;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class AccountPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new AccountPO(new Account[]{});
      } else {
          return new AccountPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
