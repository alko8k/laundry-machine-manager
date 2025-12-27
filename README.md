# My Personal Project : First Year Residence Laundry Manager

## Overview:
The **First Year Residence Laundry Manager** is an app to help Totem Park Qalaxan residents with their laundry room. The app will enable students to view the status of each washing machine, reserve a machine for a specific time slot, and track the number of machines currently available. It will also include a countdown timer for machines in use, allowing users to check the remaining time in a cycle and plan accordingly. I also want to add a notification system where the app will notify 5 minutes before your cycle is done and notify you when your cycle is done so if the user has lost track of time they can be reminded and plan accordingly. This application is designed for *students living in first-year residences*. I chose this project because I have personally experienced the chaos and disorganization that can be associated with laundry situations. Many times, I carried my laundry down only to find that no machines were available, or I had to wait because someone had forgotten their clothes, even after the timer had ended. By creating this project, I aim to design a system that relieves the stress of doing laundry and makes laundry more predictable and efficient for students like myself. 

## User Stories (features list):
- *As a user*, I want to view the list of washing machines and their availability in Totem Park Qalaxan residence.
- *As a user*, I want to add a washing machine with id to the list of washing machines.
- *As a user*, I want to remove a broken (OUT_OF_ORDER) washing machine with id to the list of washing machines.
- *As a user*, I want to reserve a machine for a specific empty time slot in the list with my id.
- *As a user*, I want to unreserve a machine for a specific empty time slot in the list with my id.
- *As a user*, I want to see whether a machine is out of order or not and if it is not be able to schedule it.
- *As a user*, before I quit the application I want the option to be able to save my LaundryRoom to file.
- *As a user*, when I start the apploication I want to be given the option to load my LaundryRoom list from file.


# Instructions for End User

- You can view the panel that displays the washing machines that have already been added to the LaundryRoom.
- You can generate the first required action related to the user story adding a washing machine to the LaundryRoom by clicking "Add Machine" and entering the machines id.
- You can generate the second required action related to the user story 
- You can locate my visual component by pressing on the washing machine icon.
- You can save the state of my application by  clicking the 'save' button.
- You can reload the state of my application by clicking the 'load' button.


# Phase 4 : Task 3
If I had more time to work on this project, one improvement that I think I could make is applying the observer pattern to separate the ui from the model. Right now, both the console and the GUI versions must manually refresh or reload the stats from the laundry room and laundry machine after every action. If the model classes notified observers whenever something changed, then the GUI could automatically update without manually calling refreshList. This would reduce coupling and make it easier to add more interfaces in the future.

Another change that could be worth doing if I had more time to work on this project would be using a composite pattern. Right now, LaundryRoom holds a flat list of machines, but if the system ever grows, for example, more floors or multiple laundry rooms or machines grouped together, the composite pattern would make it possible to treat both single machines and collections uniformly. Even if the project stays small, organizing machines under composite-like structures can improve clarity and reduce responsibilities within LaundryRoom. Finally, I think that there could be a dedicated controller class that I could factor out things from LaundryRoomGui and LaundryApp. This would reduce duplication between the two.



# Phase 4 : Task 2
- Thu Nov 27 22:33:36 PST 2025
Machine 10 reserved by 12
- Thu Nov 27 22:33:39 PST 2025
Machine 10 started wash cycle
- Thu Nov 27 22:33:41 PST 2025
Machine 10 ended wash cycle
- Thu Nov 27 22:33:48 PST 2025
Added a new machine with ID : 1928
- Thu Nov 27 22:33:52 PST 2025
Machine 1928 reserved by 12123
- Thu Nov 27 22:33:55 PST 2025
Reservation cancelled for machine 1928
- Thu Nov 27 22:33:59 PST 2025
Saved laundry room to file
- Thu Nov 27 22:34:06 PST 2025
Machine 1928 status set to RESERVED
- Thu Nov 27 22:34:11 PST 2025
Machine 1928 status set to OUT_OF_ORDER
- Thu Nov 27 22:34:12 PST 2025
Removed machine with ID : 1928
- Thu Nov 27 22:34:15 PST 2025
Saved laundry room to file
- Thu Nov 27 22:34:17 PST 2025
Loaded laundry room from file
- Thu Nov 27 22:34:17 PST 2025
Machine 1 status set to AVAILABLE
- Thu Nov 27 22:34:17 PST 2025
Machine 2 status set to AVAILABLE
- Thu Nov 27 22:34:17 PST 2025
Machine 3 status set to AVAILABLE
- Thu Nov 27 22:34:17 PST 2025
Machine 4 status set to AVAILABLE
- Thu Nov 27 22:34:17 PST 2025
Machine 5 status set to AVAILABLE
- Thu Nov 27 22:34:17 PST 2025
Machine 6 status set to AVAILABLE
- Thu Nov 27 22:34:17 PST 2025
Machine 7 status set to AVAILABLE
- Thu Nov 27 22:34:17 PST 2025
Machine 8 status set to AVAILABLE
- Thu Nov 27 22:34:17 PST 2025
Machine 9 status set to AVAILABLE
- Thu Nov 27 22:34:17 PST 2025
Machine 10 status set to AVAILABLE