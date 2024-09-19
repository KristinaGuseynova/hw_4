package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideSearchTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920*1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void shouldHaveSoftAssertions() {

        //открыть страницу
        open("/selenide/selenide");
        //перейти в раздел Wiki проекта
        $("#wiki-tab").click();
        //$(byText("Add a custom sidebar")).scrollTo();
        //убедиться, что в списке страниц (Pages) есть страница SoftAssertions
        $("#wiki-content").$(byText("Show 3 more pages…")).click();
        $("#wiki-wrapper").shouldHave(text("SoftAssertions"));
        //открыть страницу SoftAssertions
        $(byText("SoftAssertions")).click();
        //проверить, что внутри есть пример кода для JUnit5
        $(".repository-content").shouldHave(text(
                """
                        @ExtendWith({SoftAssertsExtension.class})
                        class Tests {
                          @Test
                          void test() {
                            Configuration.assertionMode = SOFT;
                            open("page.html");
                        
                            $("#first").should(visible).click();
                            $("#second").should(visible).click();
                          }
                        }"""));





    }

}
