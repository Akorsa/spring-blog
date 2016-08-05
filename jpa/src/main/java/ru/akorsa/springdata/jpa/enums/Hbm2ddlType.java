package ru.akorsa.springdata.jpa.enums;

public enum Hbm2ddlType {
    CREATE("create"),
    UPDATE("update"),
    VALIDATE("validate"),
    CREATE_DROP("create-drop"),
    NONE("none");

    private final String value;

    Hbm2ddlType(final String type){
        this.value = type;
    }

    public String toValue() {
        return value;
    }

    public static Hbm2ddlType fromValue(final String value) {
        if (value != null) {
            for (Hbm2ddlType hbm2ddl : values()) {
                if (hbm2ddl.value.equals(value)) {
                    return hbm2ddl;
                }
            }
        }
        return Hbm2ddlType.NONE;
    }

}
