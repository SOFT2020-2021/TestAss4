# Assignment #4 - REST API Test

We have chosen to use postman as our dedicated REST test tool.

Resources used by our frontend, which are subjects for our tests
*
*
*

Below is our exported tests. 

```json
{
	"info": {
		"_postman_id": "6de8fcdc-9e0a-408c-836a-b35ca41be115",
		"name": "BankTEst",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
	},
	"item": [
		{
			"name": "http://localhost:8080/BankAppTest_war_exploded/webapi/Transactions",
			"event": [
				{
					"listen": "test",
					"script": {
						"id": "55c85639-a0f5-442a-be80-f2515b238ef6",
						"exec": [
							"pm.test(\"? endpoint returns 200\", function () {\r",
							"    pm.response.to.not.be.error;\r",
							"    pm.response.to.have.status(200);\r",
							"});"
						],
						"type": "text/javascript"
					}
				}
			],
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\n    \"amount\": 500,\n    \"senderAccountId\": 2,\n    \"receiverAccountId\": 1\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/BankAppTest_war_exploded/webapi/Transactions",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"BankAppTest_war_exploded",
						"webapi",
						"Transactions"
					]
				}
			},
			"response": []
		},
		{
			"name": "Get Customers list",
			"event": [
				{
					"listen": "test",
					"script": {
						"id": "8d1f6e6c-9b32-4e76-b1fe-e66adb795014",
						"exec": [
							"pm.test(\"? endpoint returns 200\", function () {\r",
							"    pm.response.to.not.be.error;\r",
							"    pm.response.to.be.json;\r",
							"    pm.response.to.have.status(200);\r",
							"});"
						],
						"type": "text/javascript"
					}
				}
			],
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/BankAppTest_war_exploded/webapi/Customers",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"BankAppTest_war_exploded",
						"webapi",
						"Customers"
					]
				}
			},
			"response": []
		},
		{
			"name": "Get account",
			"event": [
				{
					"listen": "test",
					"script": {
						"id": "d2c9be37-663c-4fed-a343-866b281198f4",
						"exec": [
							"pm.test(\"? endpoint returns 200\", function () {\r",
							"    pm.response.to.not.be.error;\r",
							"    pm.response.to.be.json;\r",
							"    pm.response.to.have.status(200);\r",
							"    });"
						],
						"type": "text/javascript"
					}
				}
			],
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/BankAppTest_war_exploded/webapi/Accounts/1107772222",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"BankAppTest_war_exploded",
						"webapi",
						"Accounts",
						"1107772222"
					]
				}
			},
			"response": []
		}
	],
	"protocolProfileBehavior": {}
}
```
