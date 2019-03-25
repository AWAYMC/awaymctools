package net.argania.core.store;

public interface Entry {

    void insert();

    void update(boolean now);

    void delete();

}
