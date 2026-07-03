# How to Build Android Applications with Kotlin

This repository tracks my progress while reading **"How to Build Android Applications with Kotlin"** published by Packt.

Each chapter's code exercises and notes are stored in the corresponding folder below.

> **Note:** Some exercises are implemented differently from what the book instructs, for personal testing and experimentation purposes.

## Chapters

---

### [Chapter 1 – Creating Your First App](./Chapter01)

Introduction to Android development with Kotlin. Covers setting up Android Studio, creating a new project, exploring the project structure, and running a simple app on an emulator or physical device.

| Exercise / Activity | Link |
|---------------------|------|
| Exercise 01-01 | [Exercise01-01](./Chapter01/Exercise01-01) *(Only covers creating a new project)* |
| Exercise 01-02 | [Exercise01-02](./Chapter01/Exercise01-02) *(Only covers setting up an emulator)* |
| Exercise 01-03 | [Exercise01-03](./Chapter01/Exercise01-03) |
| Exercise 01-04 | [Exercise01-04](./Chapter01/Exercise01-04) |
| Exercise 01-05 | [Exercise01-05](./Chapter01/Exercise01-05) |
| Activity 01-01 | [Activity01-01](./Chapter01/Activity01-01) |

---

### [Chapter 2 – Building User Screen Flows](./Chapter02)

Explores how to build multi-screen applications using Activities and Intents. Covers passing data between screens, the Activity back stack, and creating a cohesive user flow.

