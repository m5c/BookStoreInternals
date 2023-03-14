# Book Store

Legacy java code of a playground Book-Store for academic case studies.

![version](https://img.shields.io/badge/version-1.5-brightgreen)
![coverage](https://img.shields.io/badge/coverage-97%25-brightgreen)
![building](https://img.shields.io/badge/build-passing-brightgreen)

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

 * For a full description of available functionality, read the [JavaDoc](https://m5c.github.io/BookStoreInternals/eu/kartoffelquadrat/bookstoreinternals/package-summary.html)
 * The relevant functionality is exposed by three interfaces:
    * [```Assortment.java```](https://m5c.github.io/BookStoreInternals/eu/kartoffelquadrat/bookstoreinternals/Assortment.html), to access indexed books / index new books.
    * [```GlobalStock.java```](https://m5c.github.io/BookStoreInternals/eu/kartoffelquadrat/bookstoreinternals/GlobalStock.html), to query and update the stock of individual agencies.
    * [```Comments.java```](https://m5c.github.io/BookStoreInternals/eu/kartoffelquadrat/bookstoreinternals/Comments.html), to add delete and edit comments on books.

## Usage

On execution, the bookstore will simply print a listing of persisted initial dummy data.

 1. Get the sources:  
```git clone https://github.com/kartoffelquadrat/BookStoreInternals.git```

 2. Compile the project with maven. This provides you with a self contained ```jar``` in the ```target```folder:  
```mvn clean package```
 
 3. Run the jar:  
 ```java -jar target/BookStore.jar```

## Invokation

If you want to use the bookstore as dependency:

 * First compile it with:  
```mvn clean install```

 * Then reference it, using this dependency block  
```xml
   <dependency>
       <groupId>eu.kartoffelquadrat</groupId>
       <artifactId>bookstoreinternals</artifactId>
       <version>1.3</version>
   </dependency>
```

## Contact / Pull Requests

 * Author: Maximilian Schiedermeier ![email](email.png)
 * Github: m5c
 * Webpage: https://www.cs.mcgill.ca/~mschie3
 * License: [MIT](https://opensource.org/licenses/MIT)

