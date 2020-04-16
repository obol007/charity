package pl.coderslab.charity.service;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.DTO.CategoryDTO;
import pl.coderslab.charity.domain.model.Category;
import pl.coderslab.charity.domain.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> findAllDTO() {
        ModelMapper mapper = new ModelMapper();
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category c : categories) {
            categoryDTOS.add(mapper.map(c, CategoryDTO.class));
        }
        return categoryDTOS;
    }
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
}
