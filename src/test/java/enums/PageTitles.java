package enums;

public enum PageTitles {
    HOME("Home"), BOOKS("Books"), AUTHORS("Authors");

    private String title = "";

    PageTitles(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

}
