package DAO.domain;

// 海洋生物类
public class MarineOrganism {
    private String type;
    private String name;
    //  哺乳动物    爬行动物    海鱼   节肢动物   软体动物    腔长动物    无脊椎动物   海洋植物
    private String scientificName;
    private String basicInformation;
    private String IconPath;

    public String getIconPath() {
        return IconPath;
    }

    public void setIconPath(String iconPath) {
        IconPath = iconPath;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    private String food;

    public String getBasicInformation() {

        return basicInformation;
    }

    public void setBasicInformation(String basicInformation) {
        this.basicInformation = basicInformation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    @Override
    public String toString() {
        return "name:" + name + " " + "scientificName" + scientificName + ":" + basicInformation;
    }
}
