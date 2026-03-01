# File-by-File Repository Reference

Generated on: 2026-03-01 16:30:43 UTC

This document lists every file currently present in the repository working tree.

## `.gitignore`
- Status: tracked (clean)
- MIME type: text/plain
- Size: 446 bytes
- Line count: 34
- Role: Ignore rules that prevent generated/local files from being committed.

## `.idea/.gitignore`
- Status: tracked (clean)
- MIME type: text/plain
- Size: 47 bytes
- Line count: 3
- Role: Ignore rules that prevent generated/local files from being committed.

## `.idea/AndroidProjectSystem.xml`
- Status: tracked (clean)
- MIME type: text/xml
- Size: 212 bytes
- Line count: 5
- Role: Android Studio project metadata (IDE settings, code style, Gradle integration, deployment target selections).
- Details: XML root node: 2:project.

## `.idea/codeStyles/Project.xml`
- Status: tracked (clean)
- MIME type: text/plain
- Size: 3622 bytes
- Line count: 122
- Role: Android Studio project metadata (IDE settings, code style, Gradle integration, deployment target selections).
- Details: XML root node: 1:component.

## `.idea/codeStyles/codeStyleConfig.xml`
- Status: tracked (clean)
- MIME type: text/plain
- Size: 142 bytes
- Line count: 4
- Role: Android Studio project metadata (IDE settings, code style, Gradle integration, deployment target selections).
- Details: XML root node: 1:component.

## `.idea/compiler.xml`
- Status: tracked (clean)
- MIME type: text/xml
- Size: 169 bytes
- Line count: 5
- Role: Android Studio project metadata (IDE settings, code style, Gradle integration, deployment target selections).
- Details: XML root node: 2:project.

## `.idea/deploymentTargetSelector.xml`
- Status: tracked (clean)
- MIME type: text/xml
- Size: 301 bytes
- Line count: 9
- Role: Android Studio project metadata (IDE settings, code style, Gradle integration, deployment target selections).
- Details: XML root node: 2:project.

## `.idea/gradle.xml`
- Status: tracked (clean)
- MIME type: text/xml
- Size: 622 bytes
- Line count: 17
- Role: Android Studio project metadata (IDE settings, code style, Gradle integration, deployment target selections).
- Details: XML root node: 2:project.

## `.idea/migrations.xml`
- Status: tracked (clean)
- MIME type: text/xml
- Size: 254 bytes
- Line count: 9
- Role: Android Studio project metadata (IDE settings, code style, Gradle integration, deployment target selections).
- Details: XML root node: 2:project.

## `.idea/misc.xml`
- Status: tracked (clean)
- MIME type: text/xml
- Size: 448 bytes
- Line count: 9
- Role: Android Studio project metadata (IDE settings, code style, Gradle integration, deployment target selections).
- Details: XML root node: 2:project.

## `.idea/runConfigurations.xml`
- Status: tracked (clean)
- MIME type: text/xml
- Size: 964 bytes
- Line count: 16
- Role: Android Studio project metadata (IDE settings, code style, Gradle integration, deployment target selections).
- Details: XML root node: 2:project.

## `.idea/vcs.xml`
- Status: tracked (clean)
- MIME type: text/xml
- Size: 180 bytes
- Line count: 5
- Role: Android Studio project metadata (IDE settings, code style, Gradle integration, deployment target selections).
- Details: XML root node: 2:project.

## `README.md`
- Status: untracked
- MIME type: text/plain
- Size: 3777 bytes
- Line count: 140
- Role: Project overview, feature list, build instructions, and release flow.
- Details: Markdown title: Lean - Offline Workout Helper (Android).

## `app/.gitignore`
- Status: tracked (clean)
- MIME type: text/plain
- Size: 6 bytes
- Line count: 0
- Role: Ignore rules that prevent generated/local files from being committed.

## `app/build.gradle.kts`
- Status: modified
- MIME type: text/plain
- Size: 1349 bytes
- Line count: 53
- Role: Android app module build config: SDK targets, build types, and dependencies.
- Details: Build/configuration script used by Gradle/Android tooling.

## `app/proguard-rules.pro`
- Status: tracked (clean)
- MIME type: text/plain
- Size: 750 bytes
- Line count: 20
- Role: Code shrinking/obfuscation rules for release builds.
- Details: Build/configuration script used by Gradle/Android tooling.

