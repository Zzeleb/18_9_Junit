package ru.zzeleb;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import org.junit.jupiter.params.provider.MethodSource;

import ru.zzeleb.data.Locale;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TelegramLocaleWebTest {

    static Stream<Arguments> siteShouldContainAllOfGivenButtonsForGivenLocale() {
        return Stream.of(
                Arguments.of(Locale.English, List.of("EN", "Twitter","Home", "FAQ", "Apps", "API", "Protocol")),
                Arguments.of(Locale.Русский, List.of("RU", "Twitter","Главная", "FAQ", "Приложения", "API", "Протокол")
        ));
    }

    @MethodSource
    @ParameterizedTest(name = "Для локали {0} на сайте https://telegram.org/ должен отображаться список кнопок {1}")
    void siteShouldContainAllOfGivenButtonsForGivenLocale(Locale locale, List<String> expectedButtons) {
        Selenide.open("https://telegram.org/");
        $("a.dropdown-toggle").click();
        $$(".dropdown-menu a").find(Condition.text(locale.name())).click();
        $$(".nav a").filter(Condition.visible).shouldHave(texts(expectedButtons));

    }



}
