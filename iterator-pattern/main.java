import java.util.*;;


public class main {
public static void main (String[] args) {
      Playlist playlist = new Playlist();
      playlist.addSong("Balam Thanedaar");
      playlist.addSong("Chaiyya Chaiyya");
      playlist.addSong("Jai Ho");
      Iterator<String> iterator = playlist.createIterator();
        while (iterator.hasNext()) {
            String song = iterator.next();
            System.out.println("Playing song: " + song);
        }

    }    
}


interface Iterator<T> {
    boolean hasNext();
    T next();
}

interface IterableCollection<T>{
    Iterator<T> createIterator();
}

class PlaylistIterator implements Iterator<String>{
    private final Playlist playlist;
    private int position;
    public PlaylistIterator(Playlist playlist){
        this.playlist = playlist;
        this.position = 0;
    }
    @Override
    public boolean hasNext(){
        return position < playlist.getSize();
    }
    public String next(){
        return this.playlist.getSongAt(position++);

    }
}

class Playlist implements IterableCollection<String>{
    private final List<String> songs;

    public Playlist(){
        this.songs = new ArrayList<>();
    }
    public void addSong(String song){
        songs.add(song);
    }
    public int getSize(){
        return songs.size();
    }
    public String getSongAt(int index){
        return songs.get(index);
    }

    @Override
    public Iterator<String> createIterator(){
        return new PlaylistIterator(this);
    }

}