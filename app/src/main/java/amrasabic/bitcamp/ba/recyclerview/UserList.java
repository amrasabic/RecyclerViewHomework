package amrasabic.bitcamp.ba.recyclerview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class UserList {

    private static List<User> mUserList;

    public static List<User> getUsers() {
        return mUserList;
    }

    public UserList() {
        mUserList = new ArrayList<>();
    }

    public void addUser(String name, String surname) {
        mUserList.add(new User(name.toString(), surname.toString()));
    }

    public static User getUserByUUID(UUID uuid){
        for(int i = 0; i < mUserList.size(); i++) {
            if(mUserList.get(i).getUUID().equals(uuid)) {
                return mUserList.get(i);
            }
        }
        return null;
    }

    public static void updateUser(UUID id, String name, String surname) {
        User u = getUserByUUID(id);
        u.setName(name);
        u.setSurname(surname);
    }

    public User getUser(int index) {
        return mUserList.get(index);
    }

    public int getSize() {
        return  mUserList.size();
    }

    public static void removeUser(UUID id) {
        Iterator<User> userIterator = mUserList.iterator();
        while (userIterator.hasNext()) {
            if(userIterator.next().getUUID().equals(id)){
                userIterator.remove();
                return;
            }
        }
    }
}
