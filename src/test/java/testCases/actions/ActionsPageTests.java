package testCases.actions;

import com.epam.reportportal.junit5.ReportPortalExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ValueSource;
import pages.ActionsPage;
import base.BaseTest;
import utils.ColorDateConverter;

import java.util.List;

@ExtendWith(ReportPortalExtension.class)
public class ActionsPageTests extends BaseTest {
    private static ActionsPage actionsPage;

    @BeforeEach
    public void setActionsPage() {
        actionsPage = pageFactoryManager.getPage(ActionsPage.class);
    }

    @Test
    public void checkDragAndDrop() {
        homePage.openHomePage(baseUrl);
        homePage.clickOnActionsLink();

        List<String> tabsList = homePage.tabsList();
        homePage.switchToTab(tabsList.get(FIRST));

        actionsPage.implicitWait(timeout);
        actionsPage.moveElementToContainer();

        Assertions.assertTrue(actionsPage.getDroppableContainerText().contains("Dropped!"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"147, 203, 90"})
    public void checkDoubleClick(@ConvertWith(ColorDateConverter.class) String expectedColor) {
        homePage.openHomePage(baseUrl);
        homePage.clickOnActionsLink();

        List<String> tabsList = homePage.tabsList();
        homePage.switchToTab(tabsList.get(FIRST));

        actionsPage.implicitWait(timeout);
        actionsPage.performDoubleClick();
        Assertions.assertEquals(actionsPage.getDoubleClickableItemColor(), expectedColor);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0, 255, 0"})
    public void checkClickAndHold(@ConvertWith(ColorDateConverter.class) String expectedColor) {
        homePage.openHomePage(baseUrl);
        homePage.clickOnActionsLink();

        List<String> tabsList = homePage.tabsList();
        homePage.switchToTab(tabsList.get(FIRST));

        actionsPage.implicitWait(timeout);
        actionsPage.clickAndHold();

        Assertions.assertEquals(actionsPage.getClickableBoxColor(), expectedColor);
        Assertions.assertEquals(actionsPage.getClickableBoxText(), "Well done! keep holding that click now.....");
    }
}
