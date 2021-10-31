package serverside.model.logger;
import org.apache.log4j.Logger;

import javax.swing.*;

public class ServerLogger {
    private JTextArea managableArea;

    private Logger logger;
    private ServerLogAppender logAppender;

    public ServerLogger(JTextArea managableArea) {
        this.managableArea = managableArea;

        logger = Logger.getLogger("[SERVER]");
        logAppender = new ServerLogAppender();
        logger.addAppender(logAppender);
    }

    public void addLog(String log) {
        logger.info(log);
        managableArea.setText(logAppender.getLogs());
    }

    public void addLogWarn(String log) {
        logger.warn(log);
        managableArea.setText(logAppender.getLogs());
    }

    public String getLogs() { return logAppender.getLogs(); }
}
