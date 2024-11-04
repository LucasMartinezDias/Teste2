import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(float temperature);
}

class TemperatureSensor {
    private List<Observer> observers = new ArrayList<>();
    private float temperature;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }

    public void checkTemperature(float newTemperature) {
        this.temperature = newTemperature;
        if (temperature > 75.0) {
            notifyObservers();
        } else {
            System.out.println("Temperatura normal. Nenhuma ação necessária.");
        }
    }
}

class Alarm implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Alarme disparado! Temperatura muito alta: " + temperature + "°C");
    }
}

class ManagerNotifier implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Notificação ao gerente: Temperatura atingiu " + temperature + "°C");
    }
}

class ACController implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Ar-condicionado ajustado devido à temperatura de " + temperature + "°C");
    }
}

public class Main {
    public static void main(String[] args) {
        TemperatureSensor sensor = new TemperatureSensor();

        Observer alarm = new Alarm();
        Observer managerNotifier = new ManagerNotifier();
        Observer acController = new ACController();

        sensor.addObserver(alarm);
        sensor.addObserver(managerNotifier);
        sensor.addObserver(acController);

        sensor.checkTemperature(80.0f);

        sensor.checkTemperature(70.0f);
    }
}
