package net.moznion.fluent.logger.mock.sender;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.fluentd.logger.errorhandler.ErrorHandler;
import org.fluentd.logger.sender.Sender;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Mock class for FluentLogger.
 * <p>
 * If you emit fluent logs through this sender, logs are stacked into list. You can get this list
 * via method.
 */
public class MockSender implements Sender {
    private final List<FluentLog> fluentLogs;

    /**
     * Creates instance of mock fluent logger.
     */
    public MockSender(String host, int port, int timeout, int bufferCapacity) {
        fluentLogs = new ArrayList<>();
    }

    @Override
    public void close() {
        // NOP
    }

    @Override
    public boolean emit(String tag, Map<String, Object> data) {
        return emit(tag, System.currentTimeMillis() / 1000, data);
    }

    @Override
    public boolean emit(String tag, long timestamp, Map<String, Object> data) {
        return fluentLogs.add(new FluentLog(tag, timestamp, data));
    }

    @Override
    public void flush() {
        // NOP
    }

    @Override
    public String getName() {
        return "mock";
    }

    @Override
    public boolean isConnected() {
        return true;
    }

    @Override
    public void setErrorHandler(ErrorHandler errorHandler) {
        // NOP
    }

    @Override
    public void removeErrorHandler() {
        // NOP
    }

    /**
     * Clear stacked fluent logs.
     */
    public void clearFluentLogs() {
        fluentLogs.clear();
    }

    /**
     * Get stacked fluent logs.
     *
     * @return stacked fluent logs
     */
    public List<FluentLog> getFluentLogs() {
        return fluentLogs;
    }

    @Data
    @AllArgsConstructor
    public static class FluentLog {
        private String tag;
        private long timestamp;
        private Map<String, Object> data;
    }
}
