# Repository Reference

This document maps the repository structure and explains the role of major files.

## Root

- `settings.gradle.kts`
  - Declares project name (`Lean`) and includes module `:app`.
- `build.gradle.kts`
  - Top-level plugin declarations.
- `gradle/libs.versions.toml`
  - Dependency and plugin version catalog.
- `gradle.properties`
  - Gradle runtime flags.
- `gradlew`, `gradlew.bat`, `gradle/wrapper/*`
  - Gradle wrapper scripts and configuration.

## App Module

### Build Configuration

- `app/build.gradle.kts`
  - Android application module config.
  - SDK targets, build types, Java/Kotlin compatibility.
  - Module dependencies.
- `app/proguard-rules.pro`
  - Proguard rules template for release builds.

### Manifest

- `app/src/main/AndroidManifest.xml`
  - Application declaration.
  - Activity registration:
  - `MainActivity`
  - `TimetableActivity`
  - `CaloriesActivity`
  - `WorkoutActivity`

### Kotlin Source - App Layer

- `app/src/main/java/com/example/lean/MainActivity.kt`
  - Overview dashboard.
  - Aggregates summary data from repository.

- `app/src/main/java/com/example/lean/TimetableActivity.kt`
  - Timetable feature.
  - Add/edit/delete entries and mark complete for day.

- `app/src/main/java/com/example/lean/CaloriesActivity.kt`
  - Calorie and macro tracking.
  - Supports predefined intake picker and custom intake form.

- `app/src/main/java/com/example/lean/WorkoutActivity.kt`
  - Daily workout tasks grouped by Morning/Evening.
  - Add/check/delete tasks with daily reset behavior.

### Kotlin Source - Navigation

- `app/src/main/java/com/example/lean/navigation/BaseNavActivity.kt`
  - Shared bottom-nav behavior for all top-level screens.

### Kotlin Source - Data

- `app/src/main/java/com/example/lean/data/Models.kt`
  - Data models:
  - `TimetableEntry`
  - `CalorieLog`
  - `WaterLog` (legacy/inactive in current UI)
  - `WorkoutLog`
  - `WorkoutTask`

- `app/src/main/java/com/example/lean/data/LeanRepository.kt`
  - Local persistence gateway.
  - Stores/loads all feature data through SharedPreferences JSON.
  - Handles per-day keys for daily-reset features.

### Kotlin Source - UI Adapters

- `app/src/main/java/com/example/lean/ui/TimetableAdapter.kt`
  - RecyclerView adapter for timetable entries.

- `app/src/main/java/com/example/lean/ui/CalorieLogAdapter.kt`
  - RecyclerView adapter for nutrition logs (calories + macros).

- `app/src/main/java/com/example/lean/ui/WorkoutTaskAdapter.kt`
  - RecyclerView adapter for workout tasks with checkbox interaction.

- `app/src/main/java/com/example/lean/ui/WorkoutLogAdapter.kt`
  - RecyclerView adapter for workout logs (infrastructure support).

### Kotlin Source - Utilities

- `app/src/main/java/com/example/lean/util/DateKeys.kt`
  - Date key helper (`yyyyMMdd`) for daily partitioning.

- `app/src/main/java/com/example/lean/util/TimeFormatters.kt`
  - Time and datetime format helpers.

## Resources

### Layouts (`app/src/main/res/layout`)

- `activity_main.xml` - Overview screen.
- `activity_timetable.xml` - Timetable feature screen.
- `activity_calories.xml` - Meals & calories feature screen.
- `activity_workout.xml` - Workout tasks screen.
- `dialog_timetable_entry.xml` - Timetable add/edit dialog.
- `dialog_calorie_log.xml` - Predefined intake picker dialog.
- `dialog_custom_calorie_log.xml` - Custom intake dialog.
- `dialog_single_input.xml` - Reusable single input dialog.
- `item_timetable_entry.xml` - Timetable row item.
- `item_calorie_log.xml` - Calorie log row item.
- `item_food_quantity.xml` - Predefined food quantity card row.
- `item_workout_task.xml` - Workout task row item.
- `item_workout_log.xml` - Workout log row item.

### Menu

- `app/src/main/res/menu/menu_bottom_nav.xml`
  - Bottom navigation items for Overview, Timetable, Meals, Workout.

### Drawables

- Background/system styles:
  - `bg_app_gradient.xml`
  - `bg_section.xml`
  - `bg_summary_card.xml`
  - `bg_hero.xml`
  - `bg_hero_chip.xml`
  - `bg_chip.xml`
- Navigation icons:
  - `ic_nav_overview.xml`
  - `ic_nav_timetable.xml`
  - `ic_nav_meals.xml`
  - `ic_nav_workout.xml`
- Launcher icon layers:
  - `ic_launcher_background.xml`
  - `ic_launcher_foreground.xml`

### Colors/Themes

- `values/colors.xml`, `values-night/colors.xml`
  - App palette for light/dark modes.
- `values/themes.xml`, `values-night/themes.xml`
  - Theme setup.
- `color/nav_item_tint.xml`
  - Bottom nav color states.

### Strings

- `values/strings.xml`
  - User-facing copy labels and button text.

### Launcher Mipmap

- `mipmap-anydpi-v26/ic_launcher.xml`
- `mipmap-anydpi-v26/ic_launcher_round.xml`
- density variants in `mipmap-*`

### Backup XML

- `xml/backup_rules.xml`
- `xml/data_extraction_rules.xml`

## Tests

- `app/src/test/java/com/example/lean/ExampleUnitTest.kt`
- `app/src/androidTest/java/com/example/lean/ExampleInstrumentedTest.kt`

These are default starter tests and can be expanded for feature validation.

## Data Keys (LeanRepository)

SharedPreferences file:
- `lean_repository`

Primary keys:
- `setup_done`
- `calorie_goal`
- `water_goal` (legacy storage key present)
- `timetable`
- `calorie_logs`
- `water_logs` (legacy storage key present)
- `workout_logs`
- `workout_tasks`
- `workout_tasks_date`
- `timetable_completion_<yyyyMMdd>`

## Feature/State Notes

- Timetable reminders are removed from app behavior and manifest.
- Workout section intentionally simplified to checklist model.
- Hydration UI and reminder behavior removed from active product flow.
- Some legacy models/keys remain in repository for compatibility and potential future extension.
