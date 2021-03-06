/*
   Copyright (c) 2017 hlope
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class UserCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      User.PROPERTY_USERID,
      User.PROPERTY_ISADMIN,
      User.PROPERTY_PASSWORD,
      User.PROPERTY_NAME,
      User.PROPERTY_EMAIL,
      User.PROPERTY_LOGGEDIN,
      User.PROPERTY_PHONE,
      User.PROPERTY_ACCOUNT,
      User.PROPERTY_USERNAME,
      User.PROPERTY_BANK,
      User.PROPERTY_EMPLOYINGBANK,
      User.PROPERTY_SESSIONID,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new User();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (User.PROPERTY_USERID.equalsIgnoreCase(attribute))
      {
         return ((User) target).getUserID();
      }

      if (User.PROPERTY_ISADMIN.equalsIgnoreCase(attribute))
      {
         return ((User) target).isIsAdmin();
      }

      if (User.PROPERTY_PASSWORD.equalsIgnoreCase(attribute))
      {
         return ((User) target).getPassword();
      }

      if (User.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((User) target).getName();
      }

      if (User.PROPERTY_EMAIL.equalsIgnoreCase(attribute))
      {
         return ((User) target).getEmail();
      }

      if (User.PROPERTY_LOGGEDIN.equalsIgnoreCase(attribute))
      {
         return ((User) target).isLoggedIn();
      }

      if (User.PROPERTY_PHONE.equalsIgnoreCase(attribute))
      {
         return ((User) target).getPhone();
      }

      if (User.PROPERTY_ACCOUNT.equalsIgnoreCase(attribute))
      {
         return ((User) target).getAccount();
      }

      if (User.PROPERTY_USERNAME.equalsIgnoreCase(attribute))
      {
         return ((User) target).getUsername();
      }

      if (User.PROPERTY_BANK.equalsIgnoreCase(attribute))
      {
         return ((User) target).getBank();
      }

      if (User.PROPERTY_EMPLOYINGBANK.equalsIgnoreCase(attribute))
      {
         return ((User) target).getEmployingBank();
      }

      if (User.PROPERTY_SESSIONID.equalsIgnoreCase(attribute))
      {
         return ((User) target).getSessionID();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (User.PROPERTY_SESSIONID.equalsIgnoreCase(attrName))
      {
         ((User) target).setSessionID((String) value);
         return true;
      }

      if (User.PROPERTY_USERNAME.equalsIgnoreCase(attrName))
      {
         ((User) target).setUsername((String) value);
         return true;
      }

      if (User.PROPERTY_PHONE.equalsIgnoreCase(attrName))
      {
         ((User) target).setPhone(value.toString());
         return true;
      }

      if (User.PROPERTY_LOGGEDIN.equalsIgnoreCase(attrName))
      {
         ((User) target).setLoggedIn((Boolean) value);
         return true;
      }

      if (User.PROPERTY_EMAIL.equalsIgnoreCase(attrName))
      {
         ((User) target).setEmail((String) value);
         return true;
      }

      if (User.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((User) target).setName((String) value);
         return true;
      }

      if (User.PROPERTY_PASSWORD.equalsIgnoreCase(attrName))
      {
         ((User) target).setPassword((String) value);
         return true;
      }

      if (User.PROPERTY_ISADMIN.equalsIgnoreCase(attrName))
      {
         ((User) target).setIsAdmin((Boolean) value);
         return true;
      }

      if (User.PROPERTY_USERID.equalsIgnoreCase(attrName))
      {
         ((User) target).setUserID((String) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (User.PROPERTY_ACCOUNT.equalsIgnoreCase(attrName))
      {
         ((User) target).withAccount((Account) value);
         return true;
      }
      
      if ((User.PROPERTY_ACCOUNT + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((User) target).withoutAccount((Account) value);
         return true;
      }

      if (User.PROPERTY_BANK.equalsIgnoreCase(attrName))
      {
         ((User) target).setBank((Bank) value);
         return true;
      }

      if (User.PROPERTY_EMPLOYINGBANK.equalsIgnoreCase(attrName))
      {
         ((User) target).setEmployingBank((Bank) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((User) entity).removeYou();
   }
}
