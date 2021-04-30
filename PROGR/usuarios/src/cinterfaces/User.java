package cinterfaces;

import java.io.Serializable;

public interface User extends Serializable {

    String getName();
    void setName(String name);
    String getEmail();
    String getPassword();

}
