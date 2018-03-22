# Market Exchange Simulator #

##  Mission Statement ## 
An open source trading simulator that uses the FIX Protocol and will allow users to test market strategies.

## How it works ## 
In our main, we are feeding numbers ranging from 1 to 200 million through an exchange system. This system consists of two threads. The first thread finds what market these numbers should go to. The second thread determines if it is a prime market. In other words if we have found a prime number within our system it will be queued to the Loyola Prime Market Index or (LPX). The numbers are treated like a key value pair. The key representing a ticket number and the value is the information of the order being placed. The numbers are read in like a queue that is FIFO. The processed ordered are feed to a dequeue that are eventually redirected to a market. This is a baseline approach to find a particular difference in what is being feed through the Exchange Feed

We will eventually remove the numbers that are feed here and replace them with strings
Those strings will contain an OrderID, OrderType, OrderSize, PriceBought, PriceSold, etc.
The value we will focus on first is the OrderID's e.g. LPX (Loyola Prime Index), or AAPL, MSFT, etc. This will determine which market the string info will go to. In that Market the string will be parsed into separate variables. From there we will determine if the OrderType is a Buy/Sell, QrderSize (quantity bought/sold), PriceBought $50.25, PriceSold $52.00, etc.

Then we need to do the accounting portion of the project



### TO DO ###

* Configuration
* Dependencies
* Database configuration
* How to run tests
* Deployment instructions
* Image Placeholder

## Requirements ## 

## Installation ##


Features List:

* Provides live updates of stock exchange information
* Integrate the Financial Information Exchange Application Programming Interface to:
* Provides easy simulation of trades
* Provide string responses for stock exchange quotes for clients to trade based on

## Frequently Asked Questions (FAQ) ##

* Are there indicators provided? 	Answer: No. Possibly in the future.

## Who do I talk to? ##

* Repo owner or admin
* Other community or team contact


## IRC channel ##
* ##MES

## Development ##

* Writing tests
* Code review
* Other guidelines
* Link to Contribution.MD

## GPL v3.0 License ## 
Link to GNU General Public License