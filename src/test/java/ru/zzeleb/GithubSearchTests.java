package ru.zzeleb;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GithubSearchTests {

    @BeforeEach
    void Setup() {Selenide.open("https://github.com/");}

    @ValueSource(strings = {
            "qa_guru", "junit"
    })
    @ParameterizedTest(name = "В результатах выдачи github отображается 10 результатов по запросу {0}")
    void githubSearchResultsShouldBeGreaterThen10(String testData) {
     $("input[type='text']").setValue(testData).pressEnter();
     $$("li.repo-list-item ").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(10));
    }



}
