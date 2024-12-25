package client.common.reportportal;

import com.epam.reportportal.testng.ITestNGService;
//import com.google.common.base.Supplier;
//import com.google.common.base.Suppliers;
////import rp.com.google.common.base.Supplier;
////import rp.com.google.common.base.Suppliers;

public class ReportPortalTestNGListener extends BaseTestNGListener {
    public ReportPortalTestNGListener(ITestNGService testNgService) {
        super(testNgService);
    }

//    public static final Supplier<ITestNGService> SERVICE = Suppliers.memoize(new Supplier<ITestNGService>() {
//        public ITestNGService get() {
//            ReportPortal reportPortal = ReportPortal.builder().build();
//            return new TestNGService(reportPortal);
//        }
//    });
//
//    public ReportPortalTestNGListener() {
//        super((ITestNGService)SERVICE.get());
//    }
}