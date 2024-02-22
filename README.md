# SauceDemo: Selenium with Java Test Automation Framework

Hi There! 

This project documents my journey in learning Selenium with Java and showcases a robust test automation framework designed for www.saucedemo.com.

SauceDemo is a sample e-commerce web application, providing users with the opportunity to explore and interact with various features commonly found on an online shopping website.

The framework is structured with a keen focus on modularity, maintainability, and versatility.

## Project Structure

Organized with clarity, the project is divided into separate packages for essential components:

### `src/main/java`

- **AbstractComponents:** Contains abstract classes or interfaces defining common components or behaviors shared across different parts of the application.

- **Pages:** Implements the Page Object Model (POM) with dedicated classes for each web page.

- **Resources:** Houses resources used by the application, such as configuration files. Global data shared across different components is stored here.

### `src/test/java`

- **Test:** Contains diverse test cases covering various scenarios on SauceDemo.

- **TestComponents:** Holds test-specific abstract classes or components that are reused across different test classes.

- **TestData:** Intended to store data used in tests, such as test input data, expected results, or any other relevant data.

- **Utilities:** Houses utility classes for data-driven testing and other functionalities.

## Key Features

### Page Object Model (POM)

The framework leverages the Page Object Model for creating maintainable and modular code. Each web page is represented by a distinct class within the `Pages` package, encapsulating elements and associated actions.

### Test Cases

The test suite encompasses positive and negative test cases, showcasing the framework's versatility. TestNG is employed for effective test case management and execution.

### Data-Driven Testing

Proficiency in data-driven testing is demonstrated by reading test data from external sources such as properties files. This allows the same test case to run with different sets of input data, enhancing test coverage.

### Logging and Reporting

To improve traceability and comprehension, the project incorporates logging to capture important events and information during test execution. Additionally, a robust reporting mechanism, such as Extent Reports, is integrated to generate comprehensive and visually appealing test reports.

## Test Scripts

You can find the manual test scripts that I wrote in [SauceDemo Test Scripts](https://docs.google.com/spreadsheets/d/10LuKWl6Zu-VmP7f06Y-976lba1XM7Zjb7WT17w1uurE/edit?usp=sharing)

## Usage

1. Clone the repository.
2. Set up dependencies using Maven.
3. Configure environment-specific settings
4. Run test cases using TestNG.

Please take note that this is work-in-progess and will be updated from time to time.
Feel free to explore the codebase and let me know if the any improvement can be made.

Thanks!

-nurfatin a.s-
