## Description
This is a sample gam written in java to illsutrate how we can make use of multithreading  and sockets.
The purpose of the game is simple, every player gets to write one sentence to a book based on the last sentence that was written.
He can read only read the last sentence that has been written.
Once a book is complete 10 points are added to all players, and a new book is created.
The user can also display a leaderboard of scores.

## Compile

Compile on terminal using

```
$ javac FixedStack.java Client.java Server.java
```
## Run
To start the server run:

 ``` $ java Server portNumber ``` (I used 1200) 

To start a sample client:

 ``` $ java Client Teressa (or your username) 1200 ``` 

Once the client is logged is he can type HELP to display the server menu:

GET to retrieve the last sentence of a book.

WRITE to to write a sentence to a book.

STATUS to show the leaderboard of users.

EXIT to quit the game.

Once a book is complete (I specified my book size to be 3, but you can choose your own size), 10 points are added to all players.

## Design choices
I chose my Book structure to be a stack, thus allowing fast retrieval for the last sentence

I have also implemented a comparator to compare client score and help sort my client list accordingly.
