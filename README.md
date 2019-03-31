# Expectation :
Design and implement a RESTful API (including data model and the backing implementation) for money transfers between accounts.
Explicit requirements:
1. You can use Java, Scala or Kotlin.
2. Keep it simple and to the point (e.g. no need to implement any authentication).
3. Assume the API is invoked by multiple systems and services on behalf of end users.
4. You can use frameworks/libraries if you like (except Spring), but don't forget about
requirement #2 – keep it simple and avoid heavy frameworks.
5. The data store should run in-memory for the sake of this test.
6. The final result should be executable as a standalone program (should not require
a pre-installed container/server).
7. Demonstrate with tests that the API works as expected.

# Implicit requirements:
1. The code produced by you is expected to be of high quality.
2. There are no detailed requirements, use common sense.

# Execution :
1. Build the maven project with below command
   mvn clean install
2. Run the below command to invoke the Jetty server with embedded H2 DB.
   mvn exec:java
3. Invoke the REST api with the context path /revolut-bank to the respective resource. Below are the samples.
   http://localhost:8080/revolut-bank/payments/users
   http://localhost:8080/revolut-bank/payments/accounts
