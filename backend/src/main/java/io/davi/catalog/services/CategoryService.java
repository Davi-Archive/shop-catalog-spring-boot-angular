package io.davi.catalog.services;

import io.davi.catalog.dto.CategoryDTO;
import io.davi.catalog.entities.Category;
import io.davi.catalog.repositories.CategoryRepository;
import io.davi.catalog.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> list = repository.findAll();

//        List<CategoryDTO> listDto = list.stream()
//                .map(x -> new CategoryDTO(x))
//                .collect(Collectors.toList());

//        List<CategoryDTO> listDto = new ArrayList<>();
//        for(Category cat:list){
//            listDto.add(new CategoryDTO());
//        }
        return list.stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
    Category entity = new Category();
    entity.setName(dto.getName());
    entity = repository.save(entity);
    return new CategoryDTO(entity);
    }
}
