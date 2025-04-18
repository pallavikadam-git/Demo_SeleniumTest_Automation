package client.common.reportportal;

import com.epam.reportportal.message.HashMarkSeparatedMessageParser;
import com.epam.reportportal.message.MessageParser;

import java.util.Collections;
import java.util.List;

final class Util {
    static final MessageParser MESSAGE_PARSER = new HashMarkSeparatedMessageParser();
    private static final List<String> LOGGING_ISSUE = Collections.singletonList(
            "com.epam.reportportal.service.logs.LoggingSubscriber");

    private Util() {
        //statics only
    }

    static boolean isInternal(String loggerName) {
        if (null == loggerName) {
            return false;
        }

        for (String packagePrefix : LOGGING_ISSUE) {
            if (loggerName.startsWith(packagePrefix)) {
                return true;
            }
        }
        return false;
    }
}
