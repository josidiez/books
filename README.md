# Books recomendation example


### BUILD PROJECT

to build the project on project directory excute gradlew script
```sh
$ git clone https://github.com/josidiez/books.git
$ cd books
$ ./gradlew clean build
```
If you already have Gradle installed, you dont need you use the wrapper

### RUN

```sh
$ ./gradlew bootRun
```
or with jar file
```sh
$ java -jar books.jar
```



### Testing
Testing include test for the api: enconding, format, result. Also include some on the bussiness layer and repository mocking google api and returning FAKE results. It could be some other more but, hell year ! is enough for just an example.

Spock testing is compatible with JUnit. So we can directly execute test on editor JUnit environment (Eclipse)

### API Documentation
Swagger have been added into the project, so API documentation and definition can be check at:
```sh
http://localhost:8085/swagger-ui.html
```
### Format Result
The result it's limited to 10 RESULTS.
Using your REST Client just call http://localhost:8085/books?term=house 
and the result would be like above
```json
[
  {
    "id": "jWSAbe34qI4C",
    "title": "House",
    "link": "https://www.googleapis.com/books/v1/volumes/jWSAbe34qI4C"
  },
  {
    "id": "hk6mWENqN3oC",
    "title": "How to Buy a House in California",
    "link": "https://www.googleapis.com/books/v1/volumes/hk6mWENqN3oC"
  },
  {
    "id": "lptdDwPrPAkC",
    "title": "The assertion is, that the title of the House of Hanover to the Succession of the British Monarchy ... is a title Hereditary, and of Divine Institution",
    "link": "https://www.googleapis.com/books/v1/volumes/lptdDwPrPAkC"
  },
  {
    "id": "IuRCAQAAMAAJ",
    "title": "The Assertion Is, That the Title of the House of Hanover to the Succession of the British Monarchy (on Failure of Issue of Her Present Majesty) is a Title Hereditary and of Divine Institution",
    "link": "https://www.googleapis.com/books/v1/volumes/IuRCAQAAMAAJ"
  },
  {
    "id": "4iglAQAAIAAJ",
    "title": "Journal of Proceedings",
    "link": "https://www.googleapis.com/books/v1/volumes/4iglAQAAIAAJ"
  },
  {
    "id": "n82mPQAACAAJ",
    "title": "The House of Bernarda Alba",
    "link": "https://www.googleapis.com/books/v1/volumes/n82mPQAACAAJ"
  },
  {
    "id": "eiS9sOjyL8MC",
    "title": "Code of Federal Regulations, Title 13",
    "link": "https://www.googleapis.com/books/v1/volumes/eiS9sOjyL8MC"
  },
  {
    "id": "5OJFAQAAIAAJ",
    "title": "Titles of completed theses in home economics and related fields in colleges and universities of the United States",
    "link": "https://www.googleapis.com/books/v1/volumes/5OJFAQAAIAAJ"
  },
  {
    "id": "PLolAQAAIAAJ",
    "title": "Journal of the House of the General Assembly of the State of Iowa",
    "link": "https://www.googleapis.com/books/v1/volumes/PLolAQAAIAAJ"
  },
  {
    "id": "Vz1IAQAAMAAJ",
    "title": "Journals of the House of Commons",
    "link": "https://www.googleapis.com/books/v1/volumes/Vz1IAQAAMAAJ"
  }
]
```


### api key and name

This example include credentials obtained from a random repo on github. If any error is detected on credential it's only necesary to change api.properties with a new one.


### Production environment
And the last thing I almost forgot. For production environment we should add profiles to our properties adding environments. Example on spring:

```
---

spring:
  profiles: dev
  

---
```
For an apropiate production environmet this properties should be on diferent files on a git repository. The service should be stateless and shouldn't be necesary to re deploy because of a propertie change. Using a configuration server service this issue should be addressed.


