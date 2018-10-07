package e.poor.practice.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import e.poor.practice.models.Category;

@Dao
public interface ICategoryDAO {
    @Query("SELECT * FROM category")
     List<Category> getAllCategory();
}
