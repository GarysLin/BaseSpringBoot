Test Project

recommend use IntelliJ to develope

1. run `docker-compose up -d --build` to up database and redis
2. run WebApiApplication in IntelliJ to up Server
3. open [http://localhost:8100/api/swagger-ui/index.html](http://localhost:8100/api/swagger-ui/index.html) 
to check swagger is run

自動產生mybatis相關檔案
1. 用Flyway or SQL 建立相關Table
2. 修改myBatisSingleTableGenerator.xml中資料庫連線資訊
3. 執行Task mbGenerator  
p.s. 產生出來的檔案一定不足，以產生出的檔案為Base進行改造

Note:  
If want to test websocket, can open[http://localhost:8100/api/index.html](http://localhost:8100/api/index.html)
in two browser and click start to link server and test chet room