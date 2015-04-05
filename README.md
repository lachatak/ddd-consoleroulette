# Console Roulette DDD Implementation #
This console roulette java implementation is meant to be Domain Driven Design demo to be able to capture main concepts and suggests implementation tricks and ideas based on [Vaughn Vernon's "red book"](http://www.amazon.co.uk/Implementing-Domain-Driven-Design-Vaughn-Vernon/dp/0321834577). There are some concepts I haven't touched in this demo but I am going to call your attention possible extentions points.

## The Console Roulette ##
Roulette is a popular casino game. The aim of the exercise is to create a DDD based command line multiplayer version of the game.
On start-up, load a file which contains a list of player's names.
#### Input file 1####
```
Robert
Tomas
```
Once started, it should read lines from the console, each line will contain the player's name, what they want to bet on (either a number from 1-36, EVEN or ODD), and how much they want to bet.
#### Console input ####
```
Robert 2 1.0
Or
Tomas EVEN 3.0
```
Every 30 seconds the game should choose a random number between 0 and 36 (inclusive) for the ball to land on.
- If the number is 1-36 then any bet on that number wins, and the player wins 36x the bet's amount.
- If the number is even, any bet on EVEN wins, and the player wins 2x the bet's amount.
- If the number is odd, any bet on ODD wins, and the player wins 2x the bet's amount.
- All other bets lose.
It must be possible to place bets and choose a random number concurrently.
The game should print to the console the number and for each bet - the player's name, the bet, whether they won or lost, and how much they won.

#### Output 1 ####
```
Number: 4
Player       Bet     Outcome    Winnings
---
Robert       2       LOSE       0.0
Tomas        EVEN    WIN        6.0
```
### Optional Bonus Question ###
We'd like to print out the total amount a player has won and bet. The input file (with players' names) should have optional "total win" and "total bet" comma separated values which are the amounts player has won and bet in the past. No value should be treated as zero.
#### Input File 2 ####
```
Robert,1.0,2.0
Tomas,2.0,1.0
```
After each number is chosen print out the totals in a tabular format (after the previous output is printed):
#### Output 2 ####
```
Player       Total Win  Total Bet
---
Robert       1.0        3.0
Tomas        8.0        4.0
```
Robert brought in total win 1.0 and total bet 2.0. He bet 1.0 on number 2 and lost so his total bet has increased to 3.0.
Tomas brought in total win 2.0 and total bet 1.0. She bet 3.0 on EVEN and won 6.0. Hence her total win is now 2.0 + 6.0 = 8.0 and her total bet is 1.0 + 3.0 = 4.0.

## Technical Details ##
I am going to go step by step and show each and every important DDD aspect based the "red book".

### Architecture ###
The application's architecture follows the ***Hexagonal or Ports and Adapters*** structure. Using [Mate's rule](http://tindaloscode.blogspot.co.uk/2013/11/ddd-and-hexagonal-architecture.html) terminology for adapters to be able to meaningfully distinguish them.

#### Driving adapters####
An adapter can be regarded as a ***driving adapter*** if it feeds the domain with information which could cause a state change in the in it or simply just forces the domain to handle some stateless activity like a request.
Based on this description I have [3 driving adapters](src/main/java/org/kaloz/roulette/infrastructure/adapters/driving):
- loader: it **initialises the application** when it starts. It loads the given **player.txt** and creates players
- console: it **manages user input** and delegates input to the domain. As a result the player can bet
- scheduled: it **announces the winning number** in scheduled fashion. As a result we have process player bets

#### Driven adapters####
An adapter can be regarded as a ***driven adapter*** if it is feed by the domain with information. Ultimately almost all driving adapter calls end up in the driven adapter layer.
Based on this description I have [2 driven adapters](src/main/java/org/kaloz/roulette/infrastructure/adapters/driven):
- console: the domain is using it to **print out bet results**. As a result we see the winners
- persistence: application layer iss using it to **store domain state**. As a result we could persist domain objects

### Aggregates ###
In this simple example I have 1 aggregate root. This is the [RouletteGame](src/main/java/org/kaloz/roulette/domain/RouletteGame.java). This is used to **provide roulette game specific behaviour**.

### Value Object ###
In domain level I used [VALJO](http://waytothepiratecove.blogspot.co.uk/2015/04/valjos-value-java-object.html). They **encapsulate their own validation and creation factory methods**. They are very convenient and properly express the fact that they are **immutable**.

### Services ###
I have one stateless domain service [Croupier](src/main/java/org/kaloz/roulette/domain/Croupier.java) which **handles the game flow and organises the game itself**. He register a player, check player pets and announces the results.

### Modules ###
There are couple of simple modules in the domain. As the application very simple the solution is not overcomplicated either. The main organising concept is **not to have circular dependencies**.

### Factories ###
There are many example for this DDD principle in this example. Almost everything is created via factories.

### Anti-corruption Layer - ACL ###
ACLs main responsibility to **protect the domain from adapter layer changes**. That is why ACL lays between every adapter and the domain regardless of being a driven or driving. If any change happens in the messaging or persistence layer ACL could potentially protect the domain to leak this modification to it.

### Repositories ###
For the RouletteGame aggregate root there is a repository called [RouletteGameRepository](src/main/java/org/kaloz/roulette/domain/RouletteGameRepository.java). It is **part of the domain but the implemented in the infrastructure driven adapter layer**.
It could be used together with a **different store solution**. The store itself encapsulates a specific type of the persistence. It could be SQL, NoSQL, file or memory and so on. I have chosen a memory based solution but I store the aggregate in a unique string list form to show the concept. An **ACL stays between the repository and the store** to protect the domain from any persistence change.

### Application ###
The application layer provides functionality for **finding, updating aggregate root from the repository**, **manages transactions** or in extreme case **locking**. It could **orchestrate results** from different domain calls. I use my [RouletteGameService](src/main/java/org/kaloz/roulette/app/RouletteServiceImpl.java) to load, update the RouletteGame aggregate root and handles locking as well.

## What is missing? ##
I haven't touched the following topics in this demo:
- Domain, subdomain, bounded context. As it is a really simple example the entire application is implemented in one bounded-context.
- Context mapping
- Entities
- Domain events. One of the most exciting area. Its is quite of complex to do it properly but there are nice solutions [out there](https://github.com/matemagyari/reference-ddd-blackjack).
- Integrating bounded contexts
- Event Sourcing. It has really big potential. Recently I have seen some interesting solutions based on akka and ES.
