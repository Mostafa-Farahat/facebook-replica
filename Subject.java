public interface Subject {

    void follow(Observer friend);
    void unfollow(Observer friend);
    void notifyObserver();
    
}