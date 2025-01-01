package main.java.com.example.cosmocats.repository;

import com.example.cosmocats.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
