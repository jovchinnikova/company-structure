package com.solvd.companystructure;

import com.solvd.companystructure.companyinfo.*;
import com.solvd.companystructure.companyinfo.impl.AccountingImpl;
import com.solvd.companystructure.exception.InvalidPhoneException;
import com.solvd.companystructure.exception.TillProjException;
import com.solvd.companystructure.infrastructure.*;
import com.solvd.companystructure.people.*;
import com.solvd.companystructure.people.impl.ActionImpl;
import com.solvd.companystructure.people.impl.CountPeopleServiceImpl;
import com.solvd.companystructure.reflection.Dog;
import com.solvd.companystructure.reflection.OtherDog;
import com.solvd.companystructure.services.*;
import com.solvd.companystructure.services.impl.CountCostServiceImpl;
import com.solvd.companystructure.threads.Connection;
import com.solvd.companystructure.threads.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchFieldException,
            IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalArgumentException, InterruptedException {

        Company solvd = new Company("Solvd Inc");
        CEO director = new CEO("Ivan", "Ivanov");
        director.setDob(LocalDateTime.of(1980, 11, 16, 0, 0));
        solvd.setDirector(director);
        try {
            solvd.setPhoneNumber(12345);
        } catch (InvalidPhoneException exp) {
            LOGGER.error(exp.getMessage(), exp);
        } finally {
            LOGGER.debug("The end of phone number exception");
        }
        System.out.println();

        solvd.setSite("solvd.com");

        Service manualTest = new Service("manual testing", 200.00);
        Service autoTest = new Service("automated testing", 300.00);
        List<Service> qaServices = new ArrayList<>();
        qaServices.add(manualTest);
        qaServices.add(autoTest);
        Department.QA.setServices(qaServices);

        Worker vasya = new Worker("Vasiliy", "Petrov", 30.00);
        vasya.setStartVacation(LocalDateTime.of(2021, 10, 20, 0, 0));
        vasya.setDob(LocalDateTime.of(1980, 8, 25, 0, 0));

        Worker petia = new Worker("Petr", "Pypkin", 25.00);
        petia.setDob(LocalDateTime.of(1977, 01, 28, 0, 0));
        petia.setTillProjectEnd(20);

        Worker igor = new Worker("Igor", "Lastochkin", 40.00);
        igor.setStartVacation(LocalDateTime.of(2021, 10, 21, 0, 0));

        Worker tolik = new Worker("Anatoliy", "Peskov", 80.00);
        tolik.setTillProjectEnd(15);
        Worker vlad = new Worker("Vladislav", "Baranov", 39.50);
        Set<Worker> solvdWorkers = new HashSet<>();
        solvdWorkers.add(vasya);
        solvdWorkers.add(petia);
        solvdWorkers.add(igor);
        solvdWorkers.add(tolik);
        solvdWorkers.add(vlad);
        solvd.setWorkers(solvdWorkers);

        Client client1 = new Client("Sergey", "Novik");
        client1.setDob(LocalDateTime.of(2000, 05, 15, 0, 0));
        Client client2 = new Client("Saveliy", "Martynov");
        Client client3 = new Client("Valeriy", "Martynov");
        Client client4 = new Client("Valeriy", "Mogilev");
        Set<Client> solvdClients = new TreeSet<>();
        solvdClients.add(client1);
        solvdClients.add(client2);
        solvdClients.add(client3);
        solvdClients.add(client4);

        Order order1 = new Order(1, petia, autoTest, LocalDate.of(2021, 10, 1), client1);
        Order order100 = new Order(100, vasya, manualTest, LocalDate.of(2021, 10, 12), client1);
        Map<Integer, Order> allOrders = new HashMap<>();
        allOrders.put(order1.getOrderNumber(), order1);
        allOrders.put(order100.getOrderNumber(), order100);

        LOGGER.info(solvd);
        System.out.println();
        Worker.printVacDur();
        System.out.println();

        petia.countAge();
        vasya.countAge();
        director.countAge();
        client1.countAge();
        System.out.println();

        petia.performAction();
        director.performAction();
        client1.performAction();
        System.out.println();

        Function<Worker, Integer> days = worker -> {
            LocalDateTime currentDate = LocalDateTime.now();
            Integer daysPassed = currentDate.getDayOfYear() - worker.getStartVacation().getDayOfYear();
            return daysPassed;
        };

        AccountingImpl accounting = AccountingImpl.createInstance();
        solvd.setAccountingImpl(accounting);
        accounting.setWorkers(solvdWorkers);
        accounting.vacationCount(vasya, days);
        accounting.vacationCount(igor, days);
        accounting.startCount(petia);
        System.out.println();

        Set<Worker> workersOnVacation = new HashSet<>();
        workersOnVacation.add(vasya);
        workersOnVacation.add(igor);
        accounting.allVacationCount(workersOnVacation, days);
        System.out.println();

        client1.makeOrder(solvd, 12345);
        client1.makeOrder(solvd, "solv.com");
        System.out.println();

        LOGGER.info(order1);
        order1.salaryPenalty(petia);
        order100.countCost();
        System.out.println();

        Cleaning generalClean = new Cleaning("general cleaning", 20.00,
                LocalDateTime.of(2021, 10, 6, 0, 0));
        Cleaning plainClean = new Cleaning("plain cleaning", 10.00,
                LocalDateTime.of(2021, 10, 16, 0, 0));
        List<Cleaning> allCleaning = new ArrayList<>();
        allCleaning.add(generalClean);
        allCleaning.add(plainClean);
        solvd.setCleanings(allCleaning);
        generalClean.finishCleaning();
        plainClean.finishCleaning();
        System.out.println();

        FoodSupply applesSupply = new FoodSupply("apples supply", 5.00, 100,
                java.sql.Date.valueOf(LocalDate.of(2021, 10, 9)));
        FoodSupply bananasSupply = new FoodSupply("bananas supply", 4.00, 200,
                Date.valueOf(LocalDate.of(2021, 10, 20)));
        List<FoodSupply> foodSupplies = new ArrayList<>();
        foodSupplies.add(applesSupply);
        foodSupplies.add(bananasSupply);
        solvd.setFoodSupplies(foodSupplies);
        applesSupply.bringFood();
        bananasSupply.bringFood();
        applesSupply.countCost();
        bananasSupply.countCost();
        System.out.println();

        Set<Worker> tripParticip = new HashSet<>();
        tripParticip.add(petia);
        tripParticip.add(vasya);
        tripParticip.add(vlad);
        Set<Worker> engCourseParticip = new HashSet<>();
        engCourseParticip.addAll(tripParticip);
        engCourseParticip.add(igor);
        engCourseParticip.add(tolik);
        Activity sportTrip = new Activity("sport trip", Location.FOREST, tripParticip);
        Course engCourse = new Course("English course", Location.OFFICE, engCourseParticip);
        Course qaCourse = new Course("QA course", Location.OFFICE, engCourseParticip);
        List<Activity> solvdActivities = new ArrayList<>();
        solvdActivities.add(sportTrip);
        solvdActivities.add(engCourse);
        solvdActivities.add(qaCourse);
        solvd.setActivities(solvdActivities);
        LOGGER.info(sportTrip);
        LOGGER.info(engCourse);
        sportTrip.countPeople();
        engCourse.countPeople();
        System.out.println();

        Set<Worker> belWorkers = new HashSet<>();
        belWorkers.add(igor);
        belWorkers.add(petia);
        belWorkers.add(vlad);
        Set<Worker> mainWorkers = new HashSet<>();
        mainWorkers.add(vasya);
        mainWorkers.add(igor);
        Office belOffice = new Office("Roxoft", "Belarus", belWorkers);
        Office mainOffice = new Office("Solvd", "USA", mainWorkers);
        List<Office> offices = new ArrayList<>();
        offices.add(belOffice);
        offices.add(mainOffice);
        solvd.setOffices(offices);

        WorkSpace space1 = new WorkSpace("Venera", "3rd floor", belWorkers, 2);
        WorkSpace space2 = new WorkSpace("Saturn", "2nd floor", mainWorkers, 2);

        LOGGER.info(belOffice);
        belOffice.countPeople();
        LOGGER.info(mainOffice);
        mainOffice.countPeople();
        LOGGER.info(space1);
        space1.countPeople();
        LOGGER.info(space2);
        space2.countPeople();
        System.out.println();

        Human oleg = new Worker("Oleg", "Trofimov", 32.00);
        ControlClass.doAction(oleg);

        Human lev = new CEO("Lev", "Ivanov");
        ControlClass.doAction(lev);

        Human albert = new Client("Albert", "Petrov");
        ControlClass.doAction(albert);
        System.out.println();

        Laptop lap1 = new Laptop(Equipment.Mark.APPLE, 14);
        Laptop lap2 = new Laptop(Equipment.Mark.LENOVO, 10);
        Computer comp1 = new Computer(Equipment.Mark.IBM, 20);
        List<Equipment> belEquipment = new ArrayList<>();
        belEquipment.add(lap1);
        belEquipment.add(lap2);
        belEquipment.add(comp1);
        belOffice.setAllEquipment(belEquipment);
        belOffice.countEquipment();
        System.out.println();

        LOGGER.info("Interfaces");
        System.out.println();
        CountPeopleServiceImpl countPeopleService = new CountPeopleServiceImpl();
        countPeopleService.count(belOffice);
        countPeopleService.count(engCourse);
        System.out.println();

        CountCostServiceImpl countCostService = new CountCostServiceImpl();
        countCostService.countPrice(order100);
        countCostService.countPrice(applesSupply);
        countCostService.countPrice(generalClean);
        System.out.println();

        ActionImpl action = new ActionImpl();
        action.takeAction(igor);
        action.takeAction(director);
        action.takeAction(client1);
        System.out.println();

        try (AccessGranting access1 = new AccessGranting(director)) {
            access1.grantAccess();
        }
        System.out.println();

        LOGGER.info("All " + solvd.getTitle() + " clients alphabetically" + System.lineSeparator() + solvdClients);
        System.out.println();

        solvd.setOrders(allOrders);
        solvd.countIncome();
        System.out.println();

        LOGGER.info("Calculations using generic class");
        System.out.println();
        AllCalculations<Countable, Costable> allCalculations = new AllCalculations<>();
        allCalculations.setElement(engCourse);
        allCalculations.setOtherElement(applesSupply);
        allCalculations.getElement().countPeople();
        allCalculations.getOtherElement().countCost();
        System.out.println();

        LOGGER.info("Calculations using generic methods");
        System.out.println();
        AllCalculations<?, ?> calculation = new AllCalculations<>();
        calculation.calculate(sportTrip);
        calculation.calculate(order1);
        System.out.println();

        Distribution<Client, Laptop, WorkSpace> distrib1 = new Distribution<>(client1, lap1, space1);
        Distribution<CEO, Laptop, WorkSpace> distrib2 = new Distribution<>(director, lap1, space1);
        Distribution<Worker, Laptop, WorkSpace> distrib3 = new Distribution<>(vasya, lap2, space2);
        Distribution<Worker, Computer, WorkSpace> distrib4 = new Distribution<>(petia, comp1, space2);
        distrib2.print();
        distrib3.print();
        distrib4.print();
        System.out.println();

        Worker zina = new Worker("Zinaida", "Ivanova", 5.00);
        Worker fedya = new Worker("Fedor", "Petrov", 7.00);
        Worker egor = new Worker("Egor", "Melnikov", 6.00);
        List<Cleaning> zinaWork = new ArrayList<>();
        zinaWork.add(plainClean);
        List<Cleaning> fedyaWork = new ArrayList<>();
        fedyaWork.add(plainClean);
        fedyaWork.add(generalClean);
        List<FoodSupply> egorWork = new ArrayList<>();
        egorWork.add(applesSupply);
        egorWork.add(bananasSupply);

        Fulfillment<Worker, List<Cleaning>> zinaPerformance = new Fulfillment<>(zina, zinaWork);
        zinaPerformance.print();
        Fulfillment<Worker, List<Cleaning>> fedyaPerformance = new Fulfillment<>(fedya, fedyaWork);
        fedyaPerformance.print();
        Fulfillment<Worker, List<FoodSupply>> egorPerformance = new Fulfillment<>(egor, egorWork);
        egorPerformance.print();
        System.out.println();

        File file = new File("src/main/resources/article.txt");
        WordsCount count1 = new WordsCount(file);
        count1.countWords();
        System.out.println();

        lap1.writeCharacteristic();
        lap1.writeOrigin();
        lap2.writeOrigin();
        comp1.writeCharacteristic();
        System.out.println();

        solvd.printDepartments();
        System.out.println();

        String message = "There are no activities in the location";
        LOGGER.info(Location.OFFICE.findActivity(solvdActivities)
                .orElse(message));
        LOGGER.info(Location.CINEMA.findActivity(solvdActivities)
                .orElse(message));
        System.out.println();

        LOGGER.info("Workers with high salary:");
        solvdWorkers.stream()
                .filter(worker -> worker.getAverageSalary() > 30)
                .sorted()
                .forEach(worker -> LOGGER.info(worker + " earns " + worker.getAverageSalary() + "$ a day"));
        System.out.println();

        List<AdditionalService> addServices = new ArrayList<>();
        addServices.add(plainClean);
        addServices.add(generalClean);
        addServices.add(applesSupply);
        addServices.add(bananasSupply);
        Double addServicePrice = addServices.stream()
                .mapToDouble(addService -> addService.getPrice())
                .sum();
        LOGGER.info("All additional services will cost " + addServicePrice);
        System.out.println();

        List<Worker> participatingWorkers = solvdActivities.stream()
                .flatMap(activity -> activity.getWorkers().stream())
                .distinct()
                .collect(Collectors.toList());
        LOGGER.info("Participating workers:");
        LOGGER.info(participatingWorkers);
        System.out.println();

        Integer firstTillProj = solvdWorkers.stream()
                .map(worker -> worker.getTillProjectEnd())
                .findFirst()
                .orElseThrow(() -> new TillProjException("There is no info about time till project end"));
        LOGGER.info("First time till project end in list is  " + firstTillProj);
        System.out.println();

        solvdWorkers.stream()
                .filter(worker -> worker.getDob() != null)
                .peek(worker -> LOGGER.info("There is info about age of " + worker))
                .forEach(worker -> worker.countAge());
        System.out.println();

        Class<?>[] paramTypes = {String.class, String.class};
        Dog dog1 = Dog.class.getConstructor(paramTypes).newInstance("igor", "buldog");
        Field name = Dog.class.getDeclaredField("name");
        name.setAccessible(true);
        LOGGER.info(name.get(dog1));
        Method bark = Dog.class.getDeclaredMethod("bark");
        bark.setAccessible(true);
        bark.invoke(dog1);
        System.out.println();

        Class<?> otherDog = Class.forName(OtherDog.class.getName());
        OtherDog otherDog1 = (OtherDog) otherDog.newInstance();
        Field otherName = OtherDog.class.getDeclaredField("name");
        otherName.setAccessible(true);
        otherName.set(otherDog1, "fedor");
        String oName = (String) otherName.get(otherDog1);
        Field otherBreed = OtherDog.class.getDeclaredField("breed");
        otherBreed.setAccessible(true);
        otherBreed.set(otherDog1, "mops");
        String oBreed = (String) otherBreed.get(otherDog1);
        Method otherBark = OtherDog.class.getDeclaredMethod("bark", paramTypes);
        otherBark.setAccessible(true);
        otherBark.invoke(otherDog1, oName, oBreed);
        System.out.println();

        LOGGER.info("Thread usage");
        IntStream.range(0, 50)
                .boxed()
                .forEach(index -> {
                    Thread thread = new Thread(() ->
                    {
                        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
                        Connection connection = connectionPool.getConnection();
                        connection.create();
                        connection.update();
                        connectionPool.releaseConnection(connection);
                    });
                    thread.start();
                    try {
                        thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        IntStream.range(0,50)
                .forEach(ind -> {
                    CompletableFuture<Void> connectionFuture = CompletableFuture.runAsync(() ->
                    {
                        IntStream.range(0, 5)
                                .forEach(index -> {
                                    Connection connection = new Connection("Connection " + index);
                                    connection.create();
                                    connection.update();
                                });
                    },executorService);
                });
        executorService.shutdown();
    }
}
