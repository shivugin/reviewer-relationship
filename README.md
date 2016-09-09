# reviewers-relationship-printer

In order to develop the solution and for simplicity purposes, we assumed that there is not cyclical references (child can’t review any of its hierarchical parents in any transitive way) in the input data, and every child can only have one parent.

The solution was developed as a simple java application that read data from a text file and show the hierarchical information on the command line interface.

The project consists in 4 core classes (GenericReviewerHierarchyClient, ReviewerHierarchyConsoleClient, ReviewerHierarchyBuilder, ReviewerNode), 1 main class for the application starting point (ReviewersRelationshipPrinter), and 1 test class (ReviewerHierarchyBuilderTest).

The application can be executed in two ways:

  •	Put the “reviewers-and-reviewees.txt” text file in the same place as the .jar obtained when you build the application, open the CLI, go to the .jar location, execute the .jar and enter the text file name.
  
  •	Open the CLI, go to the .jar location, execute the .jar and enter the absolute path for the text file.
