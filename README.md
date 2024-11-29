# My Personal Project - Habit Tracker

## Gamify your life 

This project will be a habit tracker that will allow users to set goals, input their activities and monitor their progress in acheiving those goals. The hope for this project is that it will make creating habits a charming and rewarding process. Rather then tracking just tracking the days a habit has been completed, the app will have features of video games to track progress. 

*These features include:* 
- An **XP bar** to track overall progress
- Small **XP boosts** each time a habit is completed
- **Level-Ups** once goals are met and the bar is filled 

Upon setting goals, you will be required to set small milestones in between your end goal and your beginning. This will allow for lots of little level ups along the way to make a rewarding and encouraging experience for the user. 

This app could be used by people of all ages, but will most likely be attractive to people who are current or past gamers, as the app will have a retro video game theme. It is intended for people who would like to better themselves by creating good habits, learn new skills or implement disipline into their life.

This project is of interest to me because I have struggles to keep a consistent routine throughout my life. There have been times where I have tried to learn a new skill or do well in school, but I was not consistent with my approach to acheiving these goals which introduced a lot of stress and decreased my preformance. I have always wanted to try habit trackers but I encountered a pay wall too many times. I think this will be a great way to approach reaching my goals with a light hearted and fun flair to it. 

## User Stories 

- As a user, I want to be able to create a habit and add it to a list of habits
- As a user, I want to be able to view a word of encouragement when I complete a habit
- As a user, I want to be able to add small milestones to my big picture goals
- As a user, I want to be able to view a list habits I am currently working on.  
- As a user, I want to be able to view a list of habits I completed today. 
- As a user, I want to be able to create different categories of habits. 
- As a user, I want to have the option to save my Habits and my progess. 
- As a user, I want to be able to load my Habits from file. 


# Instructions for End User

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking Create New Habit Button. Which will 
add the button do your habit list. It will also show a subset of all habits 
as your todo habits.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking View All Habits button to see all of the habits within your tracker, including those which having been removed from todo because you have clicked Record Habit button. 
- You can locate my visual component by Looking in the bottom right corner of app. The xp boost animation will play when Record Habit button is pressed. Additionally, upon reaching your set goal, you will see the reward message displayed on the panel. 
- You can save the state of my application by clicking the exit button on the window. User will see a pop up window with the option to save. 
- You can reload the state of my application by opening the app, user will be asked if they want to reload their saved progress. 

# Phase 4: Task 2
Tue Nov 26 22:57:06 PST 2024
New Habit: run was added to your habits.


Tue Nov 26 22:57:16 PST 2024
New Habit: study was added to your habits.


Tue Nov 26 22:57:19 PST 2024
Habit: study recorded. Current progress: 1


Tue Nov 26 22:57:19 PST 2024
Habit: study was added list of completed habits.


Tue Nov 26 22:57:22 PST 2024
Habit: run recorded. Current progress: 1


Tue Nov 26 22:57:22 PST 2024
Habit: run was added list of completed habits.


Tue Nov 26 22:57:22 PST 2024
Reward message: zoooom was displayed to user.


Tue Nov 26 22:57:25 PST 2024
Habit: run was removed from your habits.


Tue Nov 26 22:57:26 PST 2024
Habit: study was removed from your habits.

# Phase 4: Task 3

When creating my project, I originally was intending to make a habit tracker with different types of habits. Currently, with the SimpleHabit subtype, the only type of habit in my program is a daily habit. I would like to be able in the future to add more Habit subtypes for different activities. For example, habits that are measured in quantity of times spent doing them, or in some other sort of measurement (e.i. kilometers, calories, liters, etc.). In terms of creating new habits, the project is already in a good place, as their Abstract Habit class will provide them with the basic elements of a habit, and then they will be able to add their customized functionality within their subclasses. Some refactoring that I will need to do will be involved with how incremented a habit's progress will be called from other classes within the program. Currently, while the methods that add progress to the habit take a parameter of type Habit, their implementation is still designed to expect a SimpleHabit. For a new habit that, for example, adds progress by inputting how many kilometers a user ran, I would need to make the increment habit methods allow an additional parameter for the measurement the user is inputting. To support this, I could possibly create a Measurements SuperClass and create some subclasses implementing this to represent common forms of measuring progress in various activities. With the increment methods now taking an additional parameter of type Measurement, I could add switch cases for different types of Habits. In the case for SimpleHabits and other habit types that don't require this additional parameter when incrementing progress, I could make a method that just has the parameter habit and then makes a call to the actual increment methods while passing in null as the measurement parameter. To support this, I would need a switch case to test for null.

Another area for refactoring is with how my program deals with the different statuses of habits. I made the program so that the TODO, complete, and All habits, are all habits on separate lists. This has created a lot of redundancy in my code, as there are many occasions where I have created basically to the same methods to operate on the lists, with the sole difference being which list they operate on. I could perhaps make a Superclass for HabitLists with the methods that they all use in common, then find ways to pass the correct list as an argument to the methods from within the other classes. Another change I could make is adding a status field to the habits, which could be more simple than having a todo, complete, and all habits list in the first place.
