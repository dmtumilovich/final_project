package by.epam.rentacar.service.util;

public class PageCounter {

    public static int countPages(int itemsCount, int itemsPerPage) {

        return (int) Math.ceil((double) itemsCount / itemsPerPage);

    }

    private PageCounter() {

    }

}
