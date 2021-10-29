## Check todo list tasks

**Scenario: Check an empty todo list**

**Given** an empty Todo list  
**When** the user checks his todo list  
**Then** no task is returned

**Scenario: Check a filled todo list**

**Given** a todo list containing ‘Clean the house’ & ‘Wash the dog’  
**When** the user checks his todo list  
**Then** ‘Clean the house’ & ‘Wash the dog’ tasks are returned

## Add a task in my todo list

**Scenario: Add a task to an empty todo list**

**Given** an empty Todo list  
**When** the user add a ‘Buy cheese’ task  
**Then** the todo list contains ‘Buy cheese’

**Scenario: Add a task to a filled todo list**

**Given** a todo list containing ‘Clean the house’  
**When** the user add ‘Wash the car’  
**Then** the todo list contains ‘Clean the house’ & ‘Wash the car’

## Delete a task from my todo list

**Scenario: Delete all tasks (Clean the todolist)**

**Given** a todo list containing ‘Clean the house’ & ‘Wash the car’  
**When** the user choose to delete all tasks  
**Then** the todo list contains nothing

**Scenario: Delete a specific task**

**Given** a todo list containing ‘Clean the house’ & ‘Wash the car’  
**When** the user choose to delete ‘Wash the car’ task  
**Then** the todo list contains ‘Clean the house’

## Modify a task from my todo list

**Scenario: Modify a task**

**Given** a todo list containing ‘Clean the house’ & ‘Wash the car’  
**When** the user choose to modify ‘Clean the house’ by ‘Buy cheese’  
**Then** the todo list contains ‘Buy cheese’ & ‘Wash the car’

## Reorder a task from my todo list

**Scenario: Place a task on first position**

**Given** a todo list containing ‘Clean the house’ & ‘Buy cheese’  
**When** the user choose to put ‘Buy cheese’ on 1 position  
**Then** the todo list contains ‘Buy cheese’ & ‘Clean the house’

**Scenario: Place a task on last position**

**Given** a todo list containing ‘Clean the house’, ‘Buy cheese’, ‘Prepare coffee’ & ‘Wash the car’  
**When** the user choose to put ‘Clean the house’ on 4 position  
**Then** the todo list contains ‘Buy cheese’, ‘Prepare coffee’, ‘Wash the car’ & ‘Clean the house’

**Scenario: Place a task on second position**

**Given** a todo list containing ‘Clean the house’, ‘Buy cheese’, ‘Prepare coffee’ & ‘Wash the car’  
**When** the user choose to put ‘Wash the car’ on 2 position  
**Then** the todo list contains ‘Clean the house’, ‘Wash the car’, ‘Buy cheese’ & ‘Prepare coffee’