package by.epam.rentacar.util.constant;

public class DBSchema {

    public static final class UserListTable {
        public static final String TABLE_NAME = "user_list";

        public static final String ID_USER = "id_user";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String EMAIL = "email";
        public static final String NAME = "name";
        public static final String SURNAME = "surname";
        public static final String PHONE_NUMBER = "phone_number";
        public static final String PASSPORT = "passport";
        public static final String ID_ROLE = "id_role";
    }

    public static final class CarListTable {
        public static final String TABLE_NAME = "car_list";

        public static final String ID_CAR = "id_car";
        public static final String BRAND = "brand";
        public static final String MODEL = "model";
        public static final String CLASS = "class";
        public static final String YEAR_OF_ISSUE = "year_of_issue";
        public static final String NUMBER_OF_SEATS = "number_of_seats";
        public static final String COLOR = "color";
        public static final String ENGINE_VOLUME = "engine_volume";
        public static final String IS_AVAILABLE = "is_available";
        public static final String PRICE = "price";
    }

    private DBSchema() {

    }
}
