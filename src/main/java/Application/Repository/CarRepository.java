package Application.Repository;

import Application.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ionel Carmen on 22.05.2017.
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Integer>
{
    Car findByNrInmatriculare(String nr);
    List<Car> findByModel(String model);
}
