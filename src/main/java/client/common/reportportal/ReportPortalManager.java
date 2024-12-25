package client.common.reportportal;
import com.epam.reportportal.service.ReportPortal;

public class ReportPortalManager {
    private static ReportPortal reportPortal;

    private ReportPortalManager() {
        // Private constructor to prevent instantiation
    }

    public static synchronized ReportPortal getInstance() {
        if (reportPortal == null) {
            reportPortal = ReportPortal.builder().build();
        }
        return reportPortal;
    }
}
