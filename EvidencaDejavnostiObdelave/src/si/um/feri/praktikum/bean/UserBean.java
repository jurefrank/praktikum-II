package si.um.feri.praktikum.bean;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import si.um.feri.praktikum.entity.User;
import si.um.feri.praktikum.interfaces.IUser;

@Stateless
@Local(IUser.class)
public class UserBean implements IUser {

	@PersistenceContext
	EntityManager em;
	
//metode za shranjevanje user-ja po formi	

	@Override
	public void save(User user) {
		em.persist(user);
	}
	
	@Override
	public User find(String email) {
		
		return em.find(User.class, email);
	}

	@Override
	public void update(User user) {
		em.merge(user);
		
	}

	@Override
	public void delete(User user) {
		em.remove(em.contains(user) ? user : em.merge(user));
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> returnAll() {
		// TODO Auto-generated method stub
		return em.createQuery("select u from User u").getResultList();
	}

}
