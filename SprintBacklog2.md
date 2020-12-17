# Second sprint: from 04/12 to 17/12

## Item 15
As a Planner
I want to create, view, modify and delete maintenance activities
So that I can manage them
#### Status
DONE
#### Dependencies
1,12
#### Story Points
8
#### Acceptance Criteria/Notes
For each activity the following data must be recorded: 
- activity ID
- site 
- typology of the maintenance activity
- activity description
- estimated intervention time
- interruptible activity
- materials needed (optional)
- week (in which the activity must be carried out)
- workspace notes (optional)
Only “workspace notes” field must be editable


## Item 11
As a System Administrator
I want to assign specific competencies to Maintenance Procedures
So that I can describe the maintenance activities
#### Status
DONE
#### Dependencies
10
#### Story Points
2
#### Acceptance Criteria/Notes
A maintenance procedure can be characterised by the needed competencies


## Item 16
As a Planner
I want to see the list of Maintenance activities scheduled in the week
So that I can select one activity
#### Status
DONE
#### Dependencies
15
#### Story Points
5
#### Acceptance Criteria/Notes
 The following information should be displayed on the screen: 
- activity ID
- area
- typology
- estimated intervention time
These fields must not be editable.
Each activity must be selectable.


## Item 17
As a Planner
I want to verify the Maintenance activities and edit the Workspace notes
So that I can check its correctness
#### Status
DONE
#### Dependencies
16
#### Story Points
2
#### Acceptance Criteria/Notes
Planner must verify week number, activity to assign, workspace notes, intervention description, SMP, competencies needed.
Just the “workspace notes” field must be editable.


## Item 18
As a Planner
I want to assign a specific activity to a specific Maintainer, according to his availability
So that I can plan the Maintenance's execution
#### Status
IN PROGRESS
#### Dependencies
17
#### Story Points
13
#### Acceptance Criteria/Notes
The following information must be displayed: 
- week number
- activity to assign
- competencies required
- the list of Maintainers
The list contains:
- Maintainer name
- Competencies compliance(achieved/required)
- Availability percentage (for each day, from Monday to Sunday)


## Item 27
As a User
I want to login in the application
So that I do my tasks 
#### Status
DONE
#### Dependencies
1
#### Story Points
3
#### Acceptance Criteria/Notes


## Item 29
#### Type 
Technical Debt
#### Description 
Tha Administrator needs to manage competencies, which will then be used in all the application.
#### Status
DONE


## Item 30
#### Type 
Technical Debt
#### Description
Refactory of data access layer, using Data Object Pattern.
#### Status
DONE
