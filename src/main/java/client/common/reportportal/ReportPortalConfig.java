package client.common.reportportal;

import com.epam.reportportal.service.ReportPortal;
import com.epam.ta.reportportal.ws.model.FinishExecutionRQ;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;
import com.epam.reportportal.service.Launch;

public class ReportPortalConfig {
    private static Launch launch;
    static ReportPortal reportPortal;

    public static void startLaunch() {
        reportPortal = ReportPortal.builder().build();
        StartLaunchRQ startLaunchRQ = new StartLaunchRQ();
        startLaunchRQ.setName("Full_Regression_Kalyan221");
        startLaunchRQ.setDescription("This launch includes all regression tests executed by Kalyan Pawar.");
//        startLaunchRQ.setRerun(true);
//        startLaunchRQ.setUuid("kalyan_5L6CK0VLRlinNGc1CDQzuaQvScYyZ0uC_LV7M1J-kxTuBzRFwIV20gF16QmGUyaB");
        launch = reportPortal.newLaunch(startLaunchRQ);
    }

    public static void stopLaunch() {
        if (launch != null) {
            launch.finish(new FinishExecutionRQ());
        }
    }
}

