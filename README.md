# Book Store

Server side java code for modeling case studies.

## About

This code imitates server side core functionality of a bookstore chain. It is not a complete implementation of an operational bookstore, but it provides entry points to imitate server data interactions.  
The following scenario / functionality is covered:

 * The bookstore indexes books by ISBN. A book is only known to the bookstore if it has been indexed.  
 All indexed books have:
   * A price in CAD
   * A title
   * An author
   * An abstract / description
 * The bookstore chain has agencies in different cities.
   * There can not be two agencies in the same city
   * Each city has a stock, that tells the amount in store for the indexed books.
 * The bookstore chain stores reader feedback for books (comments)
   * Comments are anonymous and have no author
   * Only indexed books can be commented
   * Comments can be updated
   * Comments must not be empty

## Documentation

 * For a full description of available functionality, read the [JavaDoc](JavaDoc/index.html)
 * The relevant functionality is exposed by three interfaces:
    * ```Assortment.java```, to access indexed books / index new books.
    * ```GlobalStock.java```, to query and update the stock of individual agencies.
    * ```Comments.java```, to add delete and edit comments on books.

## Usage

On execution, the bookstore will simply print a listing of persisted initial dummy data.

 1. Compile the bookstore with maven. This provides you with a self contained ```jar``` in the ```target```folder.  
```mvn clean package```
 
 2. Run the jar with:  
 ```java -jar target/BookStore.jar```

## Contact / Pull Requests

 * Author: Maximilian Schiedermeier ![email](email.png)
 * Github: Kartoffelquadrat
 * Webpage: https://www.cs.mcgill.ca/~mschie3
 * License: [MIT](https://opensource.org/licenses/MIT)

