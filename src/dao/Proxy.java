package dao;

public class Proxy {
    private static AccessoryDAO accessoryDao;
    private static ColorDAO colorDao;
    private static CostumerDAO costumerDao;
    private static EmployeeDAO employeeDao;
    private static ManufacturerDAO manufacturerDao;
    private static ModelDAO modelDao;
    private static RentalDAO rentalDao;
    private static VehicleDAO vehicleDao;
    private static Proxy proxy = new Proxy();
    private static String dbmode;

    private Proxy() {
        getInstance();
    }

    private static void loadProxy() {
        if (dbmode.equals("mongodb")) {
            accessoryDao = new AccessoryDAOM();
            colorDao = new ColorDAOM();
            costumerDao = new CostumerDAOM();
            employeeDao = new EmployeeDAOM();
            manufacturerDao = new ManufacturerDAOM();
            modelDao = new ModelDAOM();
            rentalDao = new RentalDAOM();
            vehicleDao = new VehicleDAOM();
        } else {
            accessoryDao = new AccessoryDAOI();
            colorDao = new ColorDAOI();
            costumerDao = new CostumerDAOI();
            employeeDao = new EmployeeDAOI();
            manufacturerDao = new ManufacturerDAOI();
            modelDao = new ModelDAOI();
            rentalDao = new RentalDAOI();
            vehicleDao = new VehicleDAOI();
        }
    }

    public static Proxy getInstance() {
        if(dbmode == null) {
            getInstance("mysql");
        }
        return proxy;
    }

    public static Proxy getInstance(String mode) {
        dbmode = mode;
        loadProxy();
        return proxy;
    }

    public AccessoryDAO getAccessoryDAO () {
        return accessoryDao;
    }

    public ColorDAO getColorDAO () {
        return colorDao;
    }

    public CostumerDAO getCostumerDAO () {
        return costumerDao;
    }

    public EmployeeDAO getEmployeeDAO () {
        return employeeDao;
    }

    public ManufacturerDAO getManufacturerDAO () {
        return manufacturerDao;
    }

    public ModelDAO getModelDAO () {
        return modelDao;
    }

    public RentalDAO getRentalDAO () {
        return rentalDao;
    }

    public VehicleDAO getVehicleDAO () {
        return vehicleDao;
    }

    public static String getDbmode() {
        return dbmode;
    }
}
