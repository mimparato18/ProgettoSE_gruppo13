# Backlog

## Item 1
As a System Administrator
I want to create, view, modify and delete system users
So that I can manage them
#### Dependencies

#### Story Points
5
#### Acceptance Criteria/Notes
Users are characterized by a username, a password and a specific role
There are two roles: Planner and Maintainer


## Item 2
As a System Administrator
I want to assign specific competencies to Maintainers
So that I can differentiate the Maintainters
#### Dependencies
1
#### Story Points
2
#### Acceptance Criteria/Notes
Maintainers are characterized by a set of competencies


## Item 3
As a System Administrator
I want to assign a specific procedure to Maintainers
So that I can prepare for the intervention
#### Dependencies
1, 10
#### Story Points
3
#### Acceptance Criteria/Notes


## Item 4
As a System Administrator
I want to Associate a SMP to each Maintenance Procedure
So that I can Provide documentation to maintainers
#### Dependencies
10
#### Story Points
2
#### Acceptance Criteria/Notes
SMP is a file in PDF format


## Item 5
As a System Administrator
I want to create, view, modify and delete lists of competencies related to a specific task
So that I can describe the task's requirements

#### Dependencies
15
#### Story Points
3
#### Acceptance Criteria/Notes
Task are characterized by a set of competencies


## Item 6
As a System Administrator
I want to create, view, modify and delete sites
So that I can localize the maintainace activities inside the factory
##### Dependencies

##### Story Points
5
##### Acceptance Criteria/Notes
Sites are composed of:
- factory site (branch offices)
- area (or department)


## Item 7
As a System Administrator
I want to create, view, modify and delete Workspace notes
So that I can associate notes to a specific entity or a set of entity
#### Dependencies

#### Story Points
3
#### Acceptance Criteria/Notes
Workspace notes are associated to one or more sites


## Item 8
As a System Administrator
I want to record all access to the application
So that I can keep track of users'activities

#### Dependencies
1
#### Story Points
3
#### Acceptance Criteria/Notes


## Item 9
As a System Administrator
I want to create, view, modify and delete lists of materials
So that I can guide the Maintainers and keep track of the inventory
#### Dependencies

#### Story Points
2
#### Acceptance Criteria/Notes
A maintenance activity can be characterised by the needed materials 


## Item 10
As a System Administrator
I want to create, view, modify and delete lists of Maintenance procedures
So that I can guide the Maintainers
#### Dependencies

#### Story Points
5
#### Acceptance Criteria/Notes


## Item 11
As a System Administrator
I want to assign specific competencies to Maintenance Procedures
So that I can describe the maintenance activities
#### Dependencies
10
#### Story Points
2
#### Acceptance Criteria/Notes
A maintenance procedure can be characterised by the needed competencies


## Item 12
As a System Administrator
I want to create, view, modify and delete maintenance typologies
So that I can define Maintainance activities
#### Dependencies

#### Story Points
5
#### Acceptance Criteria/Notes
The typologies are electrical, electronic, hydraulic and mechanical


## Item 13
As a Planner
I want to assign a specific EWO to a specific Maintainer, according to his availability
So that I can handle the Emergency
#### Dependencies
20,21
#### Story Points
8
#### Acceptance Criteria/Notes
The information that need to be displayed are: 
- workspace notes
- competencies needed
- maintainers availability
-  EWO information


## Item 14
As a Planner
I want to send an email to the Production Manager, after assigning a Maintenance activity
So that I can notify him of the updated Maintenance schedule
#### Dependencies
13,15,18
#### Story Points
1
#### Acceptance Criteria/Notes


## Item 15
As a Planner
I want to create, view, modify and delete maintenance activities
So that I can manage them
#### Dependencies
1,12
#### Story Points
13
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


## Item 16
As a Planner
I want to see the list of Maintenance activities scheduled in the week
So that I can select one activity
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
#### Dependencies
17
#### Story Points
8
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


## Item 19
As a Planner
I want to send a notification to the Maintainer, after assigning a Maintenance activity
So that I can appoint him the Maintenance activity
#### Dependencies
13,18
#### Story Points
1
#### Acceptance Criteria/Notes


## Item 20
As a Planner
I want to verify the EWO and add missing information
So that I can fill the EWO
#### Dependencies
1,5,9
#### Story Points
3
#### Acceptance Criteria/Notes
The Planner must add:
- intervention description 
- estimated intervention time
- competencies 
- materials (optional)
Competencies field is selectable from a list of pre-existing competencies.



## Item 21
As a Planner
I want to interrupt a Maintainer's activity (if not uninterruptible)
So that I can assign him an EWO
#### Dependencies
1
#### Story Points
8
#### Acceptance Criteria/Notes


## Item 22
As a Planner
I want to create, view, modify and delete list of materials
So that I can update the Maintenance Activity's requested materials
#### Dependencies
1
#### Story Points
5
#### Acceptance Criteria/Notes


## Item 23
As a Planner
I want to see the list of assigned tickets (EWO)
So that I can check their state
#### Dependencies
1,13
#### Story Points
2
#### Acceptance Criteria/Notes
The following information should be displayed: 
- week number
- date
- EWO ID
- area
- typology 
- estimated intervention time
- ticket state.

The ticket state could be:
- Area: received, sent, not sent.
- Maintainer: sent, received, read.
- General state: not started, in progress, closed.
These fields must not be editable


## Item 24
As a Maintainer
I want to be notified when a task is assigned to me
So that I can prepare for the intervention
#### Dependencies
1,3,18
#### Story Points
1
#### Acceptance Criteria/Notes


## Item 25
As a Maintainer
I want to provide information about the EWO to which I was assigned
So that I can notify the production manager about the emergency
#### Dependencies
1,13
#### Story Points
3
#### Acceptance Criteria/Notes
The maintainer provides information about what
happened, why the breakdown occurred, what was done about it, and what can be done to prevent it.


## Item 26
As a Maintainer
I want to see the list of tasks assigned to me
So that I can prepare for the interventions
#### Dependencies
1,13,18
#### Story Points
1
#### Acceptance Criteria/Notes


## Item 27
#### Type: Technical Debt
Design a login interface to access the system with credentials


## Item 28
#### Type: Feature
Adding the possibility of checking and changing the competencies assigned to a Maintainer

Reference User Story ID: 2


## Item 29
#### Type: Feature
Design an Hub for the System Administrator to perform its task

