#  Report Portal Test Automation

Автоматизированные UI и API тесты для [demo.reportportal.io](https://demo.reportportal.io)
Проект разработан в рамках тестового задания.

## 📌 Используемые технологии

* Язык: **Java**
* UI тесты: **Selenium WebDriver + TestNG + Page Object**
* API тесты: **RestAssured + TestNG**
* Сборщик: **Maven**
* Отчёты: **Allure Report**
* Логирование: **SLF4J + Logback**
* Архитектура: **Page Object + BaseTest**

## ⚙️ Установка и запуск

1. Склонировать репозиторий:

   ```bash
   git clone https://github.com/your-username/report-portal-test.git
   cd report-portal-test
   ```

2. Запустить UI или API тесты:

   ```bash
   mvn clean test
   ```

3. Сгенерировать Allure-отчёт:

   ```bash
   mvn allure:report
   mvn allure:serve
   ```

> Убедитесь, что установлен [Allure CLI](https://docs.qameta.io/allure/).

## 🔐 Доступ к демо-системе

* URL: [https://demo.reportportal.io](https://demo.reportportal.io)
* Login: `default`
* Password: `1q2w3e`

## 🔪 Покрытие тестами

| Тип          | Описание                                      | Статус |
| ------------ | --------------------------------------------- | ------ |
| UI Test      | Вход, переход на Dashboard, добавление Widget | ✅      |
| API Test     | Создание Dashboard через API                  | ✅      |
| API Negative | Негативный POST-запрос без параметров         | ✅      |

## 📂 Структура проекта

```
src/test/java/
├── base/            ← BaseTest с инициализацией WebDriver
├── pages/           ← Page Object классы
├── ui/              ← UI тесты (Selenium)
├── api/             ← API тесты (RestAssured)
├── utils/           ← Вспомогательные классы
```



🧑‍💻 Автор: Юлдашев Салават
🗓️ Дата выполнения: 06.2025
