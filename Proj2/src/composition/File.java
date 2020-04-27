package composition;

import java.time.LocalDate;

import static utils.ProjUtils.checkIfNull;

public class File {
    private String name;
    private LocalDate DateCreated;
    private Folder folder;

    public File(Folder folder, String name, LocalDate dateCreated) {
        checkIfNull(name);
        checkIfNull(dateCreated);
        checkIfNull(folder);
        this.name = name;
        DateCreated = dateCreated;
        this.folder = folder;
    }

    public static File createFile(Folder folder, String name, LocalDate dateCreated) {
        File file = new File(folder, name, dateCreated);
        folder.addFile(file);
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
        return DateCreated;
    }


    public Folder getFolder() {
        return folder;
    }

    public void changeFolder(String folderName) {
        checkIfNull(folderName);
        this.folder.removeFile(this);
        this.folder = Folder.findFolder(folderName);
        folder.addFile(this);
    }

}
