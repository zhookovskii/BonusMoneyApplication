# BonusMoneyApplication

Приложение, отображающее список бонусных карт, соответствующих программам
лояльности разных компаний

Приложение написано с использованием:
* Retrofit для взаимодействия с API
* Room для взаимодействия с базой данных
* Compose для отрисовки UI
* Coil для загрузки изображений
* Jetpack Compose Navigation для навигации
* Paging3 для пагинации
* Koin для внедрнения зависимостей

и соблюдает паттерны MVVM-архитектуры

Ниже представлены запросы к серверу:
```json
{
	"info": {
		"_postman_id": "f658293f-e8a0-4f83-94f9-b89c09238834",
		"name": "Task for android app delelopers",
		"schema": "https://schema.getpostman.com/json/collection/v2.0.0/collection.json"
	},
	"item": [
		{
			"name": "get all cards",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "TOKEN",
						"value": "123"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\n\t\"offset\": 0,\n    \"limit\": 5\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": "http://devapp.bonusmoney.pro/mobileapp/getAllCompanies"
			},
			"response": []
		},
		{
			"name": "get all cards ideal",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "TOKEN",
						"value": "123"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\n\t\"offset\": 0,\n    \"limit\": 5\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": "http://devapp.bonusmoney.pro/mobileapp/getAllCompaniesIdeal"
			},
			"response": []
		},
		{
			"name": "get all cards long",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "TOKEN",
						"value": "123"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\n\t\"offset\": 0,\n    \"limit\": 5\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": "http://devapp.bonusmoney.pro/mobileapp/getAllCompaniesLong"
			},
			"response": []
		},
		{
			"name": "get all cards error",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "TOKEN",
						"value": "123"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\n\t\"offset\": 0,\n    \"limit\": 5\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": "http://devapp.bonusmoney.pro/mobileapp/getAllCompaniesError"
			},
			"response": []
		}
	]
}
```
