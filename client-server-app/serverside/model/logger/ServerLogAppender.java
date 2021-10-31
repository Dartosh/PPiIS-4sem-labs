package serverside.model.logger;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class ServerLogAppender extends AppenderSkeleton {
    private String logs;

    public ServerLogAppender() { logs = ""; }

    protected void append(LoggingEvent event) {
        logs += event.getLoggerName() + " " +
                event.getMessage() + "\n";
    }

    public void close() { logs = ""; }

    public boolean requiresLayout() { return false; }

    public String getLogs() { return logs; }
}
