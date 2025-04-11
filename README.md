# PetStore API Automation Framework (Gradle)

Фреймворк для автоматизированного тестирования API PetStore с поддержкой Allure-отчетов (сборка Gradle).

### Стек технологий

<p align="center">
<img width="6%" title="IntelijIDEA" src="images/logo/Intelij_IDEA.svg">
<img width="6%" title="Java" src="images/logo/Java.svg">
<img width="6%" title="Selenide" src="images/logo/Selenide.svg">
<img width="6%" title="Selenoid" src="images/logo/Selenoid.svg">
<img width="6%" title="Allure Report" src="images/logo/AllureReport.svg">
<img width="5%" title="Allure TestOps" src="images/logo/AllureTestOps.svg">
<img width="6%" title="Gradle" src="images/logo/Gradle.svg">
<img width="6%" title="JUnit5" src="images/logo/JUnit5.svg">
<img width="6%" title="GitHub" src="images/logo/Github.svg">
<img width="6%" title="Jenkins" src="images/logo/Jenkins.svg">
<img width="6%" title="Telegram" src="images/logo/Telegram.svg">
</p>

## 📌 Особенности

- **Полное покрытие API**: CRUD операции для сущностей Pet и Order
- **Gradle-сборка**: Оптимизированная конфигурация зависимостей
- **Инструменты**:
    - RestAssured для HTTP-запросов
    - Lombok для моделей
    - Allure для отчетности
- **Логирование**: Кастомизированные Allure-шаблоны для запросов/ответов
- **CI-готовность**: Встроенная поддержка видео-фиксации (Selenoid)
- **Cтруктура**:
  ```text
  src/
  ├── main/
  │   └── java/
  │       ├── data/            # Фабрики тестовых данных
  │       ├── helpers/         # Allure-утилиты
  │       ├── model/           # DTO-модели
  │       ├── services/        # API-клиенты
  │       ├── specs/           # Спецификации
  │       └── tests/           # Тесты
  └── resources/
      └── tpl/                 # Allure-шаблоны
  ```

## 🔍 Ключевые сущности

### Модели
- `Pet` - с вложенными `Category` и `Tag`
- `Order` - модель заказа
- `ApiResult` - универсальный response

### Сервисы
| Класс             | Методы                      |
|-------------------|-----------------------------|
| `PetApiService`   | CRUD + поиск по статусу     |
| `OrderApiService` | Создание/получение/удаление|

### Тесты
- **PetApiTest**: 6 сценариев (позитивные + негативные)
- **OrderApiTest**: 4 сценария с проверкой бизнес-логики


## 🛠 Технологический стек

| Компонент       | Версия    | Gradle-артефакт |
|-----------------|-----------|----------------|
| Java            | 17        | Встроенная через toolchain |
| RestAssured     | 5.3.1     | `implementation "io.rest-assured:*:$restAssuredVersion"` |
| Allure          | 2.21.0    | `implementation "io.qameta.allure:*:$allureVersion"` |
| JUnit 5         | 5.11.2    | `testImplementation "org.junit.jupiter:*:5.11.2"` |
| Selenide        | 7.5.1     | `testImplementation "com.codeborne:selenide:$selenideVersion"` |

## 🚀 Запуск тестов

**Стандартный запуск:**
```bash
gradle clean test
```

**Генерация Allure-отчета:**
```bash
gradle allureServe
```

## 🔧 Настройка Gradle

Ключевые части `build.gradle`:
```groovy
plugins {
    id 'java'
    id 'io.qameta.allure' version '2.11.2'
}

dependencies {
    testImplementation 'io.rest-assured:rest-assured:5.3.0'
    testImplementation 'org.junit.jupiter:junit-jupiter:5.9.0'
    compileOnly 'org.projectlombok:lombok:1.18.24'
    annotationProcessor 'org.projectlombok:lombok:1.18.24'
}

test {
    useJUnitPlatform()
    systemProperty 'allure.results.directory', 'build/allure-results'
}
```

### Пример отчета в Telegram:

<a href="#" target="_blank">
    <img src="/images/screenshots/TelegramNotification.jpg" alt="Telegram Notification" width="300" height="400">
</a>

### Пример Allure-отчета:

<a href="https://jenkins.autotests.cloud/job/api-diplom-tests/allure/#suites/12cb2cde760fee2fb9a4752bd75bbad0/cf6121cb8ce592b5/" target="_blank">
    <img src="/images/screenshots/AllureReport.png" alt="Allure Report" width="600">
</a>

### Прогон автотестов в Jenkins:

<a href="https://jenkins.autotests.cloud/job/api-diplom-tests/" target="_blank">
    <img src="/images/screenshots/Jenkins.png" alt="Jenkins Pipeline" width="600">
</a>

### Интеграция с Allure TestOps:

<a href="https://allure.autotests.cloud/project/4710/test-cases?treeId=0" target="_blank">
    <img src="/images/screenshots/AllureTestOps.png" alt="Allure TestOps" width="600">
</a>