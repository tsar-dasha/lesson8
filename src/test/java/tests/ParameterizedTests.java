package tests;

import com.codeborne.selenide.Configuration;
import data.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParameterizedTests {

    @BeforeEach
    void setUp() {
        open("https://demoqa.com/webtables");
        Configuration.pageLoadStrategy = "eager";
    }

    //Тест первый
    @EnumSource(Value.class)
    @ParameterizedTest
    @DisplayName("Выдается контакт содержащий введеное значение")
    void contactShouldContainTheEnteredValue(Value value) {
        $("#searchBox").setValue(value.name());
        $(".rt-table").shouldHave(text(value.description));
    }



    //Тест второй
    static Stream<Arguments> contactShouldContainTheEnteredText() {
        return Stream.of(
                Arguments.of("d", "Alden Cantrell 45 alden@example.com 12000 Compliance"),
                Arguments.of("y", "Kierra Gentry 29 kierra@example.com 2000 Legal")
                );
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("Выдается контакт содержащий введеное значение")
    void contactShouldContainTheEnteredText(Value value, String expectedText) {
        $("#searchBox").setValue(value.name());
        $(".rt-table").shouldHave(text(expectedText));
    }




    //Тест третий
    @ParameterizedTest
    @DisplayName("Выдается контакт содержащий введеное значение")
    @CsvSource(value = {
            "d",
            "y",
    })
    void TestWithCsvSource (String letter, String expectedText) {
        $("#searchBox").setValue(letter());
        $(".rt-table").shouldHave(text(expectedText));
    }
}

