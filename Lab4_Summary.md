# MADT Lab 4 - Calculator App Session Summary

## <@ListOfsessions>

### Session 1: Code Generation
**User Input:** `/grill-me Please generate the code for a calculator application for Android Studio. The application must meet the following requirements: The interface should resemble a standard calculator, and the history of calculations performed during the session (runtime history) must be displayed on the screen. Basic operations: addition, subtraction, multiplication, division, square root, squaring, percentage, and reciprocal (1/x) calculation... The calculation logic must be written from scratch, without the use of any third-party libraries. Button labels must not be hard-coded; instead, they should be retrieved from the "strings.xml".`
**Outcome:** The AI generated the initial Android Studio project files (`MainActivity.java`, `activity_main.xml`, `strings.xml`) implementing the required basic and special arithmetic operations entirely from scratch.

### Session 2: Code Review & Refactoring
**User Input:** `I have written a calculator app for Android Studio. Please review this code and implement the two highest-priority changes identified during the review. Here is my code: [MainActivity.java code provided]`
**Outcome:** The AI identified two critical issues and provided refactored code to address them:
1. Implemented `onSaveInstanceState` and `onRestoreInstanceState` to prevent data and history loss during screen rotation (landscape mode).
2. Added robust error handling for "Division by Zero" scenarios inside the `deleteLastDigit()` and `toggleSign()` methods to prevent logical bugs and app crashes.

### Session 3: Documentation Generation (README.md)
**User Input:** `I want to create user documentation (README.md) for my calculator app built in Android Studio. Please write a professional README.md text covering the app's features (basic/special operations, history panel, error handling, and screen rotation support). This text will be my GitHub repository description.`
**Outcome:** The AI generated a comprehensive and accurate `README.md` file specifically tailored to the Java-based project, detailing the application's features, architecture, and installation instructions.

### Session 4: GitHub Integration
**User Input:** `My app is ready. Please give me the terminal commands to commit and push this code to my GitHub repository.`
**Outcome:** The AI provided the necessary Git terminal commands (`git add .`, `git commit -m "..."`, `git push -u origin main`) to successfully upload the project files and the subsequent code review changes to the remote GitHub repository.

---
**GitHub Repository Link:** [Bura öz GitHub reponun linkini yapışdır]