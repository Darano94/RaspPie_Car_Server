package pins;

import com.pi4j.io.gpio.*;

public class PinHandler {
    private static final GpioController gpioController = GpioFactory.getInstance();
    private static GpioPinDigitalOutput[] pins;

    private void initPins() {
        pins = new GpioPinDigitalOutput[4];
        pins[0] = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_00, "MotorLinksVorwaerts", PinState.LOW); //board-nr=11
        pins[1] = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02, "MotorLinksRueckwaerts", PinState.LOW); //board-nr=13
        pins[2] = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04, "MotorRechtsVorwaerts", PinState.LOW); //board-nr=19
        pins[3] = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_05, "MotorRechtsRueckwaerts", PinState.LOW); //board-nr=21
    }

    public void fahreVorwaerts(){
        pins[0].high();
        pins[2].high();
    }
    public void fahreRueckwaerts(){
        pins[1].high();
        pins[3].high();
    }
    public void fahreLinks(){
        pins[2].high();
    }

    public void fahreRechts(){
        pins[0].high();
    }
    public void linksDrehen(){
        pins[1].high();
        pins[2].high();
    }
    public void rechtsDrehen(){
        pins[0].high();
        pins[3].high();
    }

    public void closePins() {
        for(int i = 0; i < pins.length; i++)
            pins[i].low();
    }
    public PinHandler() {
        initPins();
    }
}
