package DAO.domain;


// 海洋生物类
public class MarineOrganism {
    private String name;
    private String scientificName;
    private String type;
    //  哺乳动物    爬行动物    海鱼   节肢动物   软体动物    腔长动物    无脊椎动物   海洋植物
    private String information;
    private String iconPath;

    @Override
    public String toString() {
        return "MarineOrganism{" +
                "name='" + name + '\'' +
                ", scientificName='" + scientificName + '\'' +
                ", type='" + type + '\'' +
                ", information='" + information + '\'' +
                ", iconPath='" + iconPath + '\'' +
                '}';
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfomation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
    public MarineOrganism(){}
    public MarineOrganism(MarineOrganism mo){
        this.name = mo.name;
        this.scientificName = mo.scientificName;
        this.type = mo.type;
        this.information = mo.information;
        this.iconPath = mo.iconPath;
    }
}