| Exercise / Activity | Link |
|---------------------|------|
| Exercise 02-01 | [Exercise02-01](./Chapter02/Exercise02-01) |
| Exercise 02-02 | [Exercise02-02](./Chapter02/Exercise02-02) |
| Exercise 02-03 | Reused [Exercise01-05](./Chapter01/Exercise01-05); updated in commit [c3d8ee0](https://github.com/Daniel-0liver/How_to_Build_Android_Applications_with_Kotlin/commit/c3d8ee0babc5379bde4341bb1aaedfd25ffdbd2b) |
| Exercise 02-04 | [Exercise02-04](./Chapter02/Exercise02-04) |
| Exercise 02-05 | [Exercise02-05](./Chapter02/Exercise02-05) |
| Exercise 02-06 | [Exercise02-06](./Chapter02/Exercise02-06) |
| Activity 02-01 | [Activity02-01](./Chapter02/Activity02-01) |
| Activity 02-02 | [Activity02-02](./Chapter02/Activity02-02) |

---

### [Chapter 3 – Developing the UI with Jetpack Compose](./Chapter03)

Developing the UI with Jetpack Compose provides an in-depth look at basic composable functions and layout groups in Jetpack Compose. It demonstrates how to use them to build the UI and respond to state changes.

| Exercise / Activity | Link |
|---------------------|------|
| Exercise 03-01 | [Exercise03-01](./Chapter03/Exercise03-01) |
| Exercise 03-02 | [Exercise03-02](./Chapter03/Exercise03-02) |
| Exercise 03-03 | [Exercise03-03](./Chapter03/Exercise03-03) |
| Exercise 03-04 | [Exercise03-04](./Chapter03/Exercise03-04) |
| Activity 03-01 | [Activity03-01](./Chapter03/Activity03-01) |

---

### [Chapter 4 – Building App Navigation](./Chapter04)

Covers the Jetpack Navigation component for managing in-app navigation. Topics include the Navigation graph, NavHostFragment, passing arguments with Safe Args, and handling deep links.

| Exercise / Activity | Link |
|---------------------|------|
| Exercise 04-01 | [Exercise04-01](./Chapter04/Exercise04-01) |
| Exercise 04-02 | [Exercise04-02](./Chapter04/Exercise04-02) |
| Exercise 04-03 | [Exercise04-03](./Chapter04/Exercise04-03) |
| Exercise 04-04 | [Exercise04-04](./Chapter04/Exercise04-04) |
| Activity 04-01 | [Activity04-01](./Chapter04/Activity04-01) |

---

### [Chapter 5 – Essential Libraries: Retrofit, Moshi, and Glide](./Chapter05)

Introduces key third-party libraries for Android development. Covers making HTTP requests with Retrofit, parsing JSON responses with Moshi, and loading images asynchronously with Glide.

| Exercise / Activity | Link |
|---------------------|------|
| Exercise 05-01 | [Exercise05-01](./Chapter05/Exercise05-01); implemented in commit [0b911e8](https://github.com/Daniel-0liver/How_to_Build_Android_Applications_with_Kotlin/commit/0b911e84aecc595c3c5471933d9802ed856b7bba) |
| Exercise 05-02 | Reused [Exercise05-01](./Chapter05/Exercise05-01); updated in commit [f82426b](https://github.com/Daniel-0liver/How_to_Build_Android_Applications_with_Kotlin/commit/f82426bf4d7744defbf59be90e4acf5451ad1b96) |
| Exercise 05-03 | Reused [Exercise05-01](./Chapter05/Exercise05-01); updated in commit [2e3fac7](https://github.com/Daniel-0liver/How_to_Build_Android_Applications_with_Kotlin/commit/2e3fac79f2045effe70941dd69821f9485e4a203) |
| Activity 05-01 | [Activity05-01](./Chapter05/Activity05-01) |

---

### [Chapter 6 – RecyclerView](./Chapter06)

Deep dive into RecyclerView for displaying large, scrollable lists and grids. Covers ViewHolder pattern, LayoutManagers, DiffUtil for efficient updates, and handling item click events.

| Exercise / Activity | Link |
|---------------------|------|
| Exercise 06-01 | [Exercise06-01](./Chapter06/Exercise06-01) |
| Exercise 06-02 | Reused [Exercise06-01](./Chapter06/Exercise06-01); updated in commit [5d805a8](https://github.com/Daniel-0liver/How_to_Build_Android_Applications_with_Kotlin/commit/5d805a84ec28bae0314663a194b15f5922ab0569) |
| Exercise 06-03 | [Exercise06-03](./Chapter06/Exercise06-03) |
| Exercise 06-04 | [Exercise06-04](./Chapter06/Exercise06-04) |
| Exercise 06-05 | Reused [Exercise06-01](./Chapter06/Exercise06-01); updated in commit [98284d4](https://github.com/Daniel-0liver/How_to_Build_Android_Applications_with_Kotlin/commit/98284d42878d7ed4e9624b4c80937c2de402631d) |
| Exercise 06-06 | [Exercise06-06](./Chapter06/Exercise06-06) |
| Activity 06-01 | [Activity06-01](./Chapter06/Activity06-01) |

---

### [Chapter 7 – Android Permissions and Google Maps](./Chapter07)

Explains the Android runtime permission model and how to request permissions at runtime. Covers integrating the Google Maps SDK, displaying a map, adding markers, and using location services.

| Exercise / Activity | Link |
|---------------------|------|
| Exercise 07-01 | [Exercise07-01](./Chapter07/Exercise07-01) |
| Exercise 07-02 | [Exercise07-02](./Chapter07/Exercise07-02) |
| Exercise 07-03 | [Exercise07-03](./Chapter07/Exercise07-03) |
| Activity 07-01 | [Activity07-01](./Chapter07/Activity07-01) |

---

### [Chapter 8 – Services, WorkManager, and Notifications](./Chapter08)

Covers background processing in Android. Topics include foreground and background Services, scheduling deferred and periodic work with WorkManager, and displaying system notifications.

| Exercise / Activity | Link |
|---------------------|------|
| Exercise 08-01 | [Exercise08-01](./Chapter08/Exercise08-01) |
| Exercise 08-02 | [Exercise08-02](./Chapter08/Exercise08-02) |
| Exercise 08-03 | [Exercise08-03](./Chapter08/Exercise08-03) |
| Activity 08-01 | [Activity08-01](./Chapter08/Activity08-01) |

---

### [Chapter 9 – Unit Tests and Integration Tests with JUnit4](./Chapter09)

Introduction to automated testing on Android. Covers writing unit tests with JUnit4 and Mockito, integration tests with Espresso, and testing ViewModels and LiveData.

| Exercise / Activity | Link |
|---------------------|------|
| Exercise 09-01 | [Exercise09-01](./Chapter09/Exercise09-01) |
| Exercise 09-02 | [Exercise09-02](./Chapter09/Exercise09-02) |
| Exercise 09-03 | [Exercise09-03](./Chapter09/Exercise09-03) |
| Exercise 09-04 | [Exercise09-04](./Chapter09/Exercise09-04) |
| Activity 09-01 | [Activity09-01](./Chapter09/Activity09-01) |

---

### [Chapter 10 – Android Architecture Components](./Chapter10)

Explores the Jetpack Architecture Components: ViewModel, LiveData, and Room. Covers separating concerns with the MVVM pattern and observing data changes reactively in the UI.

| Exercise / Activity | Link |
|---------------------|------|
| Exercise 10-01 | [Exercise10-01](./Chapter10/Exercise10-01) |
| Exercise 10-02 | [Exercise10-02](./Chapter10/Exercise10-02) |
| Activity 10-01 | [Activity10-01](./Chapter10/Activity10-01) |
| Popular Movies (sample project) | [PopularMovies](./Chapter10/PopularMovies) |

---

### [Chapter 11 – Persisting Data](./Chapter11)

Covers the different data persistence options available on Android: SharedPreferences for key-value storage, the Room database for structured local data, and internal/external file storage.

| Exercise / Activity | Link |
|---------------------|------|
| Exercise 11-01 | [Exercise11-01](./Chapter11/Exercise11-01) |
| Exercise 11-02 | [Exercise11-02](./Chapter11/Exercise11-02) |
| Exercise 11-03 | [Exercise11-03](./Chapter11/Exercise11-03) |
| Activity 11-01 | [Activity11-01](./Chapter11/Activity11-01) |

---

### [Chapter 12 – Dependency Injection with Dagger, Hilt, and Koin](./Chapter12)

Introduces dependency injection (DI) concepts and their application in Android. Covers setting up Dagger2, simplifying DI with Hilt, and using Koin as a lightweight alternative.

| Exercise / Activity | Link |
|---------------------|------|
| Exercise 12-01 | [Exercise12-01](./Chapter12/Exercise12-01) |
| Exercise 12-02 | [Exercise12-02](./Chapter12/Exercise12-02) |
| Exercise 12-03 | [Exercise12-03](./Chapter12/Exercise12-03) |
| Exercise 12-04 | [Exercise12-04](./Chapter12/Exercise12-04) |
| Activity 12-01 | [Activity12-01](./Chapter12/Activity12-01) |

---

### [Chapter 13 – RxJava and Coroutines](./Chapter13)

Covers reactive and asynchronous programming on Android. Compares RxJava's Observable streams with Kotlin Coroutines and Flow for handling asynchronous operations cleanly.

| Exercise / Activity | Link |
|---------------------|------|
| Exercise 13-01 | [Exercise13-01](./Chapter13/Exercise13-01) |
| Exercise 13-02 | [Exercise13-02](./Chapter13/Exercise13-02) |
| Exercise 13-03 | [Exercise13-03](./Chapter13/Exercise13-03) |
| Exercise 13-04 | [Exercise13-04](./Chapter13/Exercise13-04) |
| Activity 13-01 | [Activity13-01](./Chapter13/Activity13-01) |

---

### [Chapter 14 – Architecture Patterns](./Chapter14)

Explores common Android architecture patterns: MVC, MVP, and MVVM. Focuses on applying MVVM with Jetpack components to build maintainable, testable, and scalable apps.

| Exercise / Activity | Link |
|---------------------|------|
| Exercise 14-01 | [Exercise14-01](./Chapter14/Exercise14-01) |
| Activity 14-01 | [Activity14-01](./Chapter14/Activity14-01) |

---

### [Chapter 15 – Animations and Transitions](./Chapter15)

Covers adding visual polish to Android apps. Topics include property animations, shared element transitions between Activities and Fragments, and using MotionLayout for complex animated UIs.

| Exercise / Activity | Link |
|---------------------|------|
| Exercise 15-01 | [Exercise15-01](./Chapter15/Exercise15-01) |
| Exercise 15-02 | [Exercise15-02](./Chapter15/Exercise15-02) |
| Exercise 15-03 | [Exercise15-03](./Chapter15/Exercise15-03) |
| Activity 15-01 | [Activity15-01](./Chapter15/Activity15-01) |

---

## License

See [LICENSE](./LICENSE) for details.
