package yl.enum_;

public enum OzWitch {
WEST("Miss Culch, aka the world"),NORTH("where are you from"),EAST("i am from English") ,SOURTH("Good morning")  ;
private String description ;
private static String mString;

private OzWitch(String description) {
    this.description = description;
}

public String getDescription() {
    return description;
}


public static void main(String[] args) {
        for (OzWitch ozWitch : OzWitch.values()) {
            System.out.println(ozWitch +  "  : " + ozWitch.getDescription());
        }
}

@Override
public String toString() {
    String id = name();
    String lower = id.substring(1).toLowerCase();
    return id.charAt(0) + lower;
}
}