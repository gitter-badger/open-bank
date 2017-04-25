Title: Resolve Technical Debts

Description:

  Resolve the technical debts that have accumulated during the project.
  a) Update User Class:
	- Datatype of Phone Number: Change it to a String
	- Add a username String attribute and use username for the functions
		userID was previously used for
		+ userID will be a unique identifier for each userID
  b) Update Account Class:
	- Remove TransactionSets Debit/Credit, consolidate to TransactionSets
		called transactions
	- Balance datatype should be two BigIntegers [java type, one to hold 
		before the decimal place and one for after the decimal place]
	- Edit transaction methods to also create an additional fee transaction
		when necessary
  c) Update Transaction Class:
    - Transactions should have a 1 to 1 relationship with itself
		+ Label it as previous and next
		+ (this is a change to the Class Diagram)
    - Amount datatype should be two BigIntegers [java type, one to hold 
		before the decimal place and one for after the decimal place]
    - Change TransactionTypeEnum fields to [DEPOSIT, WITHDRAWAL, TRANSFER,
		SEED, CLOSE, FEE]
  
Related scenario(s):

  
  
Time estimate(s):

 - 9H

Author(s):

  - Vincent

Assigned persons (currently working on task):

  - Cycielya

Log entries (who worked when and how long on this):

	