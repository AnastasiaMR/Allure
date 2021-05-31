package ru.netology.delivery.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTestValidation() {
        $("[data-test-id=city] input").setValue(DataGenerator.generateCity());
        $("[data-test-id=date] input").setValue(DataGenerator.generateDate());
        $("[data-test-id=name] input").setValue(DataGenerator.generateName());
        $("[data-test-id=phone] input").setValue(DataGenerator.generatePhone());
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Успешно!"));
        assertEquals(DataGenerator.generateDate(), $("[data-test-id=date] input").getValue());
        assertEquals("Встреча успешно запланирована на " + DataGenerator.generateDate(),
                $(withText("Встреча успешно запланирована на")).getText());
        $("[data-test-id=date] input").setValue(DataGenerator.generateDate());
        $(withText("Запланировать")).click();
        $(withText("Перепланировать")).click();
        $("[data-test-id='success-notification']").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Успешно!"));
        assertEquals(DataGenerator.generateDate(), $("[data-test-id=date] input").getValue());
        assertEquals("Встреча успешно запланирована на " + DataGenerator.generateDate(), $(withText("Встреча успешно запланирована на")).getText());
    }
}

