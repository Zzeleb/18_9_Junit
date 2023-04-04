package ru.zzeleb;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
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

    @CsvFileSource(resources = "/testdata/firstSearchResultShouldContainExpectedText.csv")

    @ParameterizedTest(name = "В первом результате выдачи github по запросу {0} отображается текст {1}")
    void firstSearchResultShouldContainExpectedText(String testData, String expectedText) {
        $("input[type='text']").setValue(testData).pressEnter();
        $$("li.repo-list-item ").first().shouldHave(Condition.text(expectedText));
    }



}
