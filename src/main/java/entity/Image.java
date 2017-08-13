package entity;

public class Image {

    private String name;
    private String extendedName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtendedName() {
        return extendedName;
    }

    public void setExtendedName(String extendedName) {
        this.extendedName = extendedName;
    }

    @Override
    public String toString() {
        return "Image{" +
                "name='" + name + '\'' +
                ", extendedName='" + extendedName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (name != null ? !name.equals(image.name) : image.name != null) return false;
        return extendedName != null ? extendedName.equals(image.extendedName) : image.extendedName == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (extendedName != null ? extendedName.hashCode() : 0);
        return result;
    }
}
