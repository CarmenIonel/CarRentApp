package Application.Repository;

import Application.Model.Car;
import Application.Model.Rent;
import Application.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ionel Carmen on 22.05.2017.
 */
@Repository
public interface RentRepository extends JpaRepository<Rent, Integer>
{
    public List<Rent> findByCar(Car car);
    public Rent findByCode(String code);
    public List<Rent> findByUser(User user);
}
