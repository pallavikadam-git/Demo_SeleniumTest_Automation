package client;

import org.testng.asserts.SoftAssert;

public class TestNGAsserts {
    public static void main(String[] args) {
//        Assert.assertEquals("Kalyan", "Kalyan", "firstname is failure");
//        Assert.assertEquals("Pawar", "Pawar", "lastname is failure");
//        Assert.assertEquals("09/11/1996", "09-11-1996", "dob is failure");
//        Assert.assertEquals(28, 28, "age is failure");

        SoftAssert sa = new SoftAssert();
        sa.assertEquals("Kalyan", "Kalyan", "firstname is failure");
        sa.assertEquals("Pawar", "Pawar", "lastname is failure");
        sa.assertEquals("09/11/1996", "09-11-1996", "dob is failure");
        sa.assertEquals(28, "28", "age is failure");
        sa.assertAll();
    }
}
