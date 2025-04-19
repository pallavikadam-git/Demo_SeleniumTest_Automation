package client.common.reportportal;

import com.epam.reportportal.service.Launch;
import com.epam.reportportal.service.ReportPortal;
import com.epam.ta.reportportal.ws.model.FinishExecutionRQ;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;

public class ReportPortalConfig {
    private static Launch launch;
    static ReportPortal reportPortal;

    public static void startLaunch() {
        reportPortal = ReportPortal.builder().build();
        StartLaunchRQ startLaunchRQ = new StartLaunchRQ();
        startLaunchRQ.setName("Full_Regression_Kalyan221");
        startLaunchRQ.setDescription("This launch includes all regression tests executed by Kalyan Pawar.");
//        startLaunchRQ.setUuid("Kalyan_YwrLD7_JTZmJcAPVoewn7slyCprgKwLPeF5SBtTpvuCuPcYpWfvtV1ZCS7E99vig");
//        startLaunchRQ.setRerun(true);
//        startLaunchRQ.setRerunOf("Rerun_Full_Regression_Kalyan221");
        launch = reportPortal.newLaunch(startLaunchRQ);
    }

    public static void stopLaunch() {
        if (launch != null) {
            launch.finish(new FinishExecutionRQ());
        }
    }
}

