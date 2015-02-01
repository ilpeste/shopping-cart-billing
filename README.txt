Demo application for calculating a bill
-----------------------------
Simple CLI app for simulating a shopping cart billing system.


INSTRUCTIONS
-----------------------------
Maven application with JUnit4 test suite


TODO
-----------------------------
- ROUNDING FUNCTION TO 0.05 for the total
- better rounding when appling discounts rate
- add more and more and more tests
- better use of junit
- add cobertura suite
- implement XMLDiscountStrategyService (but is this really needed for the demo?)
- add a simple CLI interface which parse a CSV and print the output
- correct typos and grammar mistakes in comment... 

NEED FURTHER ANALYSIS
-----------------------------
- Should I use BigDecimal instead of Double for representing the currency (especially for rounding issues with cents)?
- Can discount policies be added/removed runtime? (Evaluate with the client because these will affect the list returned by the service.
- Should Item.Type enum be customizable? 
- Should DiscountStrategyManager be a singleton class?
 