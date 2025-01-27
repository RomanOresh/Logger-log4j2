
import coffee.order.CoffeeOrderBoard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

        private static final Logger logger = LogManager.getLogger(Main.class);
        public static void main(String[] args) {
            logger.info("Приложение запущено");

            CoffeeOrderBoard board = new CoffeeOrderBoard();
            board.add("Jack");
            board.add("Jill");
            board.add("Bob");
            board.add(null);
            for(int i = 0; i < 10; i++){
                board.deliver();
            }
            board.deliver(0);
            board.deliver(3);
            board.deliver(99);


            logger.info("Приложение завершило работу");

    }
}
