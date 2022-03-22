package com.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.DespegarAlojamientoPage;
import pageObject.DespegarHomePage;
import pageObject.DespegarResultPage;
import until.BaseTest;

public class DespegarTest2 extends BaseTest {

    private WebDriver driver = null;

    @BeforeMethod(alwaysRun=true)
    public void setup(ITestContext context) {
        String navegadorTestSuite = context.getCurrentXmlTest().getParameter("Navegador");
        String navegador = navegadorTestSuite != null ? navegadorTestSuite : "CHROME";
        driver = BaseTest.iniciarBrowser(navegador);
        BaseTest.gotoMainPageDespegar(driver);

    }

    @Test(groups= {"group2"}, dataProvider = "ciudad", description = "Validar busqueda en Despegar de 2 ciudades, mostrar nombre y precio del primer hotel")
    public void AlojamientoProvide(String ciudades) throws InterruptedException {

        DespegarHomePage homePage = new DespegarHomePage(driver);
        Assert.assertTrue(homePage.DespegarVisible());
        homePage.iraAlojamiento();
        DespegarAlojamientoPage alojamiento = new DespegarAlojamientoPage(driver);
        alojamiento.alojamientos(ciudades);
        DespegarResultPage result = new DespegarResultPage(driver);
        Assert.assertTrue(result.PrimeraBusqueda());
        result.NombreHotel();
        result.precioHotel();
    }

    @AfterMethod(alwaysRun=true)
    public void endSetup() {
        driver.close();
    }

    @DataProvider(name = "ciudad")
    public Object[][] dataProvide() {
        return new Object[][] { { "Necochea, Buenos Aires, Argentina" }, { "Puerto Iguazú, Misiones, Argentina" } };
    }

}
