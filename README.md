# Vending Machine Console Application


Vending Machine Console Application based on Spring Boot, Spring data JPA, Maven and H2 in-memory database.


## Contents

1. [Features](#features)
2. [Requirements](#requirements)
3. [How to run the application](#how-to-run-the-application)
4. [Add category command example](#add-category-command-example)


## Features

* Based on Spring boot, Spring data JPA, Maven and H2 in-memory database
* Has following API:
    * Add category (addCategory) `addCategory` console command — register a snack category into the database. Endpoint accepts input with the following fields: `name, price, amount`. You can enter the amount optional, otherwise it will be set at 0.
    * Add item (addItem) `addItem` console command — register provided amount of snack items to sell `name, quantity`
    * Purchase (purchase) `purchase` console command - purchase a single snack item `name, date`
    * List (list) `list` console command - show list of served categories with amount of items available for sale sorted by amount
    * Clear (clear) `clear` console command — stop serving all snack categories that don’t have items for sale (items can not be purchased)
    * Report by date (report) `report` console command — show earnings by category gained since provided date till now sorted by category name. Command accepts the following parameters:`YYYY-MM-DD`
    * Report by month (report) `report` console command - show earnings by category in specified month. Command accepts the following parameters: `YYYY-MM`
* Input validation of `command`, `YYYY-MM-DD`, `YYYY-MM`
* Unit tests

## Requirements

- For building and running the application you need:
    - [Git](https://git-scm.com)
    - [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
    - [Maven 3](https://maven.apache.org)
    - [IntelliJ Idea](https://www.jetbrains.com/idea/)

## How to run the application
- To run this application you have to :
```bash
# Create directory
$ mkdir VendingMachine
$ cd VendgingMachine
# Clone this repository
$ git clone https://github.com/Chebamba/VendingMachine.git
# Go into the repository
$ cd VendingMachine
# Run this class "VendingMachineApplication" in IntelliJ Idea
```
### There is another way to run this project
```bash
# Write this command in terminal:
$ mvn springboot:run
```

## Add category command example:
```
addCategory "Ice Cream" 2.10 10
or
addCategory "Ice Cream" 2.10
```
