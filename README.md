# HabitTracker

A Java console habit tracker for daily discipline and progress control.

The app lets users add habits, set priorities, mark habits as completed, filter habits, search by name, and view daily progress statistics.

## Features

- Add habits
- Set priority: `High`, `Medium`, `Low`
- Mark habits as completed
- Show all habits
- Show completed habits
- Show not completed habits
- Filter habits by priority
- Search habits by name
- Calculate completion percentage
- Classify the day: `Perfect`, `Strong`, `System`, `Recovery`, `Zero`
- Validate user input
- Menu-based console navigation

## Tech Stack

- Java
- OOP
- Collections (`ArrayList`)
- Enums
- Methods
- Input validation
- Git / GitHub

## Project Structure

```text
src/
  Main.java
  Habit.java
  HabitService.java
  HabitPrinter.java
```

## Example Output

```text
1. Show all habits
2. Show done habits
3. Show not done habits
4. Mark habit completed
5. Search habit
6. Filter by priority
7. Show stats
0. Exit
```

## Goal

This project is part of my Java backend roadmap.

Current focus:

```text
Java Core → Collections → Exceptions → Files → Maven → JUnit → SQL → Spring Boot
```

The goal is to practice clean Java logic, OOP structure, collections, validation, and console application flow before moving into Maven, testing, databases, and Spring Boot.

## Roadmap

- [x] Java Core basics
- [x] OOP model with `Habit`
- [x] Priority enum
- [x] ArrayList storage
- [x] Menu navigation
- [x] Search and filters
- [x] Input validation
- [ ] Save habits to file
- [ ] Load habits from file
- [ ] Add exception handling
- [ ] Convert to Maven project
- [ ] Add JUnit tests
- [ ] Move toward Spring Boot REST API
