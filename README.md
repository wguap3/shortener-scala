# URL Shortener
## First project Scala
REST-сервис для сокращения ссылок, написанный на Scala + ZIO.

## Стек

- **Scala 3.3.1**
- **ZIO 2** — управление эффектами и асинхронностью
- **zio-http** — HTTP сервер
- **zio-json** — JSON сериализация

## Структура проекта

```
src/main/scala/shortner/
├── domain/
│   └── Url.scala           # Модель данных
├── repository/
│   ├── UrlRepository.scala          # trait (интерфейс)
│   └── InMemoryUrlRepository.scala  # реализация в памяти
├── service/
│   └── UrlService.scala    # бизнес-логика
├── routes/
│   └── UrlRoutes.scala     # HTTP роуты
└── Main.scala              # точка входа
```

## Запуск

```bash
sbt run
```

Сервер запустится на `http://localhost:8080`

## API

### Создать короткую ссылку

```
POST /shorten
Content-Type: application/json

{"originalUrl": "https://example.com/very/long/url"}
```

Ответ:
```json
{"shortUrl": "http://localhost:8080/AHmnWK"}
```

### Перейти по короткой ссылке

```
GET /:code
```

Ответ: `307 Redirect` на оригинальный URL

## Пример

```bash
# Создать короткую ссылку
curl -X POST http://localhost:8080/shorten \
  -H "Content-Type: application/json" \
  -d '{"originalUrl": "https://www.youtube.com/watch?v=dQw4w9WgXcQ"}'

# Перейти по ссылке
curl -v http://localhost:8080/AHmnWK
```
