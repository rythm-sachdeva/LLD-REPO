import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args){
        FileSystem code = new file("code.txt", 50);
        FileSystem img = new file("image.png", 150);
        directory docs = new directory("Documents");
        FileSystem pdf = new file("file.pdf", 200);
        FileSystem txt = new file("notes.txt", 100);
        docs.add(pdf);
        docs.add(txt);
        directory home = new directory("Home");
        home.add(code);
        home.add(img);

        home.add(docs);
        System.out.println("Total size of Home directory: " + home.getSize() + "KB");
        System.out.println("Structure of Home directory:");
        home.printStructure("");
        System.out.println("Deleting Home directory:");
        home.delete();
    }
}

interface FileSystem{
    int getSize();
    void printStructure(String ident);
    void delete();
}

class file implements FileSystem{
    private final String name;
    private final int size;

    public file(String name, int size){
        this.name = name;
        this.size = size;
    }

    @Override
    public int getSize(){
        return this.size;
    }

    @Override
    public void printStructure(String ident){
        System.out.println(ident + "File: " + this.name + " Size: " + this.size + "KB");
    }

    @Override
    public void delete(){
        System.out.println("Deleting file: " + this.name);
    }
}

class directory implements FileSystem{
    private final String name;
    private final List<FileSystem> children;

    public directory(String name){
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void add(FileSystem fs){
        children.add(fs);
    }

    @Override
    public int getSize(){
        int totalSize = 0;
        for(FileSystem fs : children){
            totalSize += fs.getSize();
        }
        return totalSize;
    }

    @Override
    public void printStructure(String ident){
        System.out.println(ident + "Directory: " + this.name);
        for(FileSystem fs : this.children){
            fs.printStructure(ident + "  ");
        }
    }

    @Override
    public void delete(){
        System.out.println("Deleting directory: " + this.name);
        for(FileSystem fs : children){
            fs.delete();
        }
    }
}