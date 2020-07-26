package controller;

import app.TimeInterval;
import app.ViewStyle;

public class UserDataEvent {

    /**
     * Name of the new event user want to create
     */
    private String eventName;
    /**
     * view style user want to switch to
     */
    private ViewStyle viewStyle;
    /**
     * time interval of the new event user want to create
     */
    private TimeInterval timeInterval;
    /**
     * file path of the file user want to import
     */
    private String filePath;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public ViewStyle getViewStyle() {
        return viewStyle;
    }

    public void setViewStyle(ViewStyle viewStyle) {
        this.viewStyle = viewStyle;
    }

    public TimeInterval getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(TimeInterval timeInterval) {
        this.timeInterval = timeInterval;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
