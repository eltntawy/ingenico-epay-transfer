# ingenico-epay-transfer
Simple E-Pay transfer REST webservice application

Technology Used
--
- Java 1.7
- spring boot
- spring rest
- junit
- gson lib

Solution Design
--
solution contains for several layer
- Rest Service accept requests form client.
- Business Service the handel the backend logic.
- Data Access Object handel data manipulation.
- Data Transfer Object.
- Mapper to map from Dto -> entity and vs verse.
- Using Java Map Collection as storage to save data


Solution Structure
--
- Main package "com.ingenico.pay" contains
    * dao
    * entity
    * dto
    * exception
    * rest
    * service
    * util
- Test package "com.ingenico.pay.test" testing scenarios 

How To Run
--
- Clone repo by <br/>
`git clone https://github.com/eltntawy/ingenico-epay-transfer.git`
- open from your IDE as maven project
- build with dependencies
- Run main class com.ingenico.pay.EPayTransferApplication
- Server running on port 8080 
    * HINT: application will fail if this port is allocated to another running process
    
Web Service Documentation
==

#### Account Service
- Find all account
   - ex: GET /account/
   <br/>`[{"id":"5f6ca38e-e386-4d78-9bec-f4e02952fd40","name":"Account2","balance":10.0},{"id":"6cc5fcb7-67e7-41de-97d6-9494b5c01d74","name":"Account1","balance":10.4}]`
- Find account by id
   - ex: GET /account/{id} 
   - return : the account object
   <br/>`{"id": "6cc5fcb7-67e7-41de-97d6-9494b5c01d74","name": "Account1","balance": 10.4}`
- Create account name=Account1 and initial balance=10.4 
   - ex: POST /account/?accountName={accountName}&balance={initialBalance} 
   - returns : the account object
   <br/>`{"id": "6cc5fcb7-67e7-41de-97d6-9494b5c01d74","name": "Account1","balance": 10.4}`
- Delete account
   - ex: DELETE /account/{id}
   - returns : the deleted account object
   <br/>`{"id": "6cc5fcb7-67e7-41de-97d6-9494b5c01d74","name": "Account1","balance": 10.4}`
   
#### Transfer Service
- Transfer amount from account1 to account2
   - ex: POST /transfer/account1Id/account2Id/?amount=20.2
   - returns : a succesfull message with 'Transfer Completed successfully'
   
Error Codes
==
#### Error Code Account REST service:
- 200 - OK           - Operation success
- 404 - NOT FOUND    - Account with input id not found
- 400 - BAD REQUEST  - Illegal Initial Balance

#### Error Code Transfer REST service:
- 200 - OK           - transfer done successfully
- 400 - BAD REQUEST  - Operation discurted and replay with reason message [No sufficient Balance, Invalid Amount For Transfer]
