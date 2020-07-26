package controller;

import view.UserEventEnum;

public class Controller {
    public Controller(){}

    /**
     *  your logic to handle user event here ....
     *  all view events will be captured into UserDataEvent object, and passed along with UserEventEnum type
     *  Therefore, you have to work with Christian to handle each use cases in the UserDataEvent Enum Class
     *  it is still basic use cases, we'll discuss further on Sunday (07/26) to dig deep into this, but if gives you
     *  a taste for now
     *
     *  The way it works is that you will keep instance of Model and call its API's method (ie. stateChanged(...))
     *  and View will keep instance of Controller to call handleEvent(eventType, event) to handle user interaction
     *
     *  By doing this way, all the logic will encapsulate in the Controller class and decouple it from Model and View.
     *  Hence MVC patterns arise
     */
    public void handleEvent(UserEventEnum eventType, UserDataEvent event){

    }
}
