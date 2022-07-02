package menu;

public class Menu {
    private String UrlMenu;
    private String Name;

    public Menu(){}

    public Menu(String UrlMenu, String Name) {
        this.UrlMenu = UrlMenu;
        this.Name = Name;
    }

    public String getUrlMenu() {
        return UrlMenu;
    }

    public void setUrlMenu(String urlMenu) {
        this.UrlMenu = urlMenu;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}
