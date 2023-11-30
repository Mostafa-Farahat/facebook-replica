public interface Subject {

    void follow(User friend);
    void unfollow(User friend);
    void notifyObserver();
    
}