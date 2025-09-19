# Demo Test Automation

This project is a **Java Selenium Test Automation Framework** built using **Maven**.  
It demonstrates a modular automation architecture with data-driven testing, rich reporting, and CI/CD integration using Jenkins.

---

## 📂 Project Structure
```
Demo_Test_Automation
│
├─ src
│  ├─ main
│  │   └─ java
│  │       └─ client
│  │           ├─ common
│  │           │   ├─ BaseClass
│  │           │   ├─ ClientTestValues
│  │           │   ├─ ClientUrl
│  │           │   ├─ Colors
│  │           │   ├─ DataGenerator
│  │           │   ├─ DateUtils
│  │           │   ├─ DriverClass
│  │           │   ├─ ExcelDataReader
│  │           │   ├─ LoginCreds
│  │           │   └─ ScreenshotUtility
│  │           │
│  │           ├─ common.extentreport
│  │           │   ├─ ExtentManager
│  │           │   ├─ ExtentTestManager
│  │           │   └─ TestListener
│  │           │
│  │           ├─ common.reportportal
│  │           │   ├─ BaseTestNGListener
│  │           │   ├─ ReportPortalConfig
│  │           │   ├─ ReportPortalHelper
│  │           │   ├─ ReportPortalLog4j2Appender
│  │           │   ├─ ReportPortalManager
│  │           │   ├─ ReportPortalTestListener
│  │           │   ├─ ReportPortalTestNGListener
│  │           │   └─ Util
│  │           │
│  │           └─ pages
│  │               ├─ CheckOutPage
│  │               ├─ ClientHomePage
│  │               ├─ LoginPage
│  │               └─ YourCartPage
│  │
│  └─ resources
│      ├─ log4j2.xml
│      └─ reportportal.properties
│
├─ test
│   └─ java
│       └─ client.test
│           └─ DemoTest
│
├─ TestData
│   └─ TestData.xlsx
│
├─ TestReport
│   └─ ExtentReport.html
│
├─ target
├─ pom.xml
├─ testng.xml
├─ Jenkinsfile
└─ Jenkinsfile2
```

---

## ⚡ Key Features
- **Selenium WebDriver (v4.22.0)** for browser automation.
- **TestNG (v7.10.2)** for test execution and parallel runs.
- **Apache POI (v5.2.3)** for Excel data-driven testing.
- **Extent Reports (v5.0.9)** for interactive HTML reporting.
- **ReportPortal Integration** for real-time reporting and analytics.
- **Log4j2 (v2.17.0)** for centralized logging.
- **Lombok (v1.18.22)** to reduce boilerplate code.

---

## 🛠️ Prerequisites
- **Java 17** (or higher)
- **Maven 3.6+**
- IDE: IntelliJ IDEA or Eclipse
- Browser drivers (managed automatically via WebDriverManager if configured)

---

## ⚙️ Setup & Installation
1. Clone the repository:
   ```bash
   git clone <repository_url>
   cd Demo_Test_Automation
   ```
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Update `reportportal.properties` with your ReportPortal credentials and URLs (if using ReportPortal).

---

## ▶️ Running Tests
Run tests via **Maven**:
```bash
mvn clean test
```

Or specify the TestNG suite:
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

---

## 📊 Reports
- **ExtentReport.html** is generated under `TestReport/` after execution.
- **ReportPortal** results can be viewed on the configured ReportPortal server.

---

## 📁 Test Data
- Test data is stored in `TestData/TestData.xlsx`.
- The `ExcelDataReader` utility reads data dynamically during test execution.

---

## 🏗️ CI/CD
- **Jenkins** integration is supported using the included `Jenkinsfile` and `Jenkinsfile2`.
- Execute automated tests as part of a pipeline and publish reports to Jenkins or ReportPortal.

---

## 🔧 Maven Dependencies
Key dependencies from `pom.xml`:
- `selenium-java` – Browser automation
- `testng` – Test framework
- `poi` & `poi-ooxml` – Excel data handling
- `extentreports` – HTML reporting
- `log4j-api` & `log4j-core` – Logging
- `lombok` – Boilerplate reduction
- `reportportal` client & agents – Real-time reporting

---

## 📌 Notes
- Update `log4j2.xml` to configure logging levels and patterns.
- Modify `testng.xml` to add or remove test classes and control parallel execution.
- Use `DriverClass` for WebDriver initialization and teardown.

---

## 👨‍💻 Author
Demo project created to showcase a **Java Selenium Automation Framework**  
with reporting and CI/CD capabilities.

---
