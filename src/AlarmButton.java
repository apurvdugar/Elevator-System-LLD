public class AlarmButton extends Button{

    void press() {
        if(!super.isPressed){
            super.isPressed = true;
            Alarm.trigger();
        }
    }

}
