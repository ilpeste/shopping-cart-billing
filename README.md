Demo application for calculating a bill
-----------------------------
Simple service for simulating a shopping cart billing system.

#### INSTRUCTIONS 
This is a Maven project with JUnit4/ test suite.

After taking the checkout, build it with maven using following command

mvn clean package

then run tests and for code coverage reports run the following command

mvn cobertura:cobertura 

Go to <workspace>/shopping-cart-billing/target/site/cobertura and open index.html, the complete code coverage report can be seen in the browser.

#### COMMAND LINE
The project can also read a CSV file (an example is in the example-input-file folder) via command-line and print the bill/receipt. 
Run the following command to launch the CLI (note that the jar will be in the target folder of the project) : 

java -jar billing-jar-with-dependencies.jar <CSV input file path>


TODO
-----------------------------
* add a simple CLI interface which parse a CSV and print the output
* correct typos and grammar mistakes in comment... 

#### NEED FURTHER ANALYSIS
-----------------------------
* Should I use BigDecimal instead of Double for representing the currency (especially for rounding issues with cents)?
* Can discount policies be added/removed runtime? (Evaluate with the client because these will affect the list returned by the service)
* Should Item.Type enum be customizable? 
* Should ReceiptManager be a singleton class?
 