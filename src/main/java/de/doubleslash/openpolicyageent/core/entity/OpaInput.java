package de.doubleslash.openpolicyageent.core.entity;

import lombok.Data;

@Data
public class OpaInput {

    public String method;
    public String path;
    public String user;

}
