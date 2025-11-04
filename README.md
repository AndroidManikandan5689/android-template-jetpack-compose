# Android Template with Jetpack Compose

A modern Android template project showcasing best practices, popular libraries, and Jetpack Compose UI implementation. This template serves as an excellent starting point for new Android projects.

## 🚀 Features

- **Modern UI** - Built with Jetpack Compose
- **Clean Architecture** principles
- **Dependency Injection** using Hilt
- **Local Storage** with Room Database
- **Network Communication** using Retrofit
- **Kotlin-first** development
- **Type-safe dependency management** using Version Catalogs
- **Serialization** using Kotlin Serialization
- **Image Loading** with Coil
- **Navigation** using Jetpack Navigation Compose
- **ViewModel** integration with Compose

## 🛠 Tech Stack

- **UI Framework**: Jetpack Compose with Material 3
- **Programming Language**: Kotlin
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 36
- **Build System**: Gradle with Kotlin DSL
- **Architecture Components**:
  - ViewModel
  - Room Database
  - Navigation Component
  - Hilt for dependency injection
- **Networking**: Retrofit with OkHttp
- **Image Loading**: Coil
- **Testing**: JUnit, Espresso, MockK
- **Async Operations**: Kotlin Coroutines

## 📋 Prerequisites

- Android Studio Latest Version
- JDK 17
- Android SDK with minimum API 24

## 🚀 Getting Started

1. Clone this repository:
   ```bash
   git clone https://github.com/AndroidManikandan5689/android-template-jetpack-compose.git
   ```

2. Open the project in Android Studio

3. Sync the project with Gradle files

4. Run the app on an emulator or physical device

## 📐 Project Structure

```
app/
├── build.gradle.kts        # Module-level Gradle build file
├── proguard-rules.pro     # ProGuard rules for app
└── src/
    └── main/
        ├── java/          # Kotlin/Java source files
        └── res/           # Resources
```

## 🏗 Architecture

### Clean Architecture Overview

```
┌──────────────┐
│     UI       │
│   (Compose)  │
└──────┬───────┘
       │
┌──────▼───────┐
│  Presentation│
│  (ViewModel) │
└──────┬───────┘
       │
┌──────▼───────┐
│   Domain     │
│  (Use Cases) │
└──────┬───────┘
       │
┌──────▼───────┐
│    Data      │
│(Repositories)│
└──────┬───────┘
       │
┌──────▼───────┐
│  Data Source │
│(Remote/Local)│
└──────────────┘
```

### Component Interaction Flow

```
┌─────────────┐    ┌─────────────┐    ┌─────────────┐
│   UI Event  │───▶│  ViewModel  │───▶│  Use Case   │
└─────────────┘    └─────────────┘    └─────────────┘
                          ▲                   │
                          │                   ▼
                          │            ┌─────────────┐
                          └────────────│ Repository  │
                                      └─────────────┘
```

## 💻 Code Examples

### Composable UI Component
```kotlin
@Composable
fun ItemCard(
    item: Item,
    onItemClick: (Item) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(item) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
```

### ViewModel Implementation
```kotlin
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserItemsUseCase: GetUserItemsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun loadItems() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val items = getUserItemsUseCase()
                _uiState.value = UiState.Success(items)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
```

### Room Database Entity
```kotlin
@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val createdAt: Long = System.currentTimeMillis()
)
```

### Hilt Module Example
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(KotlinxSerializationConverterFactory())
            .build()
            .create(ApiService::class.java)
    }
}
```

## 🧪 Testing Examples

### ViewModel Unit Test
```kotlin
@RunWith(MockKJUnitRunner::class)
class MainViewModelTest {
    @MockK
    lateinit var getUserItemsUseCase: GetUserItemsUseCase
    
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(getUserItemsUseCase)
    }

    @Test
    fun `when loadItems succeeds, uiState should be Success`() = runTest {
        // Given
        val items = listOf(Item("1", "Test", "Description"))
        coEvery { getUserItemsUseCase() } returns items

        // When
        viewModel.loadItems()

        // Then
        assert(viewModel.uiState.value is UiState.Success)
        val state = viewModel.uiState.value as UiState.Success
        assertEquals(items, state.items)
    }
}
```

### Compose UI Test
```kotlin
@RunWith(AndroidJUnit4::class)
class ItemCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun itemCard_displaysCorrectContent() {
        // Given
        val item = Item("1", "Test Title", "Test Description")
        
        // When
        composeTestRule.setContent {
            ItemCard(
                item = item,
                onItemClick = {}
            )
        }

        // Then
        composeTestRule.onNodeWithText("Test Title").assertExists()
        composeTestRule.onNodeWithText("Test Description").assertExists()
    }
}

## 📱 Screenshots

--

## � Deployment Instructions

### 1. Build Types Configuration

In your `app/build.gradle.kts`:
```kotlin
android {
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.release
        }
        
        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }
}
```

### 2. Release Signing Configuration

Create a `keystore.properties` file in the project root:
```properties
storeFile=../release.keystore
storePassword=your_store_password
keyAlias=your_key_alias
keyPassword=your_key_password
```

Add to `app/build.gradle.kts`:
```kotlin
val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    signingConfigs {
        create("release") {
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
        }
    }
}
```

### 3. Build Release APK

```bash
# Generate signed APK
./gradlew assembleRelease

# Generate signed App Bundle
./gradlew bundleRelease
```

### 4. CI/CD Pipeline Example (GitHub Actions)

Create `.github/workflows/android.yml`:
```yaml
name: Android CI

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v2
    
    - name: Set up JDK
      uses: actions/setup-java@v2
      with:
        java-version: '17'
        distribution: 'adopt'
        
    - name: Build and Test
      run: ./gradlew build test
      
    - name: Build Release APK
      run: ./gradlew assembleRelease
      
    - name: Upload APK
      uses: actions/upload-artifact@v2
      with:
        name: app-release
        path: app/build/outputs/apk/release/app-release.apk
```

## �🔧 Configuration

### Build Configuration

The project uses version catalogs for dependency management. Check `gradle/libs.versions.toml` for current versions.

### Key Dependencies

```kotlin
// UI - Jetpack Compose
implementation(platform("androidx.compose:compose-bom:${version}"))
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")

// Navigation
implementation("androidx.navigation:navigation-compose:2.7.4")

// Dependency Injection
implementation("com.google.dagger:hilt-android:2.57.2")

// Networking
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

// Local Database
implementation("androidx.room:room-runtime:2.6.0")
```

## 📄 License

License type: MIT
App name: Android Template
Year: 2025
Owner name: Manikandan K

## 🤝 Contributing

Feel free to contribute to this template:

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📬 Contact

Manikandan Kasinathan

Project Link: [https://github.com/AndroidManikandan5689/android-template-jetpack-compose](https://github.com/AndroidManikandan5689/android-template-jetpack-compose)