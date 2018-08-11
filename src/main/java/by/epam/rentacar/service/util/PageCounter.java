package by.epam.rentacar.service.util;

public class PageCounter {

    private static final PageCounter instance = new PageCounter();


    public int countPages(int itemsCount, int itemsPerPage) {

        return (int) Math.ceil((double) itemsCount / itemsPerPage);

    }

    public static PageCounter getInstance() {
        return instance;
    }

    private PageCounter() {

    }

}
