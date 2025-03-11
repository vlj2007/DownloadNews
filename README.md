# Выгрузка новостей с сайта нгс.

# Задача:
Выгрузка с сайта последнию новостную страницу и сохранить данные в формате CSV.
Столбцы в файле: Rubrics,	Title,	Date,	Views,	Comments,	Links.
Ссылка на новостную страницу **https://ngs.ru/text/**

### Используемые расширения языка java

    <dependencies>
        <dependency>
            <groupId>org.jsoup</groupId>
            <artifactId>jsoup</artifactId>
            <version>1.18.3</version>
        </dependency>
        <dependency>
            <groupId>com.opencsv</groupId>
            <artifactId>opencsv</artifactId>
            <version>5.9</version>
        </dependency>
    </dependencies>

