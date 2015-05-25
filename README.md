To start the server in console: java Server portNumber (I used 1200)

To start clients: java Client Teressa (or your username) 1200.

Once the client is logged is he can type help to display the server menu:

GET to retrieve the last sentence of a book.

WRITE to to write a sentence to a book.

STATUS to show the leaderboard of users.

EXIT to quit the game.

Once a book is complete (I specified my book size to be 3, but you can choose your own size), 10 points are added to all players.


I chose my Book structure to be a stack, thus allowing fast retrieval for the last sentence

I have also implemented a comparator to compare client score and help sort my client list accordingly.
