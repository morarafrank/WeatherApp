# WeatherApp ☀️🌧️

A modern, responsive weather application built with **Native Android (Kotlin)** that fetches current weather and 5-day forecasts from the **OpenWeather API**, with full offline support, error handling, and a clean UI/UX.

---

## 📱 Features

- 🔍 Search for any city and view weather data
- 🌤️ Display current weather and 5-day forecast
- 📡 Fetch data from OpenWeather API
- 📴 Offline support with local Room database cache
- ⚠️ User-friendly error handling
- 💡 Last updated timestamp for offline data
- 🎨 Clean and responsive UI with support for different screen sizes

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Giraffe or later
- Gradle 8.x
- Android SDK 31+
- Internet connection (for fetching data)

### Installation

1. **Clone the repository:**

```bash
git clone https://github.com/morarafrank/WeatherApp.git
cd WeatherApp
````

2. **Open in Android Studio:**

File → Open → Navigate to `WeatherApp` folder.

3. **Add your OpenWeather API Key:**

Add the API key inside your `local.properties` file:

```kotlin
OPENWEATHER_API_KEY=YOUR_API_KEY
```

4. **Run the app** on emulator or physical device.

---

## 🧠 Approach

* Used MVVM architecture with `ViewModel`, `Repository`, and `Room`
* API integration using Retrofit
* Jetpack Compose for UI with state handling via `StateFlow`
* Offline support with Room DB and intelligent fallback
* Network detection at the UI level to ensure clean separation of concerns

---

## ⚔️ Challenges & Solutions

### 1. **Landscape screen clipping**

* ❌ Problem: Forecast grid didn’t show fully in landscape mode.
* ✅ Solution: Replaced `Column(verticalScroll())` with `LazyColumn` and ensured bounded height for `LazyVerticalGrid`.

### 2. **Offline support logic**

* ❌ Problem: Repository initially used `Context` to check connectivity.
* ✅ Solution: Moved connectivity check to ViewModel/UI layer and passed a flag to Repository.

### 3. **Error handling & clarity**

* ❌ Problem: Raw exceptions were not user-friendly.
* ✅ Solution: Wrapped network calls in a `Resource` sealed class to handle loading, success, and error states gracefully.

---

## 📸 Screenshots

<div style="display: flex; flex-wrap: wrap; gap: 8px; max-width: 1000px;">
  <img src="https://github.com/user-attachments/assets/66c5e1a3-1cfc-4a95-9e67-12f3bd757d63" height="180" />
  <img src="https://github.com/user-attachments/assets/6b12151f-92ea-4ec9-95c0-d100e72f1eef" height="180" />
  <img src="https://github.com/user-attachments/assets/ae945ea8-9a7c-431d-ac58-d915de01a1fb" height="180" />
  <img src="https://github.com/user-attachments/assets/6e1b9424-be1a-4700-95fb-c0719d114ff9" height="180" />
  <img src="https://github.com/user-attachments/assets/4e6aa3c0-f059-42d8-b2c0-913c6233dd06" height="180" />
  <img src="https://github.com/user-attachments/assets/1d91e925-9eff-4d59-ae6f-584c6b1ff6d3" height="180" />
</div>


---

## 📄 License

MIT License. Feel free to fork, modify, and share.


## 🤝 Contributing to WeatherApp ☀️🌧️

Welcome! 👋 Thank you for taking the time to contribute to WeatherApp — a modern Android weather app with offline support and a clean UI. Your help is invaluable in improving the experience for users across the world.

---

## 🛠️ How to Contribute

1. **Fork** the repository  
2. **Create a feature branch** from `main`  
   ```bash
   git checkout -b feature/my-feature

3. **Make your changes** and commit them with clear, descriptive messages

   ```bash
   git commit -m "Add: Improved offline fallback with shared prefs"
   ```

4. **Push to your fork** and open a Pull Request

   ```bash
   git push origin feature/my-feature
   ```

5. **Describe your pull request** – what it adds or fixes, and reference any related issue.

---

## 📋 Good First Issues

Not sure where to start? Try one of these:

* 📝 Improve documentation (README, comments, etc.)
* 🎨 Improve UI on tablets or landscape orientation
* ⚠️ Enhance error or network fallback messages
* 🔍 Add tests (unit or UI)
* ♻️ Refactor view models or composables for clarity

Check the [Issues](https://github.com/morarafrank/WeatherApp/issues) page for open tasks.

---

## ✅ Code Guidelines

* Use [Kotlin best practices](https://developer.android.com/kotlin/style-guide)
* Follow the existing architecture (MVVM + Clean Architecture)
* Test both online and offline behavior
* Ensure builds pass and app runs successfully
* Use `StateFlow`, Jetpack Compose patterns, and avoid tightly coupling UI with logic

---

## 📦 Project Stack

* Android + Kotlin
* Jetpack Compose
* Retrofit + Room
* StateFlow + ViewModel
* OpenWeather API

---

## 🧪 Running Locally

1. Add your `OPENWEATHER_API_KEY` in `local.properties`
2. Build and run via Android Studio
3. Test on multiple screen sizes/emulators if possible

---

## 🙋 Need Help?

Open an issue or reach out by tagging `@morarafrank` on GitHub.

Let’s make WeatherApp more robust, beautiful, and helpful — together! ☁️🤝

```
