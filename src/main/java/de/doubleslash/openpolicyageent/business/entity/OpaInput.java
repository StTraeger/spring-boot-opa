package de.doubleslash.openpolicyageent.business.entity;

import lombok.Data;

@Data
public class OpaInput {

    public String method;
    public String path;
    public String user;

}
