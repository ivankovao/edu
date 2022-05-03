package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    /**
     * Метод, создающий список автомобилей
     *
     * @return carList - список автомобилей
     */
    public List<Car> createCarList(){
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("Lada", "white", 2019));
        carList.add(new Car("BMW", "black", 2004));
        carList.add(new Car("Renault", "grey", 1985));
        carList.add(new Car("Ford", "yellow", 2001));
        carList.add(new Car("Suzuki", "green", 2022));
        return carList;
    }

    /**
     * Метод, возвращающий список автомобилей, равный числу count
     * Если число count превышает кол-во автомобилей в исходном списке,
     * то метод возвращает список с максимальным числом автомобилей
     *
     * @param list содержит исходный список автомобилей
     * @param count содержит число возвращаемых автомобилей
     * @return carsList - целевой список автомобилей
     */
    public List<Car> findCountCars(List<Car> list, int count) {
        if (count >= list.size()) return list;
        int temp=0;
        List<Car> carsList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            carsList.add(list.get(i));
        }
        return carsList;
    }
}
