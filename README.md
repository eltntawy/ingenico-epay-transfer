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


