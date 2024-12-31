package main.java.com.example.cosmocats.repository;

import com.example.cosmocats.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