## `app/src/androidTest/java/com/example/lean/ExampleInstrumentedTest.kt`
- Status: tracked (clean)
- MIME type: text/x-c++
- Size: 659 bytes
- Line count: 23
- Role: Default instrumented test scaffold for device/emulator execution.
- Details: Kotlin source package: com.example.lean. Declarations: class ExampleInstrumentedTest {.

## `app/src/main/AndroidManifest.xml`
- Status: modified
- MIME type: text/xml
- Size: 1063 bytes
- Line count: 29
- Role: App manifest declaring application metadata and Activity registrations.
- Details: XML root node: 2:manifest.

## `app/src/main/java/com/example/lean/CaloriesActivity.kt`
- Status: untracked
- MIME type: text/plain
- Size: 15247 bytes
- Line count: 360
- Role: Meals/calories tracking Activity with macro totals and log entry flows.
- Details: Kotlin source package: com.example.lean. Declarations: class CaloriesActivity : BaseNavActivity() {.

## `app/src/main/java/com/example/lean/MainActivity.kt`
- Status: modified
- MIME type: text/plain
- Size: 3494 bytes
- Line count: 83
- Role: Overview screen Activity showing daily progress summaries and navigation.
- Details: Kotlin source package: com.example.lean. Declarations: class MainActivity : BaseNavActivity() {.

## `app/src/main/java/com/example/lean/TimetableActivity.kt`
- Status: untracked
- MIME type: text/plain
- Size: 7360 bytes
- Line count: 174
- Role: Timetable feature Activity for adding, editing, and tracking daily schedule entries.
- Details: Kotlin source package: com.example.lean. Declarations: class TimetableActivity : BaseNavActivity() {.

## `app/src/main/java/com/example/lean/WorkoutActivity.kt`
- Status: untracked
- MIME type: text/plain
- Size: 5751 bytes
- Line count: 158
- Role: Workout checklist Activity managing morning/evening tasks with daily resets.
- Details: Kotlin source package: com.example.lean. Declarations: class WorkoutActivity : BaseNavActivity() {.

## `app/src/main/java/com/example/lean/data/LeanRepository.kt`
- Status: untracked
- MIME type: text/plain
- Size: 8252 bytes
- Line count: 236
- Role: SharedPreferences JSON persistence layer for all feature data.
- Details: Kotlin source package: com.example.lean.data. Declarations: class LeanRepository(context: Context) {.

## `app/src/main/java/com/example/lean/data/Models.kt`
- Status: untracked
- MIME type: text/plain
- Size: 699 bytes
- Line count: 39
- Role: Domain data models used by repository and UI layers.
- Details: Kotlin source package: com.example.lean.data. Declarations: data class TimetableEntry(;data class CalorieLog( data class WaterLog(;data class WorkoutLog( data class WorkoutTask(.

## `app/src/main/java/com/example/lean/navigation/BaseNavActivity.kt`
- Status: untracked
- MIME type: text/plain
- Size: 1306 bytes
- Line count: 35
- Role: Shared bottom-navigation Activity base class reused across top-level screens.
- Details: Kotlin source package: com.example.lean.navigation. Declarations: none detected.

## `app/src/main/java/com/example/lean/ui/CalorieLogAdapter.kt`
- Status: untracked
- MIME type: text/plain
- Size: 2834 bytes
- Line count: 74
- Role: RecyclerView adapter rendering calorie/macronutrient log entries.
- Details: Kotlin source package: com.example.lean.ui. Declarations: class CalorieLogAdapter(.

## `app/src/main/java/com/example/lean/ui/TimetableAdapter.kt`
- Status: untracked
- MIME type: text/plain
- Size: 3546 bytes
- Line count: 87
- Role: RecyclerView adapter for timetable list rows and user actions.
- Details: Kotlin source package: com.example.lean.ui. Declarations: class TimetableAdapter(.

## `app/src/main/java/com/example/lean/ui/WorkoutLogAdapter.kt`
- Status: untracked
- MIME type: text/plain
- Size: 1503 bytes
- Line count: 43
- Role: RecyclerView adapter scaffold for workout log display entries.
- Details: Kotlin source package: com.example.lean.ui. Declarations: class WorkoutLogAdapter : RecyclerView.Adapter<WorkoutLogAdapter.WorkoutLogViewHolder>() {.

## `app/src/main/java/com/example/lean/ui/WorkoutTaskAdapter.kt`
- Status: untracked
- MIME type: text/plain
- Size: 2501 bytes
- Line count: 65
- Role: RecyclerView adapter for workout tasks with completion toggles.
- Details: Kotlin source package: com.example.lean.ui. Declarations: class WorkoutTaskAdapter(.

## `app/src/main/java/com/example/lean/util/DateKeys.kt`
- Status: untracked
- MIME type: text/plain
- Size: 250 bytes
- Line count: 9
- Role: Date-key utility helpers for day-partitioned persistence.
- Details: Kotlin source package: com.example.lean.util. Declarations: none detected.

## `app/src/main/java/com/example/lean/util/TimeFormatters.kt`
- Status: untracked
- MIME type: text/plain
- Size: 570 bytes
- Line count: 20
- Role: Time/date formatting helpers used by UI and logs.
- Details: Kotlin source package: com.example.lean.util. Declarations: none detected.

## `app/src/main/res/color/nav_item_tint.xml`
- Status: untracked
- MIME type: text/xml
- Size: 248 bytes
- Line count: 5
- Role: State-list color selectors for themed UI controls.
- Details: XML root node: 2:selector.

## `app/src/main/res/drawable/bg_app_gradient.xml`
- Status: untracked
- MIME type: text/xml
- Size: 279 bytes
- Line count: 7
- Role: Drawable XML assets for gradients, cards, chips, icons, and launcher layers.
- Details: XML root node: 2:shape.

## `app/src/main/res/drawable/bg_chip.xml`
- Status: untracked
- MIME type: text/xml
- Size: 294 bytes
- Line count: 6
- Role: Drawable XML assets for gradients, cards, chips, icons, and launcher layers.
- Details: XML root node: 2:shape.

## `app/src/main/res/drawable/bg_hero.xml`
- Status: untracked
- MIME type: text/xml
- Size: 314 bytes
- Line count: 8
- Role: Drawable XML assets for gradients, cards, chips, icons, and launcher layers.
- Details: XML root node: 2:shape.

## `app/src/main/res/drawable/bg_hero_chip.xml`
- Status: untracked
- MIME type: text/xml
- Size: 301 bytes
- Line count: 6
- Role: Drawable XML assets for gradients, cards, chips, icons, and launcher layers.
- Details: XML root node: 2:shape.

## `app/src/main/res/drawable/bg_section.xml`
- Status: untracked
- MIME type: text/xml
- Size: 389 bytes
- Line count: 7
- Role: Drawable XML assets for gradients, cards, chips, icons, and launcher layers.
- Details: XML root node: 2:shape.

## `app/src/main/res/drawable/bg_summary_card.xml`
- Status: untracked
- MIME type: text/xml
- Size: 295 bytes
- Line count: 6
- Role: Drawable XML assets for gradients, cards, chips, icons, and launcher layers.
- Details: XML root node: 2:shape.

## `app/src/main/res/drawable/ic_launcher_background.xml`
- Status: modified
- MIME type: text/xml
- Size: 885 bytes
- Line count: 27
- Role: Drawable XML assets for gradients, cards, chips, icons, and launcher layers.
- Details: XML root node: 2:vector.

## `app/src/main/res/drawable/ic_launcher_foreground.xml`
- Status: modified
- MIME type: text/plain
- Size: 586 bytes
- Line count: 23
- Role: Drawable XML assets for gradients, cards, chips, icons, and launcher layers.
- Details: XML root node: 1:vector.

## `app/src/main/res/drawable/ic_nav_meals.xml`
- Status: untracked
- MIME type: text/plain
- Size: 406 bytes
- Line count: 9
- Role: Drawable XML assets for gradients, cards, chips, icons, and launcher layers.
- Details: XML root node: 1:vector.

## `app/src/main/res/drawable/ic_nav_overview.xml`
- Status: untracked
- MIME type: text/plain
- Size: 306 bytes
- Line count: 9
- Role: Drawable XML assets for gradients, cards, chips, icons, and launcher layers.
- Details: XML root node: 1:vector.

## `app/src/main/res/drawable/ic_nav_timetable.xml`
- Status: untracked
- MIME type: text/plain
- Size: 410 bytes
- Line count: 9
- Role: Drawable XML assets for gradients, cards, chips, icons, and launcher layers.
- Details: XML root node: 1:vector.

## `app/src/main/res/drawable/ic_nav_workout.xml`
- Status: untracked
- MIME type: text/plain
- Size: 393 bytes
- Line count: 9
- Role: Drawable XML assets for gradients, cards, chips, icons, and launcher layers.
- Details: XML root node: 1:vector.

## `app/src/main/res/layout/activity_calories.xml`
- Status: untracked
- MIME type: text/xml
- Size: 6105 bytes
- Line count: 143
- Role: Layout XML defining Activity screens, dialogs, and RecyclerView row templates.
- Details: XML root node: 2:androidx.coordinatorlayout.widget.CoordinatorLayout.

## `app/src/main/res/layout/activity_main.xml`
- Status: modified
- MIME type: text/xml
- Size: 8971 bytes
- Line count: 209
- Role: Layout XML defining Activity screens, dialogs, and RecyclerView row templates.
- Details: XML root node: 2:androidx.coordinatorlayout.widget.CoordinatorLayout.

## `app/src/main/res/layout/activity_timetable.xml`
- Status: untracked
- MIME type: text/xml
- Size: 5178 bytes
- Line count: 122
- Role: Layout XML defining Activity screens, dialogs, and RecyclerView row templates.
- Details: XML root node: 2:androidx.coordinatorlayout.widget.CoordinatorLayout.

## `app/src/main/res/layout/activity_workout.xml`
- Status: untracked
- MIME type: text/xml
- Size: 6446 bytes
- Line count: 148
- Role: Layout XML defining Activity screens, dialogs, and RecyclerView row templates.
- Details: XML root node: 2:androidx.coordinatorlayout.widget.CoordinatorLayout.

## `app/src/main/res/layout/dialog_calorie_log.xml`
- Status: untracked
- MIME type: text/xml
- Size: 3979 bytes
- Line count: 92
- Role: Layout XML defining Activity screens, dialogs, and RecyclerView row templates.
- Details: XML root node: 2:androidx.core.widget.NestedScrollView.

## `app/src/main/res/layout/dialog_custom_calorie_log.xml`
- Status: untracked
- MIME type: text/xml
- Size: 2912 bytes
- Line count: 72
- Role: Layout XML defining Activity screens, dialogs, and RecyclerView row templates.
- Details: XML root node: 2:LinearLayout.

## `app/src/main/res/layout/dialog_single_input.xml`
- Status: untracked
- MIME type: text/xml
- Size: 722 bytes
- Line count: 19
- Role: Layout XML defining Activity screens, dialogs, and RecyclerView row templates.
- Details: XML root node: 2:LinearLayout.

## `app/src/main/res/layout/dialog_timetable_entry.xml`
- Status: untracked
- MIME type: text/xml
- Size: 1054 bytes
- Line count: 27
- Role: Layout XML defining Activity screens, dialogs, and RecyclerView row templates.
- Details: XML root node: 2:LinearLayout.

## `app/src/main/res/layout/item_calorie_log.xml`
- Status: untracked
- MIME type: text/xml
- Size: 2274 bytes
- Line count: 61
- Role: Layout XML defining Activity screens, dialogs, and RecyclerView row templates.
- Details: XML root node: 2:com.google.android.material.card.MaterialCardView.

## `app/src/main/res/layout/item_food_quantity.xml`
- Status: untracked
- MIME type: text/xml
- Size: 2813 bytes
- Line count: 75
- Role: Layout XML defining Activity screens, dialogs, and RecyclerView row templates.
- Details: XML root node: 2:com.google.android.material.card.MaterialCardView.

## `app/src/main/res/layout/item_timetable_entry.xml`
- Status: untracked
- MIME type: text/xml
- Size: 2700 bytes
- Line count: 69
- Role: Layout XML defining Activity screens, dialogs, and RecyclerView row templates.
- Details: XML root node: 2:com.google.android.material.card.MaterialCardView.

## `app/src/main/res/layout/item_workout_log.xml`
- Status: untracked
- MIME type: text/xml
- Size: 1257 bytes
- Line count: 35
- Role: Layout XML defining Activity screens, dialogs, and RecyclerView row templates.
- Details: XML root node: 2:com.google.android.material.card.MaterialCardView.

## `app/src/main/res/layout/item_workout_task.xml`
- Status: untracked
- MIME type: text/xml
- Size: 891 bytes
- Line count: 25
- Role: Layout XML defining Activity screens, dialogs, and RecyclerView row templates.
- Details: XML root node: 2:LinearLayout.

## `app/src/main/res/menu/menu_bottom_nav.xml`
- Status: untracked
- MIME type: text/xml
- Size: 691 bytes
- Line count: 19
- Role: Menu resource definitions (bottom navigation items).
- Details: XML root node: 2:menu.

## `app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml`
- Status: tracked (clean)
- MIME type: text/xml
- Size: 343 bytes
- Line count: 5
- Role: Launcher icon resources across densities and adaptive icon definitions.
- Details: XML root node: 2:adaptive-icon.

## `app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml`
- Status: tracked (clean)
- MIME type: text/xml
- Size: 343 bytes
- Line count: 5
- Role: Launcher icon resources across densities and adaptive icon definitions.
- Details: XML root node: 2:adaptive-icon.

## `app/src/main/res/mipmap-hdpi/ic_launcher.webp`
- Status: tracked (clean)
- MIME type: image/webp
- Size: 1404 bytes
- Line count: binary
- Role: Launcher icon resources across densities and adaptive icon definitions.
- Details: Binary image asset used by Android launcher/icon resources.

## `app/src/main/res/mipmap-hdpi/ic_launcher_round.webp`
- Status: tracked (clean)
- MIME type: image/webp
- Size: 2898 bytes
- Line count: binary
- Role: Launcher icon resources across densities and adaptive icon definitions.
- Details: Binary image asset used by Android launcher/icon resources.

## `app/src/main/res/mipmap-mdpi/ic_launcher.webp`
- Status: tracked (clean)
- MIME type: image/webp
- Size: 982 bytes
- Line count: binary
- Role: Launcher icon resources across densities and adaptive icon definitions.
- Details: Binary image asset used by Android launcher/icon resources.

## `app/src/main/res/mipmap-mdpi/ic_launcher_round.webp`
- Status: tracked (clean)
- MIME type: image/webp
- Size: 1772 bytes
- Line count: binary
- Role: Launcher icon resources across densities and adaptive icon definitions.
- Details: Binary image asset used by Android launcher/icon resources.

## `app/src/main/res/mipmap-xhdpi/ic_launcher.webp`
- Status: tracked (clean)
- MIME type: image/webp
- Size: 1900 bytes
- Line count: binary
- Role: Launcher icon resources across densities and adaptive icon definitions.
- Details: Binary image asset used by Android launcher/icon resources.

## `app/src/main/res/mipmap-xhdpi/ic_launcher_round.webp`
- Status: tracked (clean)
- MIME type: image/webp
- Size: 3918 bytes
- Line count: binary
- Role: Launcher icon resources across densities and adaptive icon definitions.
- Details: Binary image asset used by Android launcher/icon resources.

## `app/src/main/res/mipmap-xxhdpi/ic_launcher.webp`
- Status: tracked (clean)
- MIME type: image/webp
- Size: 2884 bytes
- Line count: binary
- Role: Launcher icon resources across densities and adaptive icon definitions.
- Details: Binary image asset used by Android launcher/icon resources.

## `app/src/main/res/mipmap-xxhdpi/ic_launcher_round.webp`
- Status: tracked (clean)
- MIME type: image/webp
- Size: 5914 bytes
- Line count: binary
- Role: Launcher icon resources across densities and adaptive icon definitions.
- Details: Binary image asset used by Android launcher/icon resources.

## `app/src/main/res/mipmap-xxxhdpi/ic_launcher.webp`
- Status: tracked (clean)
- MIME type: image/webp
- Size: 3844 bytes
- Line count: binary
- Role: Launcher icon resources across densities and adaptive icon definitions.
- Details: Binary image asset used by Android launcher/icon resources.

## `app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.webp`
- Status: tracked (clean)
- MIME type: image/webp
- Size: 7778 bytes
- Line count: binary
- Role: Launcher icon resources across densities and adaptive icon definitions.
- Details: Binary image asset used by Android launcher/icon resources.

## `app/src/main/res/values-night/colors.xml`
- Status: untracked
- MIME type: text/xml
- Size: 978 bytes
- Line count: 22
- Role: Theme, string, and color resources for light/dark modes.
- Details: XML root node: 2:resources.

## `app/src/main/res/values-night/themes.xml`
- Status: modified
- MIME type: text/html
- Size: 998 bytes
- Line count: 17
- Role: Theme, string, and color resources for light/dark modes.
- Details: XML root node: 1:resources.

## `app/src/main/res/values/colors.xml`
- Status: modified
- MIME type: text/xml
- Size: 978 bytes
- Line count: 22
- Role: Theme, string, and color resources for light/dark modes.
- Details: XML root node: 2:resources.

## `app/src/main/res/values/strings.xml`
- Status: modified
- MIME type: text/plain
- Size: 2549 bytes
- Line count: 54
- Role: Theme, string, and color resources for light/dark modes.
- Details: XML root node: 1:resources.

## `app/src/main/res/values/themes.xml`
- Status: modified
- MIME type: text/html
- Size: 1008 bytes
- Line count: 17
- Role: Theme, string, and color resources for light/dark modes.
- Details: XML root node: 1:resources.

## `app/src/main/res/xml/backup_rules.xml`
- Status: tracked (clean)
- MIME type: text/xml
- Size: 478 bytes
- Line count: 12
- Role: Android backup/data extraction policy resources.
- Details: XML root node: 8:full-backup-content.

## `app/src/main/res/xml/data_extraction_rules.xml`
- Status: tracked (clean)
- MIME type: text/xml
- Size: 551 bytes
- Line count: 18
- Role: Android backup/data extraction policy resources.
- Details: XML root node: 6:data-extraction-rules.

## `app/src/test/java/com/example/lean/ExampleUnitTest.kt`
- Status: tracked (clean)
- MIME type: text/x-c++
- Size: 340 bytes
- Line count: 16
- Role: Default local unit test scaffold.
- Details: Kotlin source package: com.example.lean. Declarations: class ExampleUnitTest {.

## `build.gradle.kts`
- Status: tracked (clean)
- MIME type: text/plain
- Size: 218 bytes
- Line count: 4
- Role: Top-level Gradle build script defining shared plugin declarations.
- Details: Build/configuration script used by Gradle/Android tooling.

## `docs/FILE_REFERENCE_DETAILED.md`
- Status: untracked
- MIME type: text/plain
- Size: 21751 bytes
- Line count: 635
- Role: Exhaustive per-file inventory with metadata and roles.
- Details: Markdown title: File-by-File Repository Reference.

## `docs/REPOSITORY_REFERENCE.md`
- Status: untracked
- MIME type: text/plain
- Size: 5601 bytes
- Line count: 194
- Role: High-level architecture and directory reference for major components.
- Details: Markdown title: Repository Reference.

## `gradle.properties`
- Status: tracked (clean)
- MIME type: text/plain
- Size: 1346 bytes
- Line count: 22
- Role: Gradle/JVM configuration flags used during builds.
- Details: Build/configuration script used by Gradle/Android tooling.

## `gradle/libs.versions.toml`
- Status: modified
- MIME type: text/plain
- Size: 1602 bytes
- Line count: 31
- Role: Centralized version catalog for dependencies and plugins.
- Details: Build/configuration script used by Gradle/Android tooling.

## `gradle/wrapper/gradle-wrapper.jar`
- Status: tracked (clean)
- MIME type: application/zip
- Size: 59203 bytes
- Line count: binary
- Role: Bootstrap binary used by Gradle wrapper scripts.
- Details: Binary Java archive required by tooling (not app runtime source).

## `gradle/wrapper/gradle-wrapper.properties`
- Status: tracked (clean)
- MIME type: text/plain
- Size: 231 bytes
- Line count: 6
- Role: Defines Gradle wrapper distribution URL/version.
- Details: Build/configuration script used by Gradle/Android tooling.

## `gradlew`
- Status: tracked (clean)
- MIME type: text/plain
- Size: 5766 bytes
- Line count: 185
- Role: Platform-specific Gradle wrapper launch scripts.
- Details: Build/configuration script used by Gradle/Android tooling.

## `gradlew.bat`
- Status: tracked (clean)
- MIME type: text/x-msdos-batch
- Size: 2763 bytes
- Line count: 89
- Role: Platform-specific Gradle wrapper launch scripts.
- Details: Build/configuration script used by Gradle/Android tooling.

## `settings.gradle.kts`
- Status: tracked (clean)
- MIME type: text/plain
- Size: 529 bytes
- Line count: 23
- Role: Gradle settings for project name and included modules.
- Details: Build/configuration script used by Gradle/Android tooling.

