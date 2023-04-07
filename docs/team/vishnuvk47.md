# Project Portfolio Page (vishnuvk47)

## Overview

Helped create a CLI-based application that acts like a banking app. Users can create accounts, deposit money, withdraw money, set save goal, set withdrawal limit, and view previous transactions with this application.

## Summary of Contributions

[Link to code contributed](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=vishnu&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=vishnuvk47&tabRepo=AY2223S2-CS2113-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements implemented

I have developed a 'Save Goal' feature that empowers users to adhere to a frugal lifestyle by setting a minimum balance they wish to maintain. The system sends alerts to users when a withdrawal would result in them falling below their set save goal. Although this is not a strict limit, users have the flexibility to override the system's suggestions.
The development of the 'Save Goal' feature involved integrating the use of LocalDate and closely interacting with the withdrawal feature. This relates to both front end and back end as it needs to parse the user data stored in the save file (back end) and also interact with users via the UI to receive the relevant inputs and process them(front end).

### UG contributions

Created a User Guide for the BankWithUs software/application, which included a rough template and details on the various features and their use cases. While working on the guide, I received significant assistance from my teammate, Tyuyang, who helped me improve the guide's consistency and format to align with recommended guidelines.

### DG contributions

Templated the layout and basic functionalities of the program for the DG. 

### Team-based tasks

* Set up the GitHub team org/repo
* Updated user/developer docs that are not specific to a feature e.g. documenting the target user profile
* Handled a significant number of issues that were reported in the PE-D.
* refactored the floats into Big Decimal to resolve overflow and precision issues.
