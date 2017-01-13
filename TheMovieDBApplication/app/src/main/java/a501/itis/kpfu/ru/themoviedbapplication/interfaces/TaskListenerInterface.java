package a501.itis.kpfu.ru.themoviedbapplication.interfaces;


import java.util.List;

public interface TaskListenerInterface {
    void onTaskFinish(List list, int id);
    void onTaskStarted();
}
