package si.um.feri.praktikum.interfaces;

import java.util.List;

import si.um.feri.praktikum.entity.User;

public interface IUser {
	
	User find(String email);
	void save(User user);
	void update(User user);
	void delete(User user);
	
	List<User> returnAll();
	
}
