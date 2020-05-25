package composition;

import java.time.LocalDate;

import static utils.ProjUtils.checkIfNull;

public class File {
    private String name;
    private LocalDate dateCreated;
    private ZipFolder zipFolder;

    public File(ZipFolder zipFolder, String name, LocalDate dateCreated) {
        checkIfNull(name);
        checkIfNull(dateCreated);
        checkIfNull(zipFolder);
        this.name = name;
        this.dateCreated = dateCreated;
        this.zipFolder = zipFolder;
    }

    public static File createFile(ZipFolder zipFolder, String name, LocalDate dateCreated) {
        File file = new File(zipFolder, name, dateCreated);
        zipFolder.addFile(file);
        return file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkIfNull(name);
        this.name = name;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }


    public ZipFolder getZipFolder() {
        return zipFolder;
    }

}
