package model.enums;

public enum Slogan {
    VALUE_0("I shall have my revenge, in this life or the next"),
    VALUE_1("Today we fight for a new beginning!"),
    VALUE_2("You have no right to rule on these lands!"),
    VALUE_3("A king always pay his debts"),
    VALUE_4("Nothing can stop us"),
    VALUE_5("I will take back what belongs to me");


    private String slogan;
    Slogan(String slogan) {
        this.slogan = slogan;
    }

    public String getSlogan() {
        return slogan;
    }
}
