package ua.com.company.hotels.business.service;

public interface Default {

    void abc(int a);

    void bcd(int b);

    default void bbc() {
        System.out.println("Hello 2");
    }

}
