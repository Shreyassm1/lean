# Lean - Simple Tracker (Android)

Lean is an offline-first Android app focused on daily fitness execution with a clean, modern UI.

Core pillars:
- Daily timetable tracking (single active timetable, completion reset control)
- Meals and calorie tracking (with macro tracking)
- Workout task tracking (morning/evening checklist, auto-resets daily)

## Version
- App version: `1.0`
- Release tag target: `v1.0.0`

## Tech Stack
- Kotlin
- Android SDK (`minSdk 24`, `targetSdk 36`, `compileSdk 36`)
- AndroidX + Material 3
- RecyclerView
- SharedPreferences + JSON (`org.json`) for local persistence
- MikePenz Iconics + Community Material Icons for in-app icons

## Product Overview

### 1) Overview Screen
- Shows high-level progress for timetable, calorie goal, and workout tasks.
- Uses calculated values from local repository every `onResume()`.

### 2) Timetable
- One active timetable list.
- Entry fields: `title`, `time`.
- Entry states: editable, deletable, mark as done for today.
- Daily done-state can be reset manually (`Reset Today`).
- `Clear All` wipes current timetable.
- Reminder/alarm functionality is intentionally removed.

### 3) Meals & Calories
- Daily calorie goal setup and update.
- Two intake methods:
- Predefined food picker with quantity controls.
- Custom intake form.
- Per-log nutrition captured:
- Calories
- Protein (g)
- Carbs (g)
- Fats (g)
- Live progress bar against calorie goal.
- Macro totals shown in summary and per-item logs.

Predefined foods included:
- Boiled egg
- Roti (small)
- Fried rice (100 g serving)
- Cup of coffee (with milk + sugar assumption)
- Banana

### 4) Workout Tracker
- Simple daily to-do style checklists:
- Morning
- Evening
- Add / check / delete tasks.
- Task list auto-resets each day by date key.
- Walk section removed by design.

## UI/UX Notes
- Bottom navigation shared across feature screens.
- Dark mode compatible theme resources.
- Edge-to-edge with insets handling for older Android devices.
- Custom launcher icon implemented.
- Consistent iconography using community material icon set.

## Data and Persistence

All app data is local-only (offline) using `SharedPreferences`.

Main stored entities:
- `TimetableEntry`
- `CalorieLog` (includes macros)
- `WorkoutTask`
- `WorkoutLog` (infrastructure present)

Repository:
- `app/src/main/java/com/example/lean/data/LeanRepository.kt`

Persistence characteristics:
- JSON arrays per entity set.
- Date-key partitioning for daily workout tasks and timetable completion.
- No cloud sync.
- No backend dependency.

## Project Structure

See:
- `docs/REPOSITORY_REFERENCE.md` for detailed file-by-file mapping.

## Build & Run

### Prerequisites
- Android Studio (with bundled JBR/JDK)
- Android SDK for API 36

### Debug Build
```bash
./gradlew assembleDebug
```

### Release Build
```bash
./gradlew assembleRelease
```

If your environment has restricted access to the default Gradle home, use:
```bash
GRADLE_USER_HOME=/tmp/.gradle ./gradlew assembleRelease
```

## Release Artifacts
- Debug APK: `app/build/outputs/apk/debug/app-debug.apk`
- Release APK: `app/build/outputs/apk/release/app-release.apk`

## Git Versioning

Recommended release flow:
```bash
git add .
git commit -m "release: v1.0.0"
git tag -a v1.0.0 -m "Lean v1.0.0"
git push origin main
git push origin v1.0.0
```

## Known Constraints
- No notifications/reminders/alarms (removed by product decision).
- Local storage only; uninstall clears app data.
- Release signing currently uses default debug/release config unless keystore is configured.

## Next Upgrade Suggestions
- Settings screen for nutrition database customization.
- Export/import backup (JSON file).
- Goal history and weekly analytics.
- Optional local Room database migration from SharedPreferences.
