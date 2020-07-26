package view;

public enum  UserEventEnum {
    /**
     * USECASE: when user click the create button
     */
    CREATE_NEW_EVENT,
    /**
     * USECASE: when user change the new view style of (day, week, month, agenda)
     */
    SET_VIEW_STYLE,
    /**
     * USECASE: when user get next view (day, week, month, agenda)
     */
    GET_NEXT_VIEW_STYLE,
    /**
     * USECASE: when user get previous view (day, week, month, agenda)
     */
    GET_PREVIOUS_VIEW_STYLE,
    /**
     * USECASE: when user import events from file format
     */
    IMPORT_EVENTS_FROM_FILE,
    /**
     * USECASE: when user select Today button
     */
    GET_TODAY_VIEW,
    /**
     * USECASE: when user click on the specific day button on fixed calendar
     */
    GET_SPECIFIC_DAY_VIEW,
    /**
     * USECASE: when user click next button on the fixed calendar
     */
    GET_NEXT_MONTHLY_VIEW,
    /**
     * USECASE: when user click previous button on the fixed calendar
     */
    GET_PREVIOUS_MONTHLY_VIEW
}
