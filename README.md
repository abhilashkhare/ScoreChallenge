# ScoreChallenge

## Overview

The ScoreChallenge project is designed to automate testing for mobile applications using Appium. This repository contains test scripts for validating various functionalities of a mobile application, ensuring its reliability ]automated testing scenarios.

## Features

- Automated testing scripts for mobile applications using Appium.
- Integration with TestNG to facilitate structured testing scenarios and assertions.
- Demonstrates interactions with a mobile app, including navigation and validation of UI elements.

## Getting Started

### Rationale for test automation
Project is based on Page Object Model where tests and helper method for the page are segregated. Locators are also stored in a separate file which can help in modularization

Test steps:
User navigates to "Leagues" bottom tab and finds a list of Leagues. Name of the league is parameterized. Script scrolls the league until the end of all the available options if the League name is not displayed in the current view. Number of scrolls is not hardcoded which can be helpful when the list of leagues expands.
Per requirement, user navigates to the required league and verifies the title of the League intended to be selected. 
User then navigates to the subtab "Leaders" and verifies that the subscreen is selected and the user is still under the selected League.
Validations are performed to ensure the data displayed on the screen is available for displayed headers as a regression.
Test then clicks on "Back" button and verifies the "Leagues" menu tab is displayed 

### Prerequisites

Before running the test scripts, ensure you have the following installed:
- Java JDK (version 14)
- Maven
- Appium Server (Appium client version used are specified in pom.xml)
- Android SDK

### Installation

To set up your local environment to run the tests:

1. Clone the repository to your local machine: git clone https://github.com/abhilashkhare/ScoreChallenge.git
2. Navigate to the cloned directory: cd ScoreChallenge
3. Open the project in IntelliJ and install Maven Dependencies by: mvn install or Right click pom.xml -> Maven -> reload Project
4. Build the project

### Configuration

1. Configure your testing environment by ensuring the Appium server is running and adjusting the device capabilities in `ScoreAppTest.java` to match your device or emulator settings, such as device name, platform version
2. Pre-install the app on the device from apk file found here: https://apkpure.com/thescore-sports-news-scores/com.fivemobile.thescore/download V24.4.0
3. Perform a manual navigation to set up the app for the first time such as Getting Started Screen, Local State mandated screens(such as for CA state), Location & Notification settings. Navigate to "Leagues" bottom tab and tap "Quick Tip" pop up

### Running the Tests
Run the test using IntelliJ from class ScoreAppTest.java > verifyLeagueFunctionality test method

### Contributing
Contributions to improve the test scripts or extend the testing coverage are welcome. Please submit a pull request with your changes or open an issue to discuss what you would like to change.

### License
This project is licensed under the MIT License - see the LICENSE file for details.

### Acknowledgments
Thanks to Appium for providing the automation framework.
The testing community for sharing valuable insights and best practices.




