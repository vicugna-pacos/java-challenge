### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push

- Enhance the code in any ways you can see, you are free! Some possibilities:
  - Add tests
  - Change syntax
  - Protect controller end points
  - Add caching logic for database calls
  - Improve doc and comments
  - Fix any bug you might find
- Edit readme.md and add any comments. It can be about what you did, what you would have done if you had more time, etc.
- Send us the link of your repository.

#### Restrictions
- use java 8


#### What we will look for
- Readability of your code
- Documentation
- Comments in your code 
- Appropriate usage of spring boot
- Appropriate usage of packages
- Is the application running as expected
- No performance issues

#### Your experience in Java

Please let us know more about your Java experience in a few sentences. For example:

- I have 3 years experience in Java and I started to use Spring Boot from last year
- I'm a beginner and just recently learned Spring Boot
- I know Spring Boot very well and have been using it for many years

### Javaの開発経験

- およそ10年間のJava開発経験あり。バージョンは 8。最後に業務でJavaを扱ったのは2018年。
- Struts, Spring Boot の開発経験あり。Spring Boot の経験は1年弱。小規模のWebアプリケーションを構築した。
- O/R マッパーは、MyBatis の開発経験あり。

### やったこと

- Service と Controller のテストを作成。
- JavaDoc を追加。
- キャッシュの追加。
- Service と Controller の処理を整理。
  - 検索や更新のメソッドで対象が見つからない場合は、Controller で 404 (Not Found) を返すようにそろえた。

### やらなかったこと(時間があれば実施したこと)

- Controller のパッケージをURLの階層と合わせる。
  - 変更案：`apidemo.controllers.api.v1`
  - 今回は小さいデモアプリなので、冗長と判断した。
- キャッシュの指定を Repository ではなく Service に行った。Repository でキャッシュの指定をするには検索用メソッドを再定義する必要があり、コードが冗長になると判断したため。
- キャッシュのカスタマイズ。有効期限やキャッシュを更新するタイミングの設定など。
- Entity と ViewModel (画面項目定義) の分離。
- リクエストパラメータのバリデーション。

