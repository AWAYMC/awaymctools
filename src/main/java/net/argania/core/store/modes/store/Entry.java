package net.argania.core.store.modes.store;

public interface Entry {

    void insert();

    void update(boolean now);

    void delete();

}
