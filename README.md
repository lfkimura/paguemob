#   **REST API** that registers and lists Companies and Employees
MODEL
Company:

- Name
- CNPJ
- Telephone
- Website
- Industry

Employee:
- Name *(populated by [RANDOM USER GENERATOR][] on registration)*
- Gender *(populated by [RANDOM USER GENERATOR][] on registration)*
- Email *(populated by [RANDOM USER GENERATOR][] on registration)*
- CPF
- Employer (Company)
- Job Title
- Seed

---
Aailable through the API:

 - [X] is able to **register** a `Company`
 - [X] is able to return a list with all Companies
 - [X] is able to search for Companies whose name **contains** a specified word
 - [X] is able to return a list of Companies in a specified `Industry`
 - [X] is able to return a single `Company` with a specified `id`
 - [X] is able to return a list of employees that work at a specified `Company`
 - [X] is able to **register** an `Employee`
 - [X] is able to return a list of all Employees
 - [X] is able to return a list of Employees whose `Job Title` **contains** a specified word
 - [X] is able to return a single `Employee` with a specified `id`
 - [X] is able to return a list with all supported Industries (List can have any entries, but at least 10 pre-definied items)

---
## Stack

The development was based in
 - Spring Framework (Boot,DI, Data) with Java8
 - Gradle
 - An in-memory database H2
 - Jersey framework for JAX-RS
 - Integrations testes with Springboottest

## Bonus

 - [X] Use pagination for list results
 - [X] API tests
 - [ ] Validation errors
 - [ ] API documentation
 - [X] Live version on [Heroku](https://heroku.com)
 	URLS:
 	- https://hidden-dawn-14052.herokuapp.com/rest/company (GET,POST)
 	- https://hidden-dawn-14052.herokuapp.com/rest/company/{id} (GET)
 	- https://hidden-dawn-14052.herokuapp.com/rest/employee (GET,POST)
 	- https://hidden-dawn-14052.herokuapp.com/rest/employee/{id} (GET)
 	- https://hidden-dawn-14052.herokuapp.com/rest/industry (GET)
 
 	- https://hidden-dawn-14052.herokuapp.com/rest/industry
 - [ ] Authentication using OAuth2 and Spring Security
