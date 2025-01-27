package coffee.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Optional;

public class CoffeeOrderBoard {
    private static final Logger logger = LogManager.getLogger(CoffeeOrderBoard.class);

    private final LinkedList<Order> orders = new LinkedList<>();
    private int nextOrderNumber = 1; //

    public void add(String name) {
        try {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Имя не может быть пустым");
            }
            Order order = new Order(nextOrderNumber++, name);
            orders.add(order);
            logger.info("Заказ добавлен: {}", order);
            logger.debug("Текущее состояние очереди заказов: {}", orders);
        } catch (Exception e) {
            logger.error("Ошибка во время добавления заказа: ", e);
        }
    }

    public void deliver() {
        try {
            if (orders.isEmpty()) {
                logger.warn("Попытка выдать заказ, но очередь пустая.");
                return;
            }
            Order order = orders.poll();
            logger.info("Заказ выдан: {}", order);
            logger.debug("Текущее состояние очереди заказов: {}", orders);
        } catch (Exception e) {
            logger.error("Ошибка при выдаче ближайшего заказа: ", e);
        }
    }

    public void deliver(int number) {
        try {
            Optional<Order> orderOptional = orders.stream()
                    .filter(order -> order.getNumber() == number)
                    .findFirst();

            if (orderOptional.isPresent()) {
                Order order = orderOptional.get();
                orders.remove(order);
                logger.info("Заказ выдан за номером: {}", order);
            } else {
                logger.warn("Заказ с номером {} не найден.", number);
            }

            logger.debug("Текущее состояние очереди заказов: {}", orders);
        } catch (Exception e) {
            logger.error("Ошибка выдачи заказа с номером {}: ", number, e);
        }
    }

    public void draw() {
        try {
            if (orders.isEmpty()) {
                logger.info("Очередь пустая, нет заказов для отображения");
                System.out.println("Очередь пустая.");
                return;
            }

            System.out.println("Num | Name");
            for (Order order : orders) {
                System.out.println(order);
            }

            logger.info("Состояние очереди выведено в консоль.");
        } catch (Exception e) {
            logger.error("Ошибка при выводе очереди заказов: ", e);
        }
    }
}
