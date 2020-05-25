package composition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static utils.ProjUtils.checkIfNull;

public class ZipFolder {
    private String name;
    private String category;

    private List<File> files = new ArrayList<>();

    private static Set<File> allFiles = new HashSet<>();
    private static Set<ZipFolder> extent = new HashSet<>();

    public ZipFolder(String name, String category) {
        checkIfNull(name);
        checkIfNull(category);

        this.name = name;
        this.category = category;
        extent.add(this);
    }

    public void addFile(File file) {
        if (!files.contains(file)) {
            if (allFiles.contains(file)) {
                throw new IllegalArgumentException("File already is in some folder");
            }
            files.add(file);
            allFiles.add(file);
        }
    }

    public void removeFile(File file) {
        if (allFiles.contains(file)) {
            if (files.contains(file)) {
                allFiles.remove(file);
                files.remove(file);
            } else {
                throw new IllegalArgumentException("File " + file.getName().toUpperCase() + " not in the folder" + this.getName().toUpperCase());
            }
        } else {
            throw new IllegalArgumentException("File " + file.getName().toUpperCase() + " doesnt exist");
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkIfNull(name);
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        checkIfNull(name);
        this.category = category;
    }

    public static ZipFolder findFolder(String name) {
        return extent.stream().filter(zipFolder -> zipFolder.getName().equals(name)).findFirst().get();
    }

    @Override
    public String toString() {
        String info = "ZipFolder{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", Files:[";
        StringBuilder sb = new StringBuilder();
        this.files.stream().forEach(file -> sb.append(" \'" + file.getName() + "\'"));
        sb.append(" ]");
        String files = sb.toString();
        return info + files;
    }
}
