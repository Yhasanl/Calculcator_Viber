# Calculator_Viber

A functional, intuitive, and responsive calculator application built for Android using Java and Android Studio. Designed to handle everyday arithmetic, special mathematical operations, real-time error handling, and seamless screen orientations without the use of any third-party libraries.

## 🚀 Features

### 🧮 Core & Special Operations
- **Basic Arithmetic:** Addition, subtraction, multiplication, and division.
- **Advanced Calculations:** Percentage (%), square root (√x), square (x²), fraction (1/x), and sign toggling (+/-).
- **Clear Controls:** Delete a single character (⌫), clear current entry (CE), and clear all (C).

### 📜 History Panel
- **Runtime Calculation History:** Automatically displays your past operations and current expressions in a secondary text view during the active session.

### 🛡️ Error Handling & Input Validation
- **Division by Zero Protection:** Prevents crash scenarios and displays a clear "Error" message.
- **Safe Deletion:** Disables character deletion when the screen displays an "Error" state, preventing logical bugs or crashes.

### 📱 Responsive Design & Screen Rotation
- **Orientation Support:** Fully optimized for both Portrait and Landscape modes.
- **State Preservation:** Maintains current calculation inputs, active results, and history state seamlessly across screen rotations using `onSaveInstanceState` and `onRestoreInstanceState`.
- **Localization Ready:** All button labels are extracted to `strings.xml` instead of being hard-coded, making the app easily localizable.

## 🛠️ Tech Stack & Architecture

- **Language:** Java
- **IDE:** Android Studio
- **UI Framework:** XML Layouts (LinearLayout & GridLayout)
- **Min SDK:** API 24 (Android 7.0 Nougat)
- **Dependencies:** 100% custom calculation logic (zero third-party libraries).

## 📸 Screenshots

| Portrait Mode | Landscape Mode |
| :---: | :---: |
| *(Add your screenshot link here)* | *(Add your screenshot link here)* |

## 📥 Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/Yhasanl/Calculcator_Viber.git](https://github.com/Yhasanl/Calculcator_Viber.git)