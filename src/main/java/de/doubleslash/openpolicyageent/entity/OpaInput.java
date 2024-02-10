package de.doubleslash.openpolicyageent.entity;

import lombok.Data;

@Data
public class OpaInput {

    public String method;
    public String path;
    public String user;

}
