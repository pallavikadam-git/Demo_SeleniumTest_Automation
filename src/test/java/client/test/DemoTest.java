package client.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import client.common.*;


@Listeners(TestListener.class)
public class DemoTest extends DriverClass {

    /**
     * Author - Kalyan Pawar
     * TC-01 - To validate Opportunity steps as per the Sales Process.
     */
    @Test(description = "TC-01")
    public static void testPass5() {
        Assert.assertTrue(true);
        BaseClass.justWait(10);
    }

    /**
     * Author - Kalyan Pawar
     * TC-03 -
     */
    @Test(description = "TC-02")
    public static void testPass4() {
//        ExcelDataReader dataReader = new ExcelDataReader();
//        String sheetName = "Sheet1";
//        String salesProcessName = dataReader.getStringData(sheetName, 0, 1)
//                + DataGenerator.generateRandomString(5);
//        BaseClass.justWait(5);
        Assert.assertTrue(true);
    }

    @Test(description = "TC-04")
    public static void testPass1() {
        Assert.assertTrue(true);
        BaseClass.justWait(8);
    }

    @Test(description = "TC-05")
    public static void testPass2() {
        BaseClass.justWait(15);
        Assert.assertTrue(true);
    }

    @Test(description = "TC-06")
    public static void testPass3() {
        BaseClass.justWait(5);
        Assert.assertTrue(true);
    }

    @Test(description = "TC-07")
    public static void testFail1() {
        BaseClass.justWait(10);
        Assert.fail();
    }
}

