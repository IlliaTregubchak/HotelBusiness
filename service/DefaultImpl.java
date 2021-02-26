package ua.com.company.hotels.business.service;

public class DefaultImpl extends Main implements Default {

    @Override
    public void abc(int a) {

        final Class<DefaultImpl> defaultClass = DefaultImpl.class;

        System.out.println(defaultClass.isAnnotationPresent(Override.class));

        defaultClass.getDeclaredMethods(); // DefaultImpl

        defaultClass.getMethods(); // Main
    }

    @Override
    public void bcd(int b) {

    }

    @Override
    public void bbc() {
        System.out.println("Hello");
    }
}
