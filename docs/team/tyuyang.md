# Project Portfolio Page (tyuyang)

## Overview

Helped create a CLI-based application that acts like a banking app. Users can create accounts, deposit money, withdraw money, set save goal, set withdrawal limit, and view previous transactions with this application.

## Summary of Contributions

[Link to code contributed](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=T13-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=tyuyang&tabRepo=AY2223S2-CS2113-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements implemented

Created the withdrawal limit feature. Since this feature required the tracking of withdrawals in a month, a separate feature that tracks how much money has been withdrawn in the current month had to be created. In addition, the use of LocalDate also had to be integrated into the feature, which adds to the complexity of this feature. This feature also heavily interacts with the withdraw feature, which meant that bugs that arose from integration needed to be prevented. In conclusion, this feature does not really have any bugs (aside from limit overflow) and works as expected in all tested situations.

### UG contributions

Authored the withdrawal limit section. Worked with [vishnuvk47](https://github.com/vishnuvk47) to add examples of expected program behaviour.

### DG contributions

Created the puml diagrams for the main architecture and the example sequence diagram.


### Team-based tasks

Helped in creating issues for the team to work on, especially at the start of the project. 

Did extensive bug testing for version 2.0 with [manushridiv](https://github.com/manushridiv) and published them as issues. Almost all of the functionality bugs found in the PED were already pointed out with the exception of [issue #123](https://github.com/AY2223S2-CS2113-T13-3/tp/issues/123), [issue #114](https://github.com/AY2223S2-CS2113-T13-3/tp/issues/114) and [issue #127](https://github.com/AY2223S2-CS2113-T13-3/tp/issues/127). 

Helped restructure and clean up the code in [this PR](https://github.com/AY2223S2-CS2113-T13-3/tp/pull/48). 


Reviewed and approved a significant number of PRs not authored by myself.
