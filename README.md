<div align="center"><img width="1265" alt="Capture d’écran 2021-08-29 à 20 17 36" src="https://user-images.githubusercontent.com/25029077/131261334-8307d9ab-5dd5-49c1-9ae0-4489ce217ea8.png"></div>
<p align="center">Spring project initialization.</p>

<p align="center"><b>Objective of this sandbox is to practice behaviour driven development ! 🚀</b></p>

# First steps

We will start with a basic todo list application.

## Features

1. See todo list tasks
2. Add a task in my todo list
3. Delete a task from my todo list
4. Modify a task from my todo list
5. Move a task from my todo list

### See todo list tasks

**Scenario: See an empty todo list**

**Given** an empty Todo list  
**When** I check my todo list  
**Then** no task is displayed  

**Scenario: See a filled todo list**

**Given** a todo list containing ‘Clean the house’ & ‘Wash the dog’  
**When** I check my todo list  
**Then** ‘Clean the house’ & ‘Wash the dog’ tasks are displayed  


### Add a task in my todo list

**Scenario: Add a task to empty todo list**

**Given** an empty Todo list  
**When** I add a Todo for ‘Buy cheese’  
**Then** only that item is added to my todo list  

**Scenario: Add several tasks to empty todo list**

**Given** an empty Todo list  
**When** I add Todos for ‘Buy cheese’ & ‘Wash the car’  
**Then** only those items are added to my todo list  

**Scenario: Add several tasks to todo list**

**Given** a todo list containing ‘Clean the house’  
**When** I add Todos for ‘Buy cheese’ & ‘Wash the car’  
**Then** my todo list is composed of ‘Clean the house’, ‘Buy cheese’  & ‘Wash the car’   